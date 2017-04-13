package jp.co.bsja.anken.action;

import java.io.IOException;

import javax.annotation.Resource;

import jp.co.bsja.anken.di.PrintInterface;
import jp.co.bsja.anken.dto.SessionDto;
import jp.co.bsja.anken.form.PrintForm;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

public class PrintAction {

  public SessionDto sessionDto;

  @Resource
  @ActionForm
  protected PrintForm printForm;

  /**
  * 案件情報一覧画面遷移処理を行います。 .
  *
  * @return 案件情報一覧画面のJSPファイル名
 * @throws IOException
  */
  @Execute(validator = false)
  public String index() throws IOException {
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    PrintInterface print = (PrintInterface)container.getComponent("Print");
    print.createPrintList(printForm, sessionDto);
    //print.poiTest();
    return "print.jsp";
  }
}
