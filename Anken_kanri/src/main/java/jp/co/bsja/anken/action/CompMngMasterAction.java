package jp.co.bsja.anken.action;

import static jp.co.bsja.anken.common.CommonFunction.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import jp.co.bsja.anken.di.CompMngMasterInterface;
import jp.co.bsja.anken.dto.SessionDto;
import jp.co.bsja.anken.entity.MCmpn;
import jp.co.bsja.anken.form.CompMngMasterForm;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.MessageResourcesUtil;

public class CompMngMasterAction {

  /** アクションフォーム .*/
  @ActionForm
  @Resource
  protected CompMngMasterForm compMngMasterForm;

  /** jspのヘッダーにDtoを使用しているのでここでDtoを宣言します .*/
  @Resource
  public SessionDto sessionDto;

  /** 時刻を取得するための変数(日時はjspのfunctionでactionFormに持ち込むことができないので) .*/
  public HttpServletRequest request;

  /**
   * 会社情報管理マスタに遷移するためのメソッドです。
   * @return list-comp-mng.jsp
   */
  @Execute(validator = false)
  public String index() {
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    CompMngMasterInterface compMngMaster =
        (CompMngMasterInterface)container.getComponent("CompMngMasterImpl");
    compMngMasterForm.searchResultList = compMngMaster.searchListData();
    return "list-comp-mng.jsp";
  }

  /**
   * 登録処理をするメソッドです。 .
   * @return 登録成功なら登録完了画面に遷移します。失敗なら再度登録画面に戻ります
   */
  @Execute(validator = true, input = "entry-comp-mng.jsp")
  public String entry() {
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    CompMngMasterInterface compMngMaster =
        (CompMngMasterInterface)container.getComponent("CompMngMasterImpl");

    if (eq("", compMngMasterForm.idItemString)) {
      //登録処理
      int count = compMngMaster.entry(compMngMasterForm.nameItem,
          compMngMasterForm.nameKanaItem,
          compMngMasterForm.idItemString, compMngMasterForm.flgAfterClickDialog);
      if (count == 2) {
        //同じ名前の会社が既にデータベースにあったので、jspで確認ダイアログを表示させます。
        compMngMasterForm.sameNameFlag = 1;
        compMngMasterForm.popUpData = MessageResourcesUtil
            .getMessage("MSG_I00007", compMngMasterForm.nameItem);
        return "entry-comp-mng.jsp";
      } else if (count == 1) {
        //エラー
        return "entry-comp-mng.jsp";
      } else {
        //登録成功
        return "complete-comp-mng.jsp";
      }
    } else {
      //更新処理
      Timestamp date = Timestamp.valueOf(compMngMasterForm.date);
      if (compMngMaster.update(compMngMasterForm.nameItem,
          compMngMasterForm.nameKanaItem,
          compMngMasterForm.idItemString, date) == 1) {
        return "entry-comp-mng.jsp";
      } else {
        //更新成功
        return "complete-comp-mng.jsp";
      }
    }
  }

  /**.
   * 選択したデータを削除するクラスに遷移するメソッドです。
   * @return 一覧画面に戻ります。
   */
  @Execute(validator = false)
  public String delete() {
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    CompMngMasterInterface compMngMaster =
        (CompMngMasterInterface)container.getComponent("CompMngMasterImpl");
    compMngMaster.delete(compMngMasterForm.deleteId, compMngMasterForm.deleteName);
    return "/compMngMaster/";
  }

  /** .
   * 新規登録ボタンを押した際に登録画面に遷移するためのメソッドです。
   * @return 登録画面に遷移します。
   */
  @Execute(validator = false)
  public String goToEntryJsp() {
    return "entry-comp-mng.jsp";
  }

  /** .
   * 編集リンクを押した際に登録画面(編集モード)に遷移するためのメソッドです。
   * @return 登録画面に遷移します(その画面は編集モードにします)。
   */
  @Execute(validator = false)
  public String edit() {
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    CompMngMasterInterface compMngMaster =
        (CompMngMasterInterface)container.getComponent("CompMngMasterImpl");
    MCmpn searchResult = compMngMaster.search(compMngMasterForm.idItemInteger,
        compMngMasterForm.nameItem);
    if (searchResult == null) {
      return "index";
    } else {
      compMngMasterForm.idItemString = Integer.toString(searchResult.cmpnId);
      compMngMasterForm.nameItem = searchResult.cmpnName;
      compMngMasterForm.nameKanaItem = searchResult.cmpnNameFuri;
      //排他制御に使うため、最終更新時間も取得します。
      compMngMasterForm.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
      .format(searchResult.updateDate);
      return "entry-comp-mng.jsp";
    }
  }
}
