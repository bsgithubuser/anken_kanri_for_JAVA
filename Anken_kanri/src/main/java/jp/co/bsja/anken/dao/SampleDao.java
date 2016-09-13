package jp.co.bsja.anken.dao;

import java.util.List;

import jp.co.bsja.anken.entity.MUsers;

import org.seasar.extension.jdbc.AutoSelect;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.container.SingletonS2Container;

public class SampleDao {
  JdbcManager jdbcManager = SingletonS2Container.getComponent("jdbcManager");

  /**
   * ユーザ情報リストを取得します .
   *
   * @param userName ユーザ名
   * @return ユーザ情報リスト
   */
  public List<MUsers> findUsers(String userName) {
    AutoSelect<MUsers> as = jdbcManager.from(MUsers.class).where("user_name", userName);
    List<MUsers> ret = as.getResultList();
    return null;
  }
}

