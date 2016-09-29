package jp.co.bsja.anken.di;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jp.co.bsja.anken.dao.PrjInfoListDao;
import jp.co.bsja.anken.form.PrjInfoListForm;
import jp.co.bsja.anken.form.SkillMasterForm;

import org.seasar.framework.beans.util.BeanMap;

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
   * @return nameList スキル情報マスタ一覧
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
  public List<PrjInfoListForm> simpleSearch(PrjInfoListForm prjInfoListForm) {
    PrjInfoListDao dao = new PrjInfoListDao();
    List<BeanMap> list = dao.simpleSearch(prjInfoListForm);
    List<PrjInfoListForm> infoList = new ArrayList<>();
    for (BeanMap map : list) {
      prjInfoListForm.prjId = String.valueOf(map.get("prjId"));
      prjInfoListForm.prjName = (String)map.get("skillName");
      prjInfoListForm.cmpId = (String) map.get("cmpId");
      prjInfoListForm.cmpName = (String) map.get("cmpName");
      prjInfoListForm.userName = (String) map.get("userName");
      prjInfoListForm.genDate = (String) map.get("genDate");
      prjInfoListForm.periStDt = (String) map.get("periStDt");
      prjInfoListForm.periEnDt = (String) map.get("periEnDt");
      prjInfoListForm.compName = (String) map.get("compName");
      prjInfoListForm.periFlg = (String) map.get("periFlg");
      prjInfoListForm.skillName = (String) map.get("skillName");
      prjInfoListForm.exteFlg = (String) map.get("exteFlg");

      infoList.add(prjInfoListForm);
    }
    return infoList;
  }
}
