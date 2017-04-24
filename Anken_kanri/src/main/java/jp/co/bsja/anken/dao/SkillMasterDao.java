package jp.co.bsja.anken.dao;

import java.sql.Timestamp;
import java.util.List;

import jp.co.bsja.anken.entity.MSkill;

import org.seasar.framework.beans.util.BeanMap;

public class SkillMasterDao extends Dao {

	 /**
	   * 検索条件指定なし全件検索SQL.
	   *
	   * @param tableName テーブル名
	   * @return 検索結果リスト
	   */
	/*  public List<BeanMap> findAllSkill(String tableName) {
	    return jdbcManager.selectBySql(BeanMap.class,
	        "SELECT * FROM " + tableName + " ORDER BY skill_id asc").getResultList();
	  }
	*/

	  /**
	   * 検索条件指定なし全件検索SQL.
	   *
	   * @tableName tableName テーブル名
	   * @columnName columnName カラム名
	   * @return 検索結果リスト
	   */
	  public List<BeanMap> findAllSkill(String tableName, String columnName) {
	    return jdbcManager.selectBySql(BeanMap.class,
	        "SELECT * FROM " + tableName + " ORDER BY " + columnName + " asc").getResultList();
	  }

  /**
   * 重複チェックSQL .
   *
   * @tableName tableName テーブル名
   * @columnName columnName カラム名
   * @params params パラメータ
   * @return 1以上で重複
   */
  public int checkOverlap(String tableName, String columnName, String params) {
    return jdbcManager.selectBySql(
            Integer.class,
            "SELECT count(*) FROM " + tableName + " WHERE " + columnName + " = '" + params + "'")
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

