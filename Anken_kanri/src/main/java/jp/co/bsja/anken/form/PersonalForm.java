package jp.co.bsja.anken.form;

import java.util.List;

import org.seasar.struts.annotation.Mask;
import org.seasar.struts.annotation.Maxlength;
import org.seasar.struts.annotation.Msg;
import org.seasar.struts.annotation.Required;

public class PersonalForm {

  /** 担当者ID . */
  public int userId;

  /** 担当者名 . */
  @Required
  @Maxlength(maxlength = 30)
  public String userName;

  /** 管理者権限 . */
  @Required
  public String adminiRight;

  /** 最終更新日時(排他制御に使用) .*/
  public String date;

  /** パスワード . */
  @Mask(mask = "^[a-zA-Z0-9]+$", msg = @Msg(key = "MSG_E00015", resource = true))
  public String passWord;

  /** パスワード確認用 . */
  public String certifiedPass;

  /** 担当者情報一覧用変数 . */
  public List<PersonalForm> personalFormList;

  /** 登録編集用モード判定用変数 . */
  public boolean mode;
}
