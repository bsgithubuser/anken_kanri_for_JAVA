package jp.co.bsja.anken.dao.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jp.co.bsja.anken.common.CommonFunction;
import jp.co.bsja.anken.csv.AnkenKanriCsv;
import jp.co.bsja.anken.dao.AnkenRegisterDao;
import jp.co.bsja.anken.entity.TProjInfo;
import jp.co.bsja.anken.entity.TProjSkill;

import org.seasar.framework.beans.util.BeanMap;
import org.seasar.s2csv.csv.S2CSVParseCtrl;
import org.seasar.s2csv.csv.exception.CSVFormatException;
import org.seasar.s2csv.csv.exception.runtime.CSVValidationResultRuntimeException;
import org.seasar.s2csv.csv.factory.S2CSVCtrlFactory;
import org.seasar.s2csv.csv.message.CSVMsg;
import org.seasar.s2csv.csv.validator.CSVValidateResult;

public class PrjInfoBatchImpl extends CommonFunction implements PrjInfoBatch {

  @Resource
  protected S2CSVCtrlFactory s2csvCtrlFactory;

  /** 成功数 .*/
  public static final int SUCCESS = 0;
  /** 項目数エラー .*/
  public static final int ITEMS_ERROR = 1;
  /** 未入力エラー .*/
  public static final int REQUIRED_ERROR = 2;
  /** 最大文字長エラー .*/
  public static final int MAXLENGTH_ERROR = 3;
  /** データ形式エラー .*/
  public static final int DATE_ERROR = 4;
  /** マスタ管理エラー .*/
  public static final int MASTER_ERROR = 5;

  /**
   * CSV一括登録処理を行います。 .
   *
   * @param csvパス
   * @return 一括登録結果を返します。
   */
  @Override
  public String entry(String path) {

    int[] errorList = {0,0,0,0,0,0};
    try {
      // エラー
      List<CSVValidateResult> validateErrList = new ArrayList<CSVValidateResult>();
      // DBから全検索マスタ情報取得
      List<BeanMap> cmpnList = new AnkenRegisterDao().selectAll("M_CMPN");
      List<BeanMap> userList = new AnkenRegisterDao().selectAll("M_USERS");
      List<BeanMap> skillList = new AnkenRegisterDao().selectAll("M_SKILL");

      // ファイルリーダーにファイルを登録
      BufferedReader br = new BufferedReader(new InputStreamReader(
          new FileInputStream(path), "SJIS"));

      // CSVデータを取得するコントローラ定義
      S2CSVParseCtrl<AnkenKanriCsv> controller =
          s2csvCtrlFactory.getParseController(AnkenKanriCsv.class, br);

      // 年度を取得し、string型へ変更
      String fiscalYear = fiscalYear();

      try {
        // 取得行数ループ処理を実行
        while (controller.readNext()) {

          // バリデーションエラーをparse
          CSVValidateResult validateResult = controller.validate();
          // エラーがある場合、リストに格納しこれより下の処理をスキップ
          if (validateResult != null) {
            validateErrList.add(validateResult);
            for (CSVMsg msg : validateResult.getMsgs()) {
              // 未入力チェック
              if (msg.getMsgKey().equals("errors.required")) {
                errorList[REQUIRED_ERROR]++;
                // 最大文字長チェック
              } else if (msg.getMsgKey().equals("errors.maxlength")) {
                errorList[MAXLENGTH_ERROR]++;
              }
            }
            continue;
          }

          // CSVを行単位でparse
          AnkenKanriCsv ankenKanri = controller.parse();

          // 案件情報テーブルに取得した値格納
          TProjInfo tprojInfo = getTProjInfo();
          tprojInfo.prjName = ankenKanri.prjName;
          if (!empty(ankenKanri.other)) {
            tprojInfo.other = ankenKanri.other.replace("\'", "");
            tprojInfo.other = tprojInfo.other.replaceAll("\\\\n", "\n");
          }

          // 概要は「’」を除外し格納
          tprojInfo.orverview = ankenKanri.overview.replace("\'", "");
          tprojInfo.orverview = tprojInfo.orverview.replaceAll("\\\\n", "\n");

          // データ形式チェック
          try {
            // 発生日をyyyy/MM/dd形式に変換
            tprojInfo.genDate = new Date(formatDate(
                ankenKanri.genDate, FORMAT_YMD_SLASH).getTime());

            // 期間（開始日）をyyyy/MM/dd形式に変換
            if (!empty(ankenKanri.periodFrom)) {
              tprojInfo.periFrom = new Date(formatDate(
                  ankenKanri.periodFrom, FORMAT_YMD_SLASH).getTime());
            }

            // 期間（終了日）をyyyy/MM/dd形式に変換
            if (!empty(ankenKanri.periodTo)) {
              tprojInfo.periTo = new Date(formatDate(
                  ankenKanri.periodTo, FORMAT_YMD_SLASH).getTime());
            }
          } catch (ParseException e1) {
            // 日付形式エラー
            errorList[DATE_ERROR]++;
            continue;
          }

          // 即日フラグチェック
          if (!empty(ankenKanri.sameDayFlg)) {
            tprojInfo.sameDayFlg = true;
          }
          // 随時フラグチェック
          if (!empty(ankenKanri.anyTimeFlg)) {
            tprojInfo.anyTimeFlg = true;
          }
          // 長期フラグチェック
          if (!empty(ankenKanri.longTermFlg)) {
            tprojInfo.longTermFlg = true;
          }
          // 延長フラグチェック
          if (!empty(ankenKanri.extentionFlg)) {
            tprojInfo.extentionFlg = true;
          }

          // 会社名存在チェック
          for (BeanMap cmpnMap : cmpnList) {
            if (ankenKanri.cmpnName.equals(cmpnMap.get("cmpnName"))) {
              tprojInfo.cmpnId = (Integer)cmpnMap.get("cmpnId");
            }
          }
          // 存在チェック
          if (empty(tprojInfo.cmpnId)) {
            errorList[MASTER_ERROR]++;
            continue;
          }

          // 担当者名存在チェック
          for (BeanMap userMap : userList) {
            if (ankenKanri.userName.equals(userMap.get("userName"))) {
              tprojInfo.userId = (Integer)userMap.get("userId");
            }
          }
          // 存在チェック
          if (empty(tprojInfo.userId)) {
            errorList[MASTER_ERROR]++;
            continue;
          }

          // 「、」「改行」を置き換え
          ankenKanri.skillName = ankenKanri.skillName.replace("\'", "");
          String[] skills = ankenKanri.skillName.split("\\\\n");

          // 案件スキルテーブルリスト宣言
          List<TProjSkill> tprojSkillList = new ArrayList<TProjSkill>();
          // 案件スキルテーブルのシーケンス取得
          int tprojSkillSeq = new AnkenRegisterDao().getPrjSkillIdSeq();
          tprojInfo.prjSklId = tprojSkillSeq;

          // その他の言語
          String otherSkill = "";

          // スキル名存在チェック
          for (int i = 0; i < skills.length; i++) {
            TProjSkill tprojSkill = new TProjSkill();
            // スキル管理マスタにスキルIDを検索
            for (BeanMap skillMap : skillList) {
              // スキル管理マスタに登録されていた場合
              if (skills[i].equals(skillMap.get("skillName"))) {

                // 案件スキルID,登録,更新時間を格納
                tprojSkill.prjSklId = tprojSkillSeq;
                tprojSkill.createDate = getBaseDt();
                tprojSkill.updateDate = getBaseDt();
                tprojSkill.sklId = (Integer)skillMap.get("skillId");
                // スキルリストに追加
                tprojSkillList.add(tprojSkill);
              }
            }
            // スキル管理マスタに登録されてない場合
            if (tprojSkill.sklId == null) {
              otherSkill += skills[i];
              otherSkill += "/";
            }
          }

          // その他の言語の追加
          if (!empty(otherSkill)) {
            TProjSkill tprojSkill = new TProjSkill();
            // 案件スキルID,登録,更新時間を格納
            tprojSkill.prjSklId = tprojSkillSeq;
            tprojSkill.createDate = getBaseDt();
            tprojSkill.updateDate = getBaseDt();
            tprojSkill.sklId = -1;
            // その他の長さが20を超えていた場合、20まで切り取る
            if (otherSkill.length() > 20) {
              tprojSkill.other = otherSkill.substring(0, 20);
            } else {
              tprojSkill.other = otherSkill.substring(0, otherSkill.length() - 1);
            }
            // スキルリストに追加
            tprojSkillList.add(tprojSkill);
          }

          // 案件情報テーブルのシーケンス番号を取得
          int tprojInfoSeq = new AnkenRegisterDao().getPrjIdSeq();
          // 年度＋シーケンス番号を案件IDへ格納
          tprojInfo.prjId = Integer.parseInt(fiscalYear + tprojInfoSeq);

          int result = 0;
          // 案件スキルテーブルを登録処理
          for (TProjSkill tprojSkill : tprojSkillList) {
            result += new AnkenRegisterDao().prjSkillEntry(tprojSkill);
          }
          // 案件情報テーブル登録処理
          int kekka = new AnkenRegisterDao().prjEntry(tprojInfo);

          // 成功判定
          if (result != 0 && kekka != 0) {
            errorList[SUCCESS]++;
          }
        }
      } catch (NumberFormatException e) {
        e.printStackTrace();
      } catch (CSVValidationResultRuntimeException e) {
        e.printStackTrace();
      } catch (CSVFormatException e) {
        // 項目数エラー
        errorList[ITEMS_ERROR]++;
      }


    } catch (FileNotFoundException e) {
      // TODO 自動生成された catch ブロック
      e.printStackTrace();
    } catch (UnsupportedEncodingException e1) {
      // TODO 自動生成された catch ブロック
      e1.printStackTrace();
    }

    String error = "登録成功：" + errorList[SUCCESS] + "件\n"
            + "項目数エラー:" + errorList[ITEMS_ERROR] + "件\n"
            + "未入力エラー:" + errorList[REQUIRED_ERROR] + "件\n"
            + "最大文字長エラー:" + errorList[MAXLENGTH_ERROR] + "件\n"
            + "データ形式エラー:" + errorList[DATE_ERROR] + "件\n"
            + "マスタ存在エラー:" + errorList[MASTER_ERROR] + "件\n";

    return error;
  }

  /**
   * 案件情報テーブルの初期値を格納します.
   *
   * @return 案件情報テーブル
   */
  public TProjInfo getTProjInfo() {
    TProjInfo tprojInfo = new TProjInfo();
    tprojInfo.longTermFlg = false;
    tprojInfo.sameDayFlg = false;
    tprojInfo.anyTimeFlg = false;
    tprojInfo.extentionFlg = false;
    tprojInfo.delFlg = false;
    tprojInfo.createDate = getBaseDt();
    tprojInfo.updateDate = getBaseDt();

    return tprojInfo;
  }
}
