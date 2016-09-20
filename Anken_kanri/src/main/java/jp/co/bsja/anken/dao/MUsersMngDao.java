package jp.co.bsja.anken.dao;

import static jp.co.bsja.anken.common.CommonFunction.*;
import static org.seasar.framework.util.tiger.Maps.*;

import java.sql.Timestamp;
import java.util.List;

import jp.co.bsja.anken.entity.MUsers;
import jp.co.bsja.anken.form.PersonalForm;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.framework.beans.util.BeanMap;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.util.tiger.Maps;
public class MUsersMngDao extends Dao {

  /**
   * 担当者全ての情報を取得します。 .
   *
   * @return list 担当者一覧情報
   */
  public List<BeanMap> findMusers() {
    List<BeanMap> list = selectBySqlFile("findMusers");
    return list;
  }

  /**
   *IDに紐付く担当者情報を取得します。 .
   *
   * @param form .
   * @return list 担当者情報
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
public List<BeanMap> findMuser(PersonalForm form) {
    Maps params = map("userId", form.userId);
    List<BeanMap> list = selectBySqlFile("findMuser", params);
    return list;
  }
  /**
   * 担当者マスタ管理に登録処理を行います。 .
   *
   * @param form .
   * @return 登録処理件数
   */
  public int entryUser(PersonalForm form) {
    MUsers musers = new MUsers();
    musers.userId = getUserIdSeq();
    musers.userName = form.userName;
    musers.admin = form.adminiRight;
    musers.password = form.passWord;
    musers.loginState = false;
    musers.createDate = getBaseDt();
    musers.updateDate = getBaseDt();
    //jdbcManager取得
    JdbcManager jdbcManager = SingletonS2Container.getComponent("jdbcManager");
    return jdbcManager.insert(musers).execute();

  }

  /**
   * 担当者マスタ管理に編集処理を行います。 .
   *
   * @param form .
   * @return updateCount 更新処理件数
   */
  public int updateUser(PersonalForm form) {
    //jdbcManager取得
    JdbcManager jdbcManager = SingletonS2Container.getComponent("jdbcManager");
    int updateCount = 0;
    if (form.passWord.length() == 8) {
      updateCount = jdbcManager.updateBySql("UPDATE M_USERS SET"
      + " USER_NAME = ?, PASSWORD = ?, ADMIN = ?, LOGIN_STATE = ?, UPDATE_DATE = ?"
      + " WHERE USER_ID = ?",
      String.class,
      String.class,
      String.class,
      boolean.class,
      Timestamp.class,
      Integer.class)
      .params(form.userName, form.passWord, form.adminiRight, false, getBaseDt(),
      form.userId).execute();
    } else {
      updateCount = jdbcManager.updateBySql("UPDATE M_USERS SET"
      + " USER_NAME = ?, ADMIN = ?, LOGIN_STATE = ?, UPDATE_DATE = ?"
      + " WHERE USER_ID = ?",
      String.class,
      String.class,
      boolean.class,
      Timestamp.class,
      Integer.class)
      .params(form.userName, form.adminiRight, false, getBaseDt(),
      form.userId).execute();
    }
    return updateCount;
  }
  /**
   * 担当者IDに紐付く案件情報件数を取得する。 .
   *
   * @param form .
   * @return 案件情報件数
   */
  public int checkPrjInfo(PersonalForm form) {
    return existCheck("T_PROJ_INFO","USER_ID", form.userId);
  }

  /**
   * 担当者IDに紐付くログイン状況を取得する。
   *
   * @param form .
   * @return mUsers.loginState  ログイン状況
   */
  public boolean loginCheck(PersonalForm form) {
    //jdbcManager取得
    JdbcManager jdbcManager = SingletonS2Container.getComponent("jdbcManager");
    MUsers musers = new MUsers();
    int userId = form.userId;
    musers = jdbcManager.selectBySqlFile(MUsers.class, PATH + "findLoginState", userId)
        .getSingleResult();
    return musers.loginState;
  }

  /**
   * 削除を実行する。 .
   *
   * @param form
   *
   */
  public void delete(PersonalForm form) {
    //jdbcManager取得
    JdbcManager jdbcManager = SingletonS2Container.getComponent("jdbcManager");
    MUsers musers = new MUsers();
    musers.userId = form.userId;
    jdbcManager.delete(musers).execute();
  }

}
