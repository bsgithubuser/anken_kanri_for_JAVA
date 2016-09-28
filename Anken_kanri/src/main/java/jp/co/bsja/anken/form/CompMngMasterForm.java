package jp.co.bsja.anken.form;

import java.util.List;

import jp.co.bsja.anken.entity.MCmpn;

import org.seasar.struts.annotation.Msg;
import org.seasar.struts.annotation.Required;

public class CompMngMasterForm {

  /**. 一覧画面の入力欄に表示させる情報を入れた変数です。 **/
  public List<MCmpn> searchResultList;

  /** 会社ID(検索に使用します) .*/
  public Integer idItemInteger;

  /** 排他制御に使用する、データの最終更新日時 .*/
  public String date;

  /** 編集時に入力欄に表示させるデータ .*/
  public MCmpn searchResultForEntryJsp;

  /** 会社ID .*/
  public String idItemString;

  /** 会社名 .*/
  @Required(msg = @Msg(key = "MSG_E00011"))
  public String nameItem;

  /** フリガナ .*/
  @Required(msg = @Msg(key = "MSG_E00011"))
  public String nameKanaItem;

  /** 同じ名前が既に登録されていたら1にするフラグです(jspのc:ifで使用します)。jspに戻ってポップアップを表示させます。 .*/
  public int sameNameFlag;

  /** 名前重複時にポップアップに表示させるメッセージ .*/
  public String popUpData;

  /** ポップアップで「はい」を押した後にjspのファンクションで宣言します(Actionの分岐で使用するため)。 .*/
  public int flgAfterClickDialog = 0;

  /** 削除処理をする際に使用する会社ID .*/
  public Integer deleteId;

  /** 削除処理をする際に使用する会社名 .*/
  public String deleteName;

}
