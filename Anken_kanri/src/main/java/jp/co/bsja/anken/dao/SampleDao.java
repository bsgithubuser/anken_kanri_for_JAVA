package jp.co.bsja.anken.dao;

import java.util.List;

import jp.co.bsja.anken.entity.MSkill;

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

  public List<MSkill> findUsers(String userName) {
    return (List<MSkill>) jdbcManager.from(MSkill.class).getResultList();

  }
}

