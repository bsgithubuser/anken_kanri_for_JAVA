package jp.co.bsja.anken.di;

import java.sql.Timestamp;
import java.util.List;

import jp.co.bsja.anken.entity.MCmpn;

public interface CompMngMasterInterface {

  /**.
   * 一覧画面に並べるデータを取得するためのメソッドです。
   * @return 一覧画面に並べるデータ
   */
  public List<MCmpn> searchListData();

  /**.
   * 編集時、入力欄にあらかじめ表示させる値を取得するためのメソッドです。
   * @param idItem 会社ID
   * @return 会社IDと会社名とフリガナの入った変数
   */
  public  MCmpn search(Integer id, String name);

  /**
   * 登録処理をするメソッドです。
   * @param name 会社名
   * @param nameKana フリガナ
   * @param id 会社ID
   * @param flgAfterClickDialog 同じ会社名のデータがある場合の確認ダイアログで「はい」を押したかどうか(「はい」を押したら1になります)
   * @return 0...エラー 1...異常なし
   */
  public int entry(String name, String nameKana, String id, int flgAfterClickDialog);

  /**
   *更新処理をするメソッドです。
   * @param name 会社名
   * @param nameKana フリガナ
   * @param id 会社ID
   * @param time 対象データの最終更新日時(排他制御に使用します)
   * @return 0....エラー 1...異常なし
   */
  public int update(String name, String nameKana, String id, Timestamp time);

  /**
   * 削除処理を実行します .
   *
   * @param id 会社ID
   * @param name 会社名
   */
  public void delete(Integer id, String name);
}
