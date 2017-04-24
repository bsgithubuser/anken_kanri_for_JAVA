package jp.co.bsja.anken.dao;

import java.sql.Timestamp;
import java.util.List;

import jp.co.bsja.anken.entity.MSkill;

import org.seasar.framework.beans.util.BeanMap;

public class SkillMasterDao extends Dao {

  /**
   * 検索条件指定なし全件検索SQL.
   *
   * @return 検索結果リスト
   */
  public List<BeanMap> findAllSkill() {
    return jdbcManager.selectBySql(BeanMap.class,
        "SELECT * FROM M_SKILL ORDER BY skill_id asc").getResultList();
  }


  /**
   * 全件検索して引数に指定した列名の昇順で並び替えるSQL.
   *
   * @columnName columnName カラム名
   * @return 検索結果リスト
   */
  public List<BeanMap> SearchAndSortAllSkill(String columnName) {
    return jdbcManager.selectBySql(BeanMap.class,
        "SELECT * FROM M_SKILL ORDER BY " + columnName + " asc").getResultList();
  }

  /**
   * 新規登録時重複チェックSQL .
   *
   * @params params 入力したスキル名
   * @return 1以上で重複
   */
  public int signUpCheckOverlap(String columnName, String params) {
    return jdbcManager.selectBySql(
            Integer.class,
            "SELECT count(*) FROM M_SKILL WHERE LOWER('" + params + "') = LOWER(skill_name)")
        .getSingleResult();
  }

  /**
   * 編集時重複チェックSQL .
   *
   * @params1 params1 入力したスキル名
   * @params2 params2 編集するID
   * @return 1以上で重複
   */
  public int editCheckOverlap(String params1, String params2) {
    return jdbcManager.selectBySql(
            Integer.class,
            "SELECT count(*) FROM M_SKILL"
            + " WHERE LOWER('" + params1 + "') = LOWER(skill_name) AND skill_id != " + params2)
        .getSingleResult();
  }

  /**
   * 更新データ取得SQL .
   *
   * @tableName sql 実行するSQL
   * @return 該当データ1件
   */
  public BeanMap fetchData(String sql) {
    return jdbcManager.selectBySql(BeanMap.class, sql).getSingleResult();
  }

  /**
   * データ更新日時取得SQL .
   *
   * @tableName sql 実行するSQL
   * @return 該当データ1件
   */
  public Timestamp fetchUpdateDate(String sql) {
    return jdbcManager.selectBySql(Timestamp.class, sql).getSingleResult();
  }

  /**
   * データ登録SQL .
   *
   * @param entity 登録するスキル情報
   * @return 登録処理成功件数
   */
  public int insert(MSkill entity) {
    return jdbcManager.insert(entity).execute();
  }

  /**
   * データ更新SQL .
   *
   * @param entity 更新するスキル情報
   * @return 更新処理成功件数
   */
  public int uptate(MSkill entity) {
    return jdbcManager.update(entity).execute();

  }

  /**
   * データ削除SQL .
   *
   * @param entity 削除するスキル情報
   * @return 削除処理成功件数
   */
  public int delete(MSkill entity) {
    return jdbcManager.delete(entity).execute();
  }
}

