package jp.co.bsja.anken.action;

import jp.co.bsja.anken.dto.SessionDto;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

public class MenuAction {

  @ActionForm
  public SessionDto sessionDto;
  /**
   * メニュー画面への実行メソッド .
   * @return 遷移先jsp
   */
  //メニュー画面への実行メソッド
  @Execute(validator = false)
  public String index() {
    return "menu.jsp";
  }
}
