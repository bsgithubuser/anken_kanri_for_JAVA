package jp.co.bsja.anken.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import jp.co.bsja.anken.common.CommonFunction;
import jp.co.bsja.anken.di.Login;
import jp.co.bsja.anken.dto.SessionDto;
import jp.co.bsja.anken.entity.MUsers;
import jp.co.bsja.anken.form.LoginForm;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ActionMessagesUtil;

public class LoginAction extends CommonFunction{

  @ActionForm
  @Resource
  protected LoginForm loginForm;

  public SessionDto sessionDto;

  @Resource
  protected HttpServletRequest request;

  /**
   * ログイン画面(初期表示) .
   *
   * @return 遷移先JSP名
   */
  @Execute(validator = false)
  public String index() {
    return "login.jsp";
  }

  /**
   * ログインボタン押下時の動作 .
   *
   * @return 遷移先JSP名
   */
  @Execute(validator = true,input = "login.jsp")
  public String login() {

    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    Login login = (Login)container.getComponent("LoginImpl");

    //userIdに文字列が含まれていた場合、int型0に変換
    loginForm.asIntUserId = asInt(loginForm.userId,0);

    //パスワードをハッシュ化
    loginForm.tempPassword = toEncryptedHashValue("SHA-256", loginForm.password);

    //user情報取得
    MUsers userList = login.getUser(loginForm);

    //検索結果が存在しない場合、エラーメッセージを返す
    if (userList == null) {
      //ログイン失敗:loginへエラーを返す
      ActionMessages errors = new ActionMessages();
      errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("MSG_E00017"));
      ActionMessagesUtil.addErrors(request, errors);

      return "login.jsp";
    } else {
      //TODO: 要検討事項 現時点は機能落ちしています
      /*
      //ログイン状況がTRUEの場合、エラーメッセージを返す
      if (userList.loginState == true) {
        //ログイン失敗:loginへエラーを返す
        ActionMessages errors = new ActionMessages();
        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("MSG_E00002", userList.userId));
        ActionMessagesUtil.addErrors(request, errors);

        return "login.jsp";
      } else {
        //ログイン成功:セッションへ値を渡し、ログイン状況を更新後、menu画面へ遷移
        login.updateLoginStateToLogin(userList);
       */
      sessionDto.userId = String.valueOf(userList.userId);
      sessionDto.userName = userList.userName;
      sessionDto.password = userList.password;
      //sessionDto.loginState = true;
      sessionDto.admin = userList.admin;

      return "/menu";
    }
  }

  /**
   * ログアウトボタン押下時の動作 .
   *
   * @return login.jsp
   */
  @Execute(validator = false)
  @RemoveSession(name = "sessionDto")
  public String logout() {
    //TODO: 要検討事項 現時点は機能落ちしています
    /*
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    Login login = (Login)container.getComponent("LoginImpl");

    //ログイン状況を「ログアウト」に変更
    int userId = Integer.parseInt(sessionDto.userId);
    login.updateLoginStateToLogout(userId);
     */
    sessionDto = null;

    return "/login/index";
  }
}