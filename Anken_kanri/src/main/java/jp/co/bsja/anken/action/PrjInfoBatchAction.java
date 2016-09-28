package jp.co.bsja.anken.action;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import jp.co.bsja.anken.dao.impl.PrjInfoBatch;
import jp.co.bsja.anken.dto.SessionDto;
import jp.co.bsja.anken.form.PrjInfoBatchForm;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ServletContextUtil;
import org.seasar.struts.util.UploadUtil;

public class PrjInfoBatchAction {

  @Resource
  @ActionForm
  PrjInfoBatchForm prjInfoBatchForm;

  @Resource
  public SessionDto sessionDto;

  public ServletContext application;

  /**
   * 初期画面を表示します。 .
   *
   * @return 初期画面
   */
  @Execute(validator = false)
  public String index() {

    return "prjInfoBatch.jsp";
  }

  /**
   * CSV一括登録処理を行います。 .
   *
   * @return CSV処理画面
   */
  @Execute(validator = true, validate = "validateExtension", input = "prjInfoBatch.jsp")
  public String upload() {
    String path = ServletContextUtil.getServletContext().getRealPath("/");
    path = path + "upload/" + prjInfoBatchForm.formFile.getFileName();
    UploadUtil.write(path, prjInfoBatchForm.formFile);

    // S2Containerを初期化
    SingletonS2ContainerFactory.init();
    // インターフェースを実装したコンポーネント取得
    S2Container container = SingletonS2ContainerFactory.getContainer();
    PrjInfoBatch prjInfoBatch = (PrjInfoBatch)container.getComponent("PrjInfoBatchImpl");

    prjInfoBatchForm.errorMsg = prjInfoBatch.entry(path);

    return "prjInfoBatch.jsp";
  }
}
