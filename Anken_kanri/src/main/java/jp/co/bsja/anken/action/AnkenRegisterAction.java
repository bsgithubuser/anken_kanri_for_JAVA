package jp.co.bsja.anken.action;

import javax.annotation.Resource;

import jp.co.bsja.anken.di.AnkenRegisterInterface;
import jp.co.bsja.anken.form.AnkenRegisterForm;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

public class AnkenRegisterAction {

  @ActionForm
  @Resource
  protected  AnkenRegisterForm ankenRegisterForm;

  /**
   * 新規登録画面遷移処理を行います。 .
   * @return 新規登録画面のJSPファイル名
   */
  @Execute(validator = false)
  public String index() {
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    AnkenRegisterInterface  ankenRegister = (AnkenRegisterInterface)container
        .getComponent("AnkenRegisterImpl");

    int num = 3;
    String jsp;

    if (num == 0) {
      jsp = ankenRegister.initEntry(ankenRegisterForm);
    } else {
      jsp = ankenRegister.initEdit(ankenRegisterForm);
    }
    return jsp;
  }

  /**
   * 新規登録処理を行います。 .
   * @return 完了画面のJSPファイル名
   */
  @Execute(validator = true,input = "ankenRegister.jsp")
  public String entry() {
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    AnkenRegisterInterface  ankenRegister = (AnkenRegisterInterface)container
        .getComponent("AnkenRegisterImpl");
    String jsp = ankenRegister.entry(ankenRegisterForm);

    if (jsp.equals("ankenRegister.jsp")) {
      ankenRegister.clear(ankenRegisterForm);
    }

    return jsp;

  }

  /**
   * 編集処理を行います。 .
   * @return 完了画面のJSPファイル名
   */
  @Execute(validator = true,input = "ankenRegister.jsp")
  public String edit() {
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    AnkenRegisterInterface  ankenRegister = (AnkenRegisterInterface)container
        .getComponent("AnkenRegisterImpl");
    String jsp = ankenRegister.edit(ankenRegisterForm);

    if (jsp.equals("ankenRegister.jsp")) {
      ankenRegister.clear(ankenRegisterForm);
    }

    return jsp;
  }

  /**
   * 入力項目のクリア処理を行います。 .
   * @return 新規登録画面のJSPファイル名
   */
  @Execute(validator = false)
  public String clear() {
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    AnkenRegisterInterface  ankenRegister = (AnkenRegisterInterface)container
        .getComponent("AnkenRegisterImpl");
    ankenRegister.clear(ankenRegisterForm);

    return "ankenRegister.jsp";
  }
}
