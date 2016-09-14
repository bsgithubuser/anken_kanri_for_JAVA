package jp.co.bsja.anken.dao;

import java.sql.Timestamp;
import java.util.List;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.beans.util.BeanMap;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.util.tiger.Maps;

public class Dao {
  public JdbcManager jdbcManager = SingletonS2Container.getComponent("jdbcManager");
  public static final String PATH = "data/";

  public static final String CMPN_ID_SEQ = "CMPN_ID_SEQ";
  public static final String PRJ_ID_SEQ = "PRJ_ID_SEQ";
  public static final String PRJ_SKILL_ID_SEQ = "PRJ_SKILL_ID_SEQ";
  public static final String SKILL_ID_SEQ = "SKILL_ID_SEQ";
  public static final String USERS_ID_SEQ = "USERS_ID_SEQ";

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
  public int getAnkenIdSeq() {
    String seq = jdbcManager.selectBySql(String.class,
        "SELECT nextval('prj_id_seq')FROM T_PROJ_INFO").getSingleResult();
    int ankenId = Integer.parseInt(seq);
    return ankenId;
  }

  /**
   * 案件スキルID採番用シーケンス番号取得 .
   *
   * @return 次のシーケンス番号
   */
  public int getAnkenSkillIdSeq() {
    String seq = jdbcManager.selectBySql(String.class,
        "SELECT nextval('prj_skill_id_seq')FROM T_PROJ_INFO").getSingleResult();
    int ankenId = Integer.parseInt(seq);
    return ankenId;
  }

  /**
   * 担当者ID採番用シーケンス番号取得 .
   *
   * @return 次のシーケンス番号
   */
  public int getUserIdSeq() {
    String seq = jdbcManager.selectBySql(String.class,
        "SELECT nextval('users_id_seq')FROM T_PROJ_INFO").getSingleResult();
    int ankenId = Integer.parseInt(seq);
    return ankenId;
  }

  /**
   * 会社ID採番用シーケンス番号取得 .
   *
   * @return 次のシーケンス番号
   */
  public int getCmpnIdSeq() {
    String seq = jdbcManager.selectBySql(String.class,
        "SELECT nextval('cmpn_id_seq')FROM T_PROJ_INFO").getSingleResult();
    int ankenId = Integer.parseInt(seq);
    return ankenId;
  }

  /**
   * スキルID採番用シーケンス番号取得 .
   *
   * @return 次のシーケンス番号
   */
  public int getSkillIdSeq() {
    String seq = jdbcManager.selectBySql(String.class,
        "SELECT nextval('skill_id_seq')FROM T_PROJ_INFO").getSingleResult();
    int ankenId = Integer.parseInt(seq);
    return ankenId;
  }

  public List<BeanMap> allseach(String formName) {
    return jdbcManager.selectBySql(BeanMap.class,"SELECT * FROM " + formName ).getResultList();
  }

  /**
   * 存在チェックSQL .
   *
   * @fromName fromName テーブル名
   * @idName idName カラム名
   * @params params パラメータ
   * @return 次のシーケンス番号
   */
  public int presenceCheck(String fromName,String idName,int params) {
    return jdbcManager.selectBySql(
        int.class,"SELECT COUNT(1) FROM " + fromName + " WHERE " + idName + " = " + params )
        .getSingleResult();
  }

  /**
   * 排他制御SQL .
   *
   * @param fromName テーブル名
   * @param idName カラム名
   * @param id ID番号
   * @param time 更新日時
   * @return 他のユーザーにより変更されていた場合1を返す
   */
  public int lock(String fromName,String idName,String id,Timestamp time) {
    return jdbcManager.selectBySql(
        int.class,"SELECT COUNT(1) FROM " + fromName + " WHERE "
    + idName + " = " + id + "UPDATE_DATE <> " + time).getSingleResult();
  }

}
