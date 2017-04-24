package jp.co.bsja.anken.di;

import static jp.co.bsja.anken.common.CommonFunction.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jp.co.bsja.anken.dao.PrjInfoListDao;
import jp.co.bsja.anken.dao.SkillMasterDao;
import jp.co.bsja.anken.dto.SessionDto;
import jp.co.bsja.anken.entity.TProjInfo;
import jp.co.bsja.anken.form.AnkenRegisterForm;
import jp.co.bsja.anken.form.PrjInfoListForm;
import jp.co.bsja.anken.form.SkillMasterForm;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.util.BeanMap;
import org.seasar.struts.util.ActionMessagesUtil;
import org.seasar.struts.util.RequestUtil;

public class PrjInfoListImpl implements PrjInfoListInterface {
  public static final String UNSET_ITEM = "未設定";
  public static final String LONG_TERM = "長期";
  public static final String SAME_DAY  = "即日";
  public static final String ANY_TIME  = "随時";
  public static final String BR = System.getProperty("line.separator");

  /**
   * 担当者マスタの担当者名全てを取得します。 .
   *
   * @return nameList 担当者名一覧
   */
  public List<String> findMUsersName() {
    PrjInfoListDao dao = new PrjInfoListDao();
    List<BeanMap> list = dao.findMusersName();
    List<String> nameList = new ArrayList<>();
    //担当者名プルダウン先頭に「全検索」項目の追加
    nameList.add("全検索");
    for (BeanMap map : list) {
      nameList.add((String)map.get("userName"));
    }
    return nameList;
  }

  /**
   * スキルマスタ情報全てを取得します。 .
   *
   * @return formList スキル情報マスタ一覧
   */
  public List<SkillMasterForm> findSkillInfo() {
//    PrjInfoListDao dao = new PrjInfoListDao();
    SkillMasterDao skillDao = new SkillMasterDao();
//    List<BeanMap> list = dao.findSkillInfo();
    List<BeanMap> list = skillDao.findAllSkill("M_SKILL", "skill_number");
    List<SkillMasterForm> formList = new ArrayList<>();
    for (BeanMap map : list) {
      SkillMasterForm skillMasterForm = new SkillMasterForm();
      skillMasterForm.skillId = String.valueOf(map.get("skillId"));
      skillMasterForm.skillName = (String)map.get("skillName");
      skillMasterForm.createDate = (Timestamp) map.get("createDate");
      skillMasterForm.updateDate = (Timestamp) map.get("updateDate");

      formList.add(skillMasterForm);
    }
    return formList;
  }

  /**
   * 選択した項目で検索を行い、案件情報を取得します。 .
   *
   * @return infoList 案件情報一覧
   */
  @Override
  public List<PrjInfoListForm> search(PrjInfoListForm prjInfoListForm, SessionDto sessionDto) {
    PrjInfoListDao dao = new PrjInfoListDao();

    //検索条件保持
    pushSearchCondition(prjInfoListForm, sessionDto);

    //日付を比較する
    Boolean compareDate = null;
    if (!empty(prjInfoListForm.periStDt) && !empty(prjInfoListForm.periEnDt)) {
      Timestamp fromDate = prjInfoListForm.periStDt;
      Timestamp toDate = prjInfoListForm.periEnDt;
      compareDate = fromDate.before(toDate);
    } else {
      compareDate = true;
    }

    List<PrjInfoListForm> infoList = new ArrayList<>();

    if (compareDate) {
      //スキルIDから案件スキルIDを取得する
      List<Integer> prjSklId = dao.searchPrjSklId(prjInfoListForm);

      if (prjSklId.size() != 0) {
        List<BeanMap> list = dao.search(prjInfoListForm, prjSklId);
        List<BeanMap> prjList = integrateIdentityPrj(list);

        //印刷用に検索結果を保持
        sessionDto.searchResultList = prjList;

        for (BeanMap map : prjList) {
          PrjInfoListForm form = new PrjInfoListForm();

          //発生日をString型でフォーマットする
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
          String formattedGenData = sdf.format(map.get("genDate"));

          //概要 一行のみ一覧で表示、一行に表示する文字数を最大12文字に制限
          //改行で区切った概要
          String[] split = ((String) map.get("orverview")).split("\r\n|[\n\r\u2028\u2029\u0085]");

          //一行目
          String oneLineOrverview = split[0];
          //概要が複数行の場合
          if (split.length > 1) {
            //一行目が13文字以上のとき、13文字以上を切り捨て
            if (oneLineOrverview.length() > 12) {
              oneLineOrverview = oneLineOrverview.substring(0, 12);
            }
            oneLineOrverview = oneLineOrverview + "…";
          } else {
            //概要が単行の場合
            if (oneLineOrverview.length() > 12) {
              oneLineOrverview = oneLineOrverview.substring(0, 12) + "…";
            }
          }

          //ポップアップ表示用に改行を含んだ文字列を作る
          String originalOrverview = null;
          for (String oneLine: split) {
            if (empty(originalOrverview)) {
              originalOrverview = oneLine + BR;
            } else {
              originalOrverview += oneLine + BR;
            }
          }

          form.prjId = String.valueOf(map.get("prjId"));
          form.prjName = (String)map.get("prjName");
          form.cmpName = (String) map.get("cmpnName");
          form.userName = (String) map.get("userName");
          form.genDate = formattedGenData;
          form.prjPeriod = displyPriodFormat(map);
          map.put("printPriod", displyPriodFormat(map));
          form.skillName = (String) map.get("skillName");
          form.overview = originalOrverview;
          form.displayOverview = oneLineOrverview;
          form.updateDate = (Timestamp) map.get("updateDate");

          infoList.add(form);
        }
      }
    } else {
      ActionMessages errors = new ActionMessages();
      errors.add(ActionMessages.GLOBAL_MESSAGE,
                          new ActionMessage("MSG_E00014", "発生日"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
    }

    return infoList;
  }

  /**
   * 案件情報一覧用の期間の表示内容を設定します .
   *
   * @return displyPriod 表示用期間
   */
  public String displyPriodFormat(BeanMap map) {
    String displyPriod = "";
    String periFrom = null;
    String periTo = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    if (!empty(map.get("periFrom"))) {
      periFrom = sdf.format(map.get("periFrom"));
    }
    if (!empty(map.get("periTo"))) {
      periTo = sdf.format(map.get("periTo"));
    }

        // periFromとperiTo両方に値があれば、それを表示する
    if (!empty(periFrom) && !empty(periTo)) {
      displyPriod = formatFromTo(periFrom,periTo);
    } else {
       // periFromとperiTo両方とも未入力
      if (empty(periFrom) && empty(periTo)) {
                // フラグが全てFALSEの場合
        if (!(boolean) map.get("longTermFlg")
                        && !(boolean) map.get("anyTimeFlg")
                        && !(boolean) map.get("sameDayFlg")) {
          displyPriod = formatFromTo(UNSET_ITEM,UNSET_ITEM);
        } else {
          if ((boolean) map.get("longTermFlg")) {
            if ((boolean) map.get("sameDayFlg")) {
              return formatFromTo(SAME_DAY,LONG_TERM);
            }
            if ((boolean) map.get("anyTimeFlg")) {
              return formatFromTo(ANY_TIME,LONG_TERM);
            }
            return LONG_TERM;
          } else if ((boolean) map.get("sameDayFlg")) {
            return SAME_DAY;
          } else if ((boolean) map.get("anyTimeFlg")) {
            return ANY_TIME;
          }
        }
      } else {
        // periFromとperiToいずれかに値がある場合
        if (!empty(periFrom)) {
          // Fromのみ入力あり
          if ((boolean) map.get("longTermFlg")) {
            // 長期フラグが true
            displyPriod = formatFromTo(periFrom,LONG_TERM);
          } else {
            displyPriod = formatFromTo(periFrom,UNSET_ITEM);
          }
        } else {
          // Toのみ入力あり
          if ((boolean) map.get("sameDayFlg")) {
            // 随時フラグが true
            displyPriod = formatFromTo(SAME_DAY,periTo);
          } else if ((boolean) map.get("anyTimeFlg")) {
            // 即日フラグが true
            displyPriod = formatFromTo(ANY_TIME,periTo);
          } else {
            // 随時 即日フラグが false
            displyPriod = formatFromTo(UNSET_ITEM,periTo);
          }
        }
      }
    }
    return displyPriod;
  }

    /**
     * 案件情報一覧用の期間をFrom～Toに整形します .
     *
     * @return formatFromTo 表示用期間
     */
  public String formatFromTo(String from, String to) {
    String makeFromTo = "";
    if (empty(from) && empty(to)) {
      makeFromTo = UNSET_ITEM + "～" + BR + UNSET_ITEM;
      return makeFromTo;
    }
    makeFromTo = from + "～" + BR + to;
    return makeFromTo;
  }

  /**
   * IDが同じ案件情報を統合します .
   *
   * @return list 統合後案件情報一覧
   */
  public List<BeanMap> integrateIdentityPrj(List<BeanMap> list){
    for (int i = 0; i < list.size(); i++) {
      BeanMap originColumn = list.get(i);
      String originPrjId = String.valueOf(originColumn.get("prjId"));

      for (int j = 0; j < list.size(); j++) {
        // originColumn 以外の列
        if (i != j) {
          BeanMap column = list.get(j);
          String prjId = String.valueOf(column.get("prjId"));

          //案件情報IDの重複があった場合
          if (originPrjId.equals(prjId)) {
            //改行
            String BR = System.getProperty("line.separator");

            //重複している列のスキル名を取り出し、originColumnのスキル名に追加する
            String originSkill = String.valueOf(originColumn.get("skillName"));
            String skillName = String.valueOf(column.get("skillName"));
            String combined = originSkill + BR + skillName;
            originColumn.put("skillName", combined);

            //重複していたほうのcolumnをlistから削除する
            list.remove(j);
          }
        }
      }
    }
    return list;
  }

  /**
   * 渡された案件情報IDをankenRegisterFormに格納します .
   *
   */
  @Override
  public void pullIdToARF(PrjInfoListForm prjInfoListForm, AnkenRegisterForm ankenRegisterForm) {
    //案件IDをankenRegisterForm.idに格納する
    int id = Integer.parseInt(prjInfoListForm.prjId);
    ankenRegisterForm.id = id;
  }

  /**
   * 選択された案件情報を論理削除します .
   *
   */
  @Override
  public void delete(PrjInfoListForm prjInfoListForm, SessionDto sessionDto) {
    TProjInfo entity = new TProjInfo();
    PrjInfoListDao dao = new PrjInfoListDao();

    //削除押下時点の更新日時
    Timestamp upDateDate = Timestamp.valueOf(prjInfoListForm.updtDate);

    //案件IDを元に選択された案件情報を取得する
    String sql = "SELECT * FROM t_proj_info WHERE prj_id = " + prjInfoListForm.prjId;
    BeanMap willDelPrj = dao.findSelectedPrj(sql);

    Boolean delFlg = (Boolean) willDelPrj.get("delFlg");
    Timestamp updtDt = (Timestamp) willDelPrj.get("updateDate");

    int result = upDateDate.compareTo(updtDt);

    //delFlgがtrue/result!=0ならエラーを表示する
    if (delFlg || result != 0) {
      ActionMessages errors = new ActionMessages();
      errors.add(ActionMessages.GLOBAL_MESSAGE,
                          new ActionMessage("MSG_E00009", prjInfoListForm.prjName));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
    } else {
      Timestamp delTimestamp = getBaseDt();

      entity.prjId = (Integer) willDelPrj.get("prjId");
      entity.prjName = (String) willDelPrj.get("prjName");
      entity.userId = (Integer) willDelPrj.get("userId");
      entity.cmpnId = (Integer) willDelPrj.get("cmpnId");
      entity.prjSklId = (Integer) willDelPrj.get("prjSklId");
      entity.genDate = convertTsIntoDt(willDelPrj.get("genDate"));
      entity.periFrom = convertTsIntoDt(willDelPrj.get("periFrom"));
      entity.periTo = convertTsIntoDt(willDelPrj.get("periTo"));
      entity.longTermFlg = (Boolean) willDelPrj.get("longTermFlg");
      entity.sameDayFlg = (Boolean) willDelPrj.get("sameDayFlg");
      entity.anyTimeFlg = (Boolean) willDelPrj.get("anyTimeFlg");
      entity.extentionFlg = (Boolean) willDelPrj.get("extentionFlg");
      entity.orverview = (String) willDelPrj.get("orverview");
      entity.other = (String) willDelPrj.get("other");
      entity.delFlg = true;
      entity.createDate = (Timestamp) willDelPrj.get("createDate");
      entity.updateDate = delTimestamp;

      //削除フラグ更新処理
      dao.prjInfoUpdate(entity);
    }

    //保持した検索条件を取り出す
    pullSearchCondition(prjInfoListForm, sessionDto);
  }

  /**
   * 印刷画面で使用する情報をSessionDtoに格納します .
   * 新規画面で印刷画面を開くと、ここで印刷Formに値を入れても初期化されてしまうため .
   *
   */
  @Override
  public void holdPrintInfo(
      PrjInfoListForm prjInfoListForm, SessionDto sessionDto, Boolean bulkFlag) {

    prjInfoListForm.isDisplayPrintPage = true;
    sessionDto.printAnknIds = prjInfoListForm.printAnknIds;
    sessionDto.bulkFlag = bulkFlag;

    //保持した検索条件を取り出す
    pullSearchCondition(prjInfoListForm, sessionDto);
  }

  /**
   * object型の日付をDate型に変換します .
   *
   *@return cenvertedDate Date型の日付
   */
  @Override
  public Date convertTsIntoDt(Object date) {
    if (!empty(date)) {
      Timestamp dateTs = (Timestamp) date;
      Date cenvertedDate = new Date(dateTs.getTime());
      return cenvertedDate;
    } else {
      return null;
    }
  }

  /**
   * 検索条件を保持します .
   *
   */
  @Override
  public void pushSearchCondition(PrjInfoListForm prjInfoListForm, SessionDto sessionDto) {
    sessionDto.compName = prjInfoListForm.compName;
    sessionDto.userNameList = prjInfoListForm.userNameList;
    sessionDto.prjSkillId = prjInfoListForm.skillId;
    sessionDto.periStDtDummy = prjInfoListForm.periStDtDummy;
    sessionDto.periStDt = prjInfoListForm.periStDt;
    sessionDto.periEnDtDummy = prjInfoListForm.periEnDtDummy;
    sessionDto.periEnDt = prjInfoListForm.periEnDt;
    sessionDto.lngTrmFlg = prjInfoListForm.lngTrmFlg;
    sessionDto.smDyFlg = prjInfoListForm.smDyFlg;
    sessionDto.anyTmFlg = prjInfoListForm.anyTmFlg;
    sessionDto.serchOverview = prjInfoListForm.serchOverview;
    sessionDto.selectedSkills = prjInfoListForm.selectedSkills;
    sessionDto.exteFlg = prjInfoListForm.exteFlg;
    sessionDto.kindOfSerch = prjInfoListForm.kindOfSerch;
    sessionDto.tabName = prjInfoListForm.tabName;
  }

  /**
   * 保持した検索条件を取り出します .
   *
   */
  @Override
  public void pullSearchCondition(PrjInfoListForm prjInfoListForm, SessionDto sessionDto) {
    prjInfoListForm.compName = sessionDto.compName;
    prjInfoListForm.userNameList = sessionDto.userNameList;
    prjInfoListForm.skillId = sessionDto.prjSkillId;
    prjInfoListForm.periStDtDummy = sessionDto.periStDtDummy;
    prjInfoListForm.periStDt = sessionDto.periStDt;
    prjInfoListForm.periEnDtDummy = sessionDto.periEnDtDummy;
    prjInfoListForm.periEnDt = sessionDto.periEnDt;
    prjInfoListForm.lngTrmFlg = sessionDto.lngTrmFlg;
    prjInfoListForm.smDyFlg = sessionDto.smDyFlg;
    prjInfoListForm.anyTmFlg = sessionDto.anyTmFlg;
    prjInfoListForm.serchOverview = sessionDto.serchOverview;
    prjInfoListForm.selectedSkills = sessionDto.selectedSkills;
    prjInfoListForm.exteFlg = sessionDto.exteFlg;
    prjInfoListForm.kindOfSerch = sessionDto.kindOfSerch;
    prjInfoListForm.tabName = sessionDto.tabName;
  }
}
