package jp.co.bsja.anken.action;

import org.seasar.struts.annotation.Execute;

public class ErrorAction {
  @Execute(validator = false)
  public String index() {
    return "error.jsp";
  }
}
