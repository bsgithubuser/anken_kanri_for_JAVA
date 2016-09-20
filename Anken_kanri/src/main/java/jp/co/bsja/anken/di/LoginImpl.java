package jp.co.bsja.anken.di;

import jp.co.bsja.anken.dao.LoginDao;
import jp.co.bsja.anken.entity.MUsers;
import jp.co.bsja.anken.form.LoginForm;

public class LoginImpl implements Login{

  /**
   * ユーザー情報を取得します。
   *
   * @param loginForm .
   * @return ユーザー情報
   *
   */
  @Override
  public MUsers getUser(LoginForm loginForm) {
    LoginDao dao = new LoginDao();
    return dao.getUser(loginForm);
  }

  /**
   * ログイン状況をtrueに更新する。
   *
   * @param userList .
   *
   */
  @Override
  public void updateLoginStateToLogin(MUsers userList) {
    LoginDao dao = new LoginDao();
    dao.updateLoginStateToLogin(userList);
  }

  /**
   * ログイン状況をfalseに更新する。
   *
   * @param userId .
   *
   */
  @Override
  public void updateLoginStateToLogout(int userId) {
    LoginDao dao = new LoginDao();
    dao.updateLoginStateToLogout(userId);
  }
}