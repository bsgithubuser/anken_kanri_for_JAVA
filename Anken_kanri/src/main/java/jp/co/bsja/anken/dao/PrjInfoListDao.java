package jp.co.bsja.anken.dao;

import static jp.co.bsja.anken.common.CommonFunction.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.bsja.anken.entity.TProjInfo;
import jp.co.bsja.anken.form.PrjInfoListForm;

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

  /**
   * スキルIDから、案件スキルIDを取得します .
   * @param prjInfoListForm
   *
   * @return list 案件スキルID一覧
   */
  public List<Integer> searchPrjSklId(PrjInfoListForm prjInfoListForm) {
    BeanMap beanMap = new BeanMap();
    String sqlFilePath = "data/findPrjSklId";

  //検索用にスキルIDをint型で格納する
    List<Integer> skillIdList = null;
    if (!empty(prjInfoListForm.skillId)) {
      skillIdList = new ArrayList<Integer>();
      Iterator<String> itr = prjInfoListForm.skillId.iterator();
      while (itr.hasNext()) {
        int id = Integer.parseInt(itr.next());
        skillIdList.add(id);
      }
    }

    beanMap.put("skillId", skillIdList);

    List<Integer> list =
        jdbcManager.selectBySqlFile(Integer.class, sqlFilePath, beanMap)
        .getResultList();

    return list;
  }

  /**
   * 選択した項目で検索を行い、案件情報を取得します。 .
   * @param prjInfoListForm
   * @param prjSklId
   *
   * @return list 案件情報一覧
   */
  public List<BeanMap> search(PrjInfoListForm prjInfoListForm,List<Integer> prjSklId) {
    BeanMap beanMap = new BeanMap();
    String userName = prjInfoListForm.userNameList.get(0);

    String sqlFilePath;
    if (prjInfoListForm.kindOfSerch.equals("simple")) {
      sqlFilePath = "data/simpleSearch";
    } else {
      sqlFilePath = "data/detailSearch";

      //延長の条件を指定するか
      String extFlg = prjInfoListForm.exteFlg;
      Boolean extend = null;
      if (!extFlg.equals("none")) {
        extend = Boolean.valueOf( extFlg );
      }

      beanMap.put("lngTrmFlg", prjInfoListForm.lngTrmFlg);
      beanMap.put("smDyFlg", prjInfoListForm.smDyFlg);
      beanMap.put("anyTmFlg", prjInfoListForm.anyTmFlg);
      beanMap.put("searchExt", extFlg);
      beanMap.put("exteFlg", extend);
      beanMap.put("overview", prjInfoListForm.serchOverview);
      beanMap.put("prjSklId", prjSklId);
      beanMap.put("prjSklIdCnt", prjSklId.size());
    }

    beanMap.put("userName", userName);
    beanMap.put("periStDt", prjInfoListForm.periStDt);
    beanMap.put("periEnDt", prjInfoListForm.periEnDt);
    beanMap.put("compName", prjInfoListForm.compName);

    List<BeanMap> list =
        jdbcManager.selectBySqlFile(BeanMap.class, sqlFilePath, beanMap)
        .getResultList();

    return list;
  }

  /**
   * IDから選択された案件情報を取得します .
   * @param prjInfoListForm
   *
   * @return 選択した案件情報
   */
  public BeanMap findSelectedPrj(String sql) {
    return jdbcManager.selectBySql(BeanMap.class, sql).getSingleResult();
  }

  /**
   * 選択した項目を論理削除します .
   * @param TProjInfo
   *
   * @return 論理削除件数
   */
  public int prjInfoUpdate(TProjInfo entity) {
    return jdbcManager.update(entity).execute();
  }


}
