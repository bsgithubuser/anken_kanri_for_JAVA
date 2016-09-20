package jp.co.bsja.anken.form;

import org.seasar.struts.annotation.Arg;
import org.seasar.struts.annotation.Msg;
import org.seasar.struts.annotation.Required;

public class LoginForm {

  /** 担当者ID .  */
  @Required(msg = @Msg(key = "MSG_E00001"),arg0 = @Arg(key = "担当者ID", resource = false))
  public String userId;

  /** パスワード . */
  @Required(msg = @Msg(key = "MSG_E00001"),arg0 = @Arg(key = "パスワード", resource = false))
  public String password;

  /** エラーメッセージ . */
  public String errorMessage;

  /** int型担当者ID . */
  public int asIntUserId;
}
