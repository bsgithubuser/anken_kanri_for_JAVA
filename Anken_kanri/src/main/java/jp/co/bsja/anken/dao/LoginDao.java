package jp.co.bsja.anken.dao;

import jp.co.bsja.anken.entity.MUsers;
import jp.co.bsja.anken.form.LoginForm;

public class LoginDao extends Dao {

  /**
   * テーブルMUsersからユーザー情報を取得する .
   *
   * @param loginForm .
   * @return ユーザー情報
   */
  public MUsers getUser(LoginForm loginForm) {
    MUsers musers = new MUsers();
    musers.userId = loginForm.asIntUserId;
    musers.password = loginForm.tempPassword;

    return jdbcManager.selectBySqlFile(MUsers.class,
        "data/findMUsersByUserIdAndPassword.sql",musers).getSingleResult();
  }

  /**
   * ログイン状況をログインに更新する .
   *
   * @param userList
   *
   */
  public void updateLoginStateToLogin(MUsers userList) {
    jdbcManager.updateBySqlFile("data/updateMUsersByLoginStateToLogin.sql",userList).execute();
  }

  /**
   * ログイン状況をログアウトに更新する .
   *
   * @param userId
   *
   */
  public void updateLoginStateToLogout(int userId) {
    jdbcManager.updateBySqlFile("data/updateMUsersByLoginStateToLogout.sql",userId).execute();
  }
}
