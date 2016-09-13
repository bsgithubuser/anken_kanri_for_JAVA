package jp.co.bsja.anken.action;

import org.seasar.struts.annotation.Execute;

public class MenuAction {
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
