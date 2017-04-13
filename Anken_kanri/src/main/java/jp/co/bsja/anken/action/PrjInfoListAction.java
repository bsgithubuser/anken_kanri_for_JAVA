package jp.co.bsja.anken.action;

import javax.annotation.Resource;

import jp.co.bsja.anken.di.PrjInfoListInterface;
import jp.co.bsja.anken.dto.SessionDto;
import jp.co.bsja.anken.form.AnkenRegisterForm;
import jp.co.bsja.anken.form.PrjInfoListForm;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

public class PrjInfoListAction {

  public SessionDto sessionDto;

  @Resource
  @ActionForm
  protected PrjInfoListForm prjInfoListForm;
  @Resource
  @ActionForm
  protected AnkenRegisterForm ankenRegisterForm;

  /**
  * 案件情報一覧画面遷移処理を行います。 .
  *
  * @return 案件情報一覧画面のJSPファイル名
  */
  @Execute(validator = false)
  public String index() {
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    PrjInfoListInterface prjInfoList = (PrjInfoListInterface)container
        .getComponent("PrjInfoList");
    prjInfoListForm.userNameList = prjInfoList.findMUsersName();
    prjInfoListForm.skillMasterFormList = prjInfoList.findSkillInfo();
    return "prj-info-list.jsp";
  }

  /**
   * 検索を行います。 .
   *
   * @return 案件情報一覧画面のJSPファイル名
   */
  @Execute(validator = true, input = "prj-info-list.jsp")
  public String select() {
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    PrjInfoListInterface prjInfoList = (PrjInfoListInterface)container
         .getComponent("PrjInfoList");
    prjInfoListForm.prjInfoListFormList = prjInfoList.search(prjInfoListForm, sessionDto);
    return "/prjInfoList/";
  }

  /**
   * マスタメニュー画面への遷移 実行メソッド .
   * @return 遷移先jsp
   */
  @Execute(validator = false)
  public String returnMenu() {
    return "/menu/";
  }

  /**
   * 案件情報編集 編集画面遷移メソッド .
   * @return 遷移先jsp
   */
  @Execute(validator = false)
  public String showUpdate() {
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    PrjInfoListInterface prjInfoList = (PrjInfoListInterface)container
         .getComponent("PrjInfoList");
    prjInfoList.pullIdToARF(prjInfoListForm, ankenRegisterForm);
    return "/ankenRegister/";
  }

  /**
   * 案件情報削除 実行メソッド .
   * @return 遷移先jsp
   */
  @Execute(validator = false)
  public String delete() {
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    PrjInfoListInterface prjInfoList = (PrjInfoListInterface)container
         .getComponent("PrjInfoList");
    prjInfoList.delete(prjInfoListForm, sessionDto);
    return select();
  }

  /**
   * 案件情報印刷 実行メソッド .
   * @return 遷移先jsp
   */
  @Execute(validator = false)
  public String print() {
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    PrjInfoListInterface prjInfoList = (PrjInfoListInterface)container
           .getComponent("PrjInfoList");
    prjInfoList.holdPrintInfo(prjInfoListForm, sessionDto, false);
    return "/prjInfoList/";
  }

  /**
   * 案件情報一括印刷 実行メソッド .
   * @return 遷移先jsp
   */
  @Execute(validator = false)
  public String bulkPrint() {
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    PrjInfoListInterface prjInfoList = (PrjInfoListInterface)container
           .getComponent("PrjInfoList");
    prjInfoList.holdPrintInfo(prjInfoListForm, sessionDto, true);
    return "/prjInfoList/";
  }

}
