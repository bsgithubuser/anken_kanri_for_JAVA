package jp.co.bsja.anken.form;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.seasar.struts.annotation.Msg;
import org.seasar.struts.annotation.Required;
import org.seasar.struts.util.ActionMessagesUtil;
import org.seasar.struts.util.RequestUtil;


public class PrjInfoBatchForm {

  /** ファイル .*/
  @Required(msg = @Msg(key = "ファイル選択は必須です。", resource = false))
  public FormFile formFile;

  /** 一括登録結果格納 .*/
  public String errorMsg;

  /**
   * 拡張子がCSV形式かチェックを行います。 .
   *
   * @return エラーメッセージ
   */
  public ActionMessages validateExtension() {
    // エラーメッセージ
    ActionMessages errors = new ActionMessages();

    // 拡張子チェック
    int index = formFile.getFileName().indexOf(".");
    String extension = formFile.getFileName().substring(index + 1);
    // 拡張子がcsvではない場合
    if (!extension.equals("csv") && formFile.getFileSize() != 0) {
      errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("MSG_E00016"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
    }
    return errors;
  }
}
