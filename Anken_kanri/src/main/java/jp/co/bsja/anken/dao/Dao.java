package jp.co.bsja.anken.dao;

import static jp.co.bsja.anken.common.CommonFunction.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.beans.util.BeanMap;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.util.tiger.Maps;

public class Dao {
  public JdbcManager jdbcManager = SingletonS2Container.getComponent("jdbcManager");
  public static final String PATH = "data/";

  public static final String TABLE_M_CMPN = "M_CMPN";
  public static final String TABLE_M_SKILL = "M_SKILL";
  public static final String TABLE_M_USERS = "M_USERS";
  public static final String TABLE_T_PROJ_INFO = "T_PROJ_INFO";
  public static final String TABLE_T_PROJ_SKILL = "T_PROJ_SKILL";

  /**
   * SELECTを実行します .
   *
   * @param sql SQL文
   * @return BeanMapのリスト
   */
  public List<BeanMap> selectBySql(String sql) {
    return jdbcManager.selectBySql(BeanMap.class, sql).getResultList();
  }

  /**
   * 単一結果文字列を返却するSELECTを実行します .
   *
   * @param sql SQL文
   * @return 文字列
   */
  public String selectBySqlSingleString(String sql) {
    return jdbcManager.selectBySql(String.class, sql).getSingleResult();
  }

  /**
   * 指定したSQLファイルを使ってSQLを実行します .
   *
   * @param sqlFName SQLファイル名
   * @return BeanMapのリスト
   */
  public List<BeanMap> selectBySqlFile(String sqlFName) {
    return jdbcManager.selectBySqlFile(BeanMap.class, PATH + sqlFName).getResultList();
  }

  /**
   * 指定したSQLファイルを使ってSQLを実行します .
   *
   * @param sqlFName SQLファイル名
   * @param params パラメータ
   * @return BeanMapのリスト
   */
  public List<BeanMap> selectBySqlFile(String sqlFName, Maps<String, Object> params) {
    return jdbcManager.selectBySqlFile(BeanMap.class, PATH + sqlFName, params.$()).getResultList();
  }

  /**
   * 案件ID採番用シーケンスを取得する .
   *
   * @return BeanMapのリスト
   */
  public int getPrjIdSeq() {
    return jdbcManager.selectBySql(int.class,
        "SELECT nextval('PRJ_ID_SEQ')").getSingleResult();
  }

  /**
   * 案件スキルID採番用シーケンス番号取得 .
   *
   * @return 次のシーケンス番号
   */
  public int getPrjSkillIdSeq() {
    return jdbcManager.selectBySql(int.class,
        "SELECT nextval('PRJ_SKILL_ID_SEQ')").getSingleResult();
  }

  /**
   * 担当者ID採番用シーケンス番号取得 .
   *
   * @return 次のシーケンス番号
   */
  public int getUserIdSeq() {
    return jdbcManager.selectBySql(int.class,
        "SELECT nextval('USERS_ID_SEQ')").getSingleResult();
  }

  /**
   * 会社ID採番用シーケンス番号取得 .
   *
   * @return 次のシーケンス番号
   */
  public int getCmpnIdSeq() {
    return jdbcManager.selectBySql(int.class,
        "SELECT nextval('CMPN_ID_SEQ')").getSingleResult();
  }

  /**
   * スキルID採番用シーケンス番号取得 .
   *
   * @return 次のシーケンス番号
   */
  public int getSkillIdSeq() {
    return jdbcManager.selectBySql(int.class,
        "SELECT nextval('SKILL_ID_SEQ')").getSingleResult();
  }

  /**
   * 検索条件指定なし全件検索します.
   *
   * @param tableName テーブル名
   * @return 検索結果リスト
   */
  public List<BeanMap> selectAll(String tableName) {
    return jdbcManager.selectBySql(BeanMap.class, "SELECT * FROM " + tableName).getResultList();
  }

  /**
   * 存在チェックSQL 存在しなければnullを返却 .
   *
   * @fromName tableName テーブル名
   * @idName columnName カラム名
   * @params param パラメータ
   * @return 存在可否
   */
  public Integer existCheck(String tableName, String columnName, int param) {
    return jdbcManager.selectBySql(Integer.class,
        String.format("SELECT COUNT(1) FROM %s WHERE %s = %s", tableName, columnName, param))
        .getSingleResult();
  }

  /**
   * 排他制御SQL .
   *
   * @param tableName テーブル名
   * @param columnName カラム名
   * @param param パラメータ
   * @param time 更新日時
   * @return 他のユーザーにより変更されていた場合1を返す
   */
  public Integer hasLocked(String tableName, String columnName, String param, Timestamp time) {
    return jdbcManager.selectBySql(Integer.class,
          String.format("SELECT COUNT(1) FROM %s WHERE %s = %s AND UPDATE_DATE <> '%s'",
              tableName, columnName, param,
              new SimpleDateFormat(FORMAT_YMDHMSS_HYPEN).format(time)))
        .getSingleResult();
  }
}
