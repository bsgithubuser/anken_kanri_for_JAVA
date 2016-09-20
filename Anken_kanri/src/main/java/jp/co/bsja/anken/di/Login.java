package jp.co.bsja.anken.di;

import jp.co.bsja.anken.entity.MUsers;
import jp.co.bsja.anken.form.LoginForm;

public interface Login {
  public MUsers getUser(LoginForm loginForm);

  public void updateLoginStateToLogin(MUsers userList);

  public void updateLoginStateToLogout(int userId);
}
