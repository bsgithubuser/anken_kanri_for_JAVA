package jp.co.bsja.anken.action;

import javax.annotation.Resource;

import jp.co.bsja.anken.dto.SessionDto;

import org.seasar.struts.annotation.Execute;

public class MenuAction {

  @Resource
  public SessionDto sessionDto;
  /**
   * メニュー画面への実行メソッド .
   * @return 遷移先jsp
   */
  //メニュー画面への実行メソッド
  @Execute(validator = false)
  public String index() {
    //    if (sessionDto.administrativePermission.equals("0")) {
    //      if (sessionDto.loginData == null) {
    //        return "/login/login.jsp";
    return "menu.jsp";
    //      } else {
    //      // 管理者ではない人が管理者ページに行こうとした場合のエラー
    //    ActionMessages errors = new ActionMessages();
    //    errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("MSG_E00003"));
    //    ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
    //    return "/error/";
    //  }
  }
}
