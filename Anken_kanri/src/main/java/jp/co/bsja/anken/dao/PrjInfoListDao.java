package jp.co.bsja.anken.dao;

import java.util.List;

import org.seasar.framework.beans.util.BeanMap;

public class PrjInfoListDao extends Dao {
  /**
   * 担当者マスタの担当者名全てを取得します。 .
   *
   * @return list 担当者名一覧
   */
  public List<BeanMap> findMusersName() {
    List<BeanMap> list = selectBySqlFile("findMusersName");
    return list;
  }

  /**
   * スキルマスタの情報全てを取得します。 .
   *
   * @return list スキルマスタ情報一覧
   */
  public List<BeanMap> findSkillInfo() {
    List<BeanMap> list = selectBySqlFile("findSkillInfo");
    return list;
  }

}
