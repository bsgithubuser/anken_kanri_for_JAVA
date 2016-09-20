package jp.co.bsja.anken.di;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import jp.co.bsja.anken.dao.MUsersMngDao;
import jp.co.bsja.anken.form.PersonalForm;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.util.BeanMap;
import org.seasar.struts.util.ActionMessagesUtil;
import org.seasar.struts.util.RequestUtil;


public class MUsersMngImpl implements MUsersMngInterface{

  /**
   * 全担当者情報を取得します。
   *
   * @return formList 担当者情報一覧
   */
  public List<PersonalForm> showMusers() {
    MUsersMngDao dao = new MUsersMngDao();
    //テーブルから情報取得
    List<BeanMap> list = dao.findMusers();
    List<PersonalForm> formList = new ArrayList<>();
    for (BeanMap map : list) {
      PersonalForm form = new PersonalForm();
      form.userId = (int)map.get("userId");
      form.userName = (String)map.get("userName");
      form.passWord = (String)map.get("password");
      form.adminiRight = alterAdmini((String)map.get("admin"));
      formList.add(form);
    }
    return formList;
  }

  /**
   * 管理者権限の変換処理をします。 .
   *
   * @param admini .
   * @return 管理者権限名
   */
  private String alterAdmini(String admini) {
    switch (admini) {
      case "0": return "管理者";
      case "1": return "一般";
      case "9": return "開発者";
      default : return "";
    }
  }

  /**
   * 担当者情報を取得します。 .
   *
   * @param form .
   * @return form 担当者情報
   */
  public PersonalForm showMuser(PersonalForm form) {
    MUsersMngDao dao = new MUsersMngDao();
    //テーブルから情報取得
    List<BeanMap> list = dao.findMuser(form);
    for (BeanMap map : list) {
      form.userId = (int)map.get("userId");
      form.userName = (String)map.get("userName");
      form.adminiRight = alterAdmini((String)map.get("admin"));
    }
    return form;
  }

  /**
   * 登録更新処理を行います。 .
   *
   * @param form .
   * @return success 登録更新件数
   */
  public int registrationUpdate(PersonalForm form) {
    int success = 0;
    //登録処理
    if (form.mode) {
      success = registrationUser(form);
    } else {
      //更新処理
      success = updateUser(form);
    }
    return success;
  }

  /**
   * 登録処理を行います。 .
   *
   * @param form
   *
   * @return count 登録件数
   */
  public int registrationUser(PersonalForm form) {
    MUsersMngDao dao = new MUsersMngDao();
    ActionMessages errors = new ActionMessages();
    int count = 0;
    //パスワードnullチェック
    if ((form.passWord == null) || (form.passWord.isEmpty()) ) {
      errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("MSG_E00001", "パスワード"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
      return count;
    }
    //確認用パスワードnullチェック
    if ((form.certifiedPass == null) || (form.certifiedPass.isEmpty()) ) {
      errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("MSG_E00001", "確認用パスワード"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
      return count;
    }
    if ((form.passWord.length() == 8)
          && (form.passWord.equals(form.certifiedPass))) {
      //パスワードハッシュ化処理
      form.passWord = toEncryptedHashValue("SHA-256", form.passWord);
      count = dao.entryUser(form);
    } else {
      errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("MSG_E00004"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
    }
    return count;

  }

  /**
   * 編集処理を行います。 .
   *
   * @param form
   *
   * @return count 更新件数
   */
  public int updateUser(PersonalForm form) {
    MUsersMngDao dao = new MUsersMngDao();
    ActionMessages errors = new ActionMessages();
    int count = 0;
    //パスワードチェック
    if ((form.passWord == null) || (form.passWord.length() == 0)) {
      count = dao.updateUser(form);
    } else {
      if ((form.passWord.length() == 8)
              && (form.passWord.equals(form.certifiedPass))) {
        //パスワードハッシュ化処理
        form.passWord = toEncryptedHashValue("SHA-256", form.passWord);
        count = dao.updateUser(form);
      } else {
        errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("MSG_E00004"));
        ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
      }
    }
    return count;
  }

  /**
   * パスワードハッシュ化処理を行います。 .
   *
   * @param algorithmName ハッシュ化アルゴリズム .
   * @param value  パスワード
   * @return ハッシュ化により出来た64byte文字列
   */
  public String toEncryptedHashValue(String algorithmName, String value) {
    MessageDigest md = null;
    StringBuilder sb = null;
    try {
      md = MessageDigest.getInstance(algorithmName);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    md.update(value.getBytes());
    sb = new StringBuilder();
    for (byte b : md.digest()) {
      String hex = String.format("%02x", b);
      sb.append(hex);
    }
    return sb.toString();
  }

  /**
   * 削除処理を行います。 .
   *
   * @param form
   **/
  public void deleteUser(PersonalForm form) {
    MUsersMngDao dao = new MUsersMngDao();
    int count = 0;
    boolean loginJudge = false;
    //担当者IDに紐付く案件情報を取得し、1件以上エラー
    count = existPrjInfo(form);
    if (count >= 1 ) {
      ActionMessages errors = new ActionMessages();
      errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("MSG_E00005"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
    }
    //担当者IDに紐付くログイン状況を取得し、「ログイン」の場合エラー
    loginJudge = findLoginState(form);
    if (loginJudge) {
      ActionMessages errors = new ActionMessages();
      errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("MSG_E00007"));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
    }
    if ((count == 0) && !(loginJudge)) {
      dao.delete(form);
    }
  }

  /**
   * 削除前チェックを行います。 .
   *
   * @param form
   * @return checkCount 該当案件情報件数
   **/
  public int existPrjInfo(PersonalForm form) {
    MUsersMngDao dao = new MUsersMngDao();
    int checkCount = dao.checkPrjInfo(form);
    return checkCount;
  }

  /**
   * 削除前チェックを行います。 .
   *
   * @param form
   * @return loginCheck 該当する情報のログイン
   **/
  public boolean findLoginState(PersonalForm form) {
    MUsersMngDao dao = new MUsersMngDao();
    boolean loginCheck = dao.loginCheck(form);
    return loginCheck;
  }
}
