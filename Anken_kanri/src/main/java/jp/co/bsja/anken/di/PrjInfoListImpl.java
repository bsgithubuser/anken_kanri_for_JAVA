package jp.co.bsja.anken.di;

import static jp.co.bsja.anken.common.CommonFunction.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jp.co.bsja.anken.dao.PrjInfoListDao;
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
  /**
   * 担当者マスタの担当者名全てを取得します。 .
   *
   * @return nameList 担当者名一覧
   */
  public List<String> findMUsersName() {
    PrjInfoListDao dao = new PrjInfoListDao();
    List<BeanMap> list = dao.findMusersName();
    List<String> nameList = new ArrayList<>();
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
    PrjInfoListDao dao = new PrjInfoListDao();
    List<BeanMap> list = dao.findSkillInfo();
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

        for (BeanMap map : prjList) {
          PrjInfoListForm form = new PrjInfoListForm();

          //発生日をString型でフォーマットする
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
          String formattedGenData = sdf.format(map.get("genDate"));

          //期間を出力用に整形する
          String prjPeriod = null;
          String periFrom = null;
          String periTo = null;

          if (!empty(map.get("periFrom"))) {
            periFrom = sdf.format(map.get("periFrom"));
          }
          if (!empty(map.get("periTo"))) {
            periTo = sdf.format(map.get("periTo"));
          }

          //改行
          String BR = System.getProperty("line.separator");

          //periFromとperiTo両方に値があれば、それを表示する
          if (!empty(periFrom) && !empty(periTo)){
            prjPeriod = periFrom + "～" + BR + periTo;
          } else if ((boolean) map.get("longTermFlg")) {
          //長期フラグが true のとき "periFrom～長期"又は"長期"
            if (!empty(periFrom)) {
              prjPeriod = periFrom + "～" + BR + "長期";
            } else {
              prjPeriod = "長期";
            }
          } else if ((boolean) map.get("anyTimeFlg")) {
          //随時フラグが true のとき "随時～periTo"又は"随時"
            if (!empty(periTo)) {
              prjPeriod = "随時" + BR + "～" + periTo;
            } else {
              prjPeriod = "随時";
            }
          } else if ((boolean) map.get("sameDayFlg")) {
          //即日フラグが true のとき "即日～periTo"又は"即日"
            if (!empty(periTo)) {
              prjPeriod = "即日" + BR + "～" + periTo;
            } else {
              prjPeriod = "即日";
            }
          }

          String extendFlg = "";
          //延長フラグが true のとき○を表示する
          if ((boolean) map.get("extentionFlg")) {
            extendFlg = "○";
          }

          form.prjId = String.valueOf(map.get("prjId"));
          form.prjName = (String)map.get("prjName");
          form.cmpName = (String) map.get("cmpnName");
          form.userName = (String) map.get("userName");
          form.genDate = formattedGenData;
          form.prjPeriod = prjPeriod;
          form.skillName = (String) map.get("skillName");
          form.extendFlg = extendFlg;
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
    sessionDto.overview = prjInfoListForm.overview;
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
    prjInfoListForm.overview = sessionDto.overview;
    prjInfoListForm.selectedSkills = sessionDto.selectedSkills;
    prjInfoListForm.exteFlg = sessionDto.exteFlg;
    prjInfoListForm.kindOfSerch = sessionDto.kindOfSerch;
    prjInfoListForm.tabName = sessionDto.tabName;
  }
}