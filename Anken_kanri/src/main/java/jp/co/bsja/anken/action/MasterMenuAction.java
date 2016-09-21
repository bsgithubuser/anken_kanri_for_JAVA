package jp.co.bsja.anken.action;

import javax.annotation.Resource;

import jp.co.bsja.anken.dto.SessionDto;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ActionMessagesUtil;
import org.seasar.struts.util.RequestUtil;

public class MasterMenuAction {

  @Resource
  //jspのヘッダーにDtoを使用しているのでここでDtoを宣言する
  public SessionDto sessionDto;

  /**
   * マスタ管理画面に遷移するためのメソッドです。
   * @return 管理者権限がある場合(adimin = 0)はmaster-menu.jspに遷移します。
   */
  @Execute(validator = false)
  public String index() {
    if (sessionDto.admin.equals("0")) {
      return "master-menu.jsp";
    } else {
      // 管理者ではない人が管理者ページに行こうとした場合のエラー
      ActionMessages errors = new ActionMessages();
      errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("MSG_E00003"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
      return "/error/";
    }
  }
}
