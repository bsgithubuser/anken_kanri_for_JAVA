package jp.co.bsja.anken.di;

import jp.co.bsja.anken.form.AnkenRegisterForm;

public interface AnkenRegisterInterface {
  /**
   * 案件情報登録初期表示.
   * @param ankenRegisterForm 案件情報登録フォーム
   * @return JSPファイル名
   */
  public String initEntry(AnkenRegisterForm ankenRegisterForm);

  /**
   * 案件情報編集初期表示.
   * @param ankenRegisterForm 案件情報登録フォーム
   * @return JSPファイル名
   */
  public String initEdit(AnkenRegisterForm ankenRegisterForm);

  /**
   * 案件情報登録処理.
   * @param ankenRegisterForm 案件情報登録フォーム
   * @return JSPファイル名
   */
  public String entry(AnkenRegisterForm ankenRegisterForm);

  /**
   * 案件情報編集処理.
   * @param ankenRegisterForm 案件情報登録フォーム
   * @return JSPファイル名
   */
  public String edit(AnkenRegisterForm ankenRegisterForm);

  /**
   * 画面入力クリア処理.
   * @param ankenRegisterForm 案件情報登録フォーム
   */
  public void clear(AnkenRegisterForm ankenRegisterForm);
}
