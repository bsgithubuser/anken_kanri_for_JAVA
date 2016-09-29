package jp.co.bsja.anken.action;

import javax.annotation.Resource;

import jp.co.bsja.anken.di.PrjInfoListInterface;
import jp.co.bsja.anken.dto.SessionDto;
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
   * 簡易検索を行います。 .
   *
   * @return 案件情報一覧画面のJSPファイル名
   */
  @Execute(validator = false)
  public String select() {
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    PrjInfoListInterface prjInfoList = (PrjInfoListInterface)container
         .getComponent("PrjInfoList");
    prjInfoListForm.prjInfoListFormList = prjInfoList.simpleSearch(prjInfoListForm);
    return "prj-info-list.jsp";
  }

}
