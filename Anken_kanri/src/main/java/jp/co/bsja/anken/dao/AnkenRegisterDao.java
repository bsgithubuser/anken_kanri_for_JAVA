package jp.co.bsja.anken.dao;

import java.util.List;

import jp.co.bsja.anken.entity.MCmpn;
import jp.co.bsja.anken.entity.TProjInfo;
import jp.co.bsja.anken.entity.TProjSkill;

import org.seasar.framework.beans.util.BeanMap;

public class AnkenRegisterDao extends Dao {

  /**
   *  編集初期表示.
   * @param id 案件ID
   * @return 案件情報
   */
  public TProjInfo initEdit(int id) {
    TProjInfo tprojInfo = jdbcManager
        .from(TProjInfo.class)
        .where("PRJ_ID = ?", id)
        .getSingleResult();
    return tprojInfo;

  }

  /**
   * 案件スキル登録.
   * @param entity 登録するデータ
   * @return 登録件数
   */

  public int prjSkillEntry(TProjSkill entity) {
    return jdbcManager.insert(entity).execute();
  }
  /**
   * 案件情報登録.
   * @param entity 登録するデータ
   * @return 登録件数
   */
  public int prjEntry(TProjInfo entity) {
    return jdbcManager.insert(entity).execute();
  }

  /**
   * 削除チェック.
   * @param param 案件ID
   * @return 論理削除済み件数
   */
  public int delCheck(int param) {
    return jdbcManager.selectBySql(Integer.class,
        String.format(
            "SELECT COUNT(1) FROM T_PROJ_INFO WHERE DEL_FLG = TURE AND PRJ_ID = %s", param))
            .getSingleResult();
  }

  /**
   * 案件スキル情報削除.
   * @param prjSklId 案件スキルID
   * @return 削除件数
   */
  public int delete(int prjSklId) {
    return jdbcManager
        .updateBySql(
            "DELETE FROM T_PROJ_SKILL WHERE PRJ_SKL_ID = ?",
            Integer.class)
            .params(prjSklId)
            .execute();
  }

  public int uptate(TProjInfo entity) {
    return jdbcManager.update(entity).execute();
  }

  /**
   * 会社ID取得.
   * @param param 会社名
   * @return 会社ID
   */
  public String cmpmCheck(String param) {

    MCmpn cmpn = jdbcManager
        .from(MCmpn.class)
        .where("CMPN_NAME = ?", param)
        .getSingleResult();

    String cmpnId = String.valueOf(cmpn.cmpnId);
    return cmpnId;

  }

  /**
   * 会社名検索.
   * @param id 会社ID
   * @return 会社名
   */
  public String cmpm(int id) {
    MCmpn cmpn = jdbcManager
        .from(MCmpn.class)
        .where("CMPN_ID = ?", id)
        .getSingleResult();

    return cmpn.cmpnName;
  }

  /**
   * 案件スキルテーブルのスキルID検索.
   * @param params 案件スキルID
   * @return 案件スキルリスト
   */
  public List<BeanMap> ankenSkill(int params) {
    String sql = "SELECT SKL_ID, OTHER FROM T_PROJ_SKILL WHERE PRJ_SKL_ID = %s";
    return jdbcManager.selectBySql(BeanMap.class,String.format(sql, params)).getResultList();
  }
}
