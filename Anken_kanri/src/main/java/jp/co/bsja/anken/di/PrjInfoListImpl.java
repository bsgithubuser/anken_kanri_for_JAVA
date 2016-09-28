package jp.co.bsja.anken.di;

import java.util.ArrayList;
import java.util.List;

import jp.co.bsja.anken.dao.PrjInfoListDao;

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
//  public List<SkillMasterForm> findSkillInfo() {
//    PrjInfoListDao dao = new PrjInfoListDao();
//    List<BeanMap> list = dao.findSkillInfo();
//    List<SkillMasterForm> formList = new ArrayList<>();
//    for (BeanMap map : list) {
//     // SkillMasterFormコミット後実装する
//    }
//    return formList;
//  }
}
