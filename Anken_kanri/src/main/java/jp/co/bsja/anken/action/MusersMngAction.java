package jp.co.bsja.anken.action;

import javax.annotation.Resource;

import jp.co.bsja.anken.di.MUsersMngInterface;
import jp.co.bsja.anken.dto.SessionDto;
import jp.co.bsja.anken.form.PersonalForm;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

public class MusersMngAction {

  public SessionDto sessionDto;

  @Resource
  @ActionForm
  protected PersonalForm personalForm;

  /**
  * 担当者管理マスタ画面遷移処理を行います。 .
  *
  * @return 担当者管理マスタ画面のJSPファイル名
  */
  @Execute(validator = false)
  public String index() {
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    MUsersMngInterface personalManagement = (MUsersMngInterface)container
        .getComponent("MUsersMng");
    personalForm.personalFormList = personalManagement.showMusers();
    return "musersmng.jsp";
  }

  /**
   * 新規登録画面遷移処理を行います。 .
   *
   * @return 新規登録画面のJSPファイル名
   */
  @Execute(validator = false)
  public String showEntry() {
    personalForm.mode = true;
    return "info-entry.jsp";
  }

  /**
   * 更新画面遷移処理を行います。 .
   *
   * @return 更新画面のJSPファイル名
   */

  @Execute(validator = false)
  public String showUpdate() {
    personalForm.mode = false;
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    MUsersMngInterface mng = (MUsersMngInterface)container
        .getComponent("MUsersMng");
    personalForm = mng.showMuser(personalForm);

    return "info-entry.jsp";
  }

  /**
   * 登録更新処理を行います。 .
   *
   * @return 遷移先画面のJSPファイル名
   * */
  @Execute(validator = true, input = "info-entry.jsp")
  public String entryUpdate() {
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    MUsersMngInterface mng = (MUsersMngInterface)container
        .getComponent("MUsersMng");
    int count = mng.registrationUpdate(personalForm);
    if (count == 1) {
      return "complete.jsp";
    } else {
      return "info-entry.jsp";
    }
  }

  /**
   * 更新画面入力項目クリア処理を行います。 .
   *
   * @return 遷移先画面のJSPファイル名
   * */
  @Execute(validator = false)
  public String clear() {
    //入力項目をクリアにする。
    personalForm.userName = "";
    personalForm.passWord = "";
    personalForm.certifiedPass = "";
    personalForm.adminiRight = "";
    return "info-entry.jsp";
  }

  /**
   * 削除処理を行います。 .
   *
   * @return 遷移先画面のJSPファイル名
   * */
  @Execute(validator = false)
  public String delete() {
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    MUsersMngInterface mng = (MUsersMngInterface)container
        .getComponent("MUsersMng");
    //削除処理
    mng.deleteUser(personalForm);
    return index();

  }
}
