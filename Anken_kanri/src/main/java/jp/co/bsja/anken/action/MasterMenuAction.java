package jp.co.bsja.anken.action;

import javax.annotation.Resource;

import jp.co.bsja.anken.dto.LoginCheckDto;

import org.seasar.struts.annotation.Execute;

public class MasterMenuAction {

  @Resource
  public LoginCheckDto loginCheckDto;

  /**
   * マスタ管理画面に遷移するためのメソッドです。
   * @return 管理者権限を持ち、なおかつログイン情報がある場合はmaster-menu.jspに遷移します。
   */
  @Execute(validator = false)
  public String index() {
    //    if (loginCheckDto.administrativePermission.equals("0")) {
    //      if (loginCheckDto.loginData == null) {
    //        return "/login/login.jsp";
    //      } else {
           return "master-menu.jsp";
    //      }
    //    } else {
    //      // 管理者ではない人が管理者ページに行こうとした場合のエラー
    //    ActionMessages errors = new ActionMessages();
    //    errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("MSG_E00003"));
    //    ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
    //    return "/error/";
    //  }
  }
}
