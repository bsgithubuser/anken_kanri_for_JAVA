package jp.co.bsja.anken.dao;

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
}
