package jp.co.bsja.anken.dao;

import java.sql.Timestamp;
import java.util.List;

import jp.co.bsja.anken.entity.MCmpn;
import jp.co.bsja.anken.entity.TProjInfo;

public class CompMngMasterDao extends Dao{

  /**.
   * 一覧画面に並べるデータを取得します。
   * @return 現時点で会社情報管理マスタテーブルに登録されている全ての会社
   */
  public List<MCmpn> search() {
    return (List<MCmpn>)jdbcManager.from(MCmpn.class).orderBy("CMPN_ID").getResultList();
  }

  /**.
   * 登録画面の入力欄に表示させるために、編集対象のデータを検索します。
   * @param id 編集する会社のID
   * @return 編集予定のデータ
   */
  public MCmpn search(Integer id) {
    return (MCmpn)jdbcManager.from(MCmpn.class).where("cmpnId = ?", id).getSingleResult();
  }

  /**.
   * データベースに同じ名前の会社名が何件あるか検索するメソッドです。
   * @param name 会社名
   * @return ヒットした件数
   */
  public int checkSameName(String name) {
    return (int)jdbcManager.from(MCmpn.class).where("CMPN_NAME = ?",name).getCount();
  }

  /**
   * 登録処理をするメソッドです。
   * @param name 会社名
   * @param nameKana フリガナ
   * @return 登録成功...1 登録失敗...0
   */
  public int entry(String name, String nameKana) {
    Dao dao = new Dao();
    MCmpn entity = new MCmpn();
    entity.cmpnId = dao.getCmpnIdSeq();
    entity.cmpnName = name;
    entity.cmpnNameFuri = nameKana;
    entity.createDate = new Timestamp(System.currentTimeMillis());
    entity.updateDate = new Timestamp(System.currentTimeMillis());
    return jdbcManager.insert(entity).execute();
  }

  /**
   * 更新をするメソッドです。
   * @param id 会社ID
   * @param name 会社名
   * @param nameKana 会社名カナ
   * @return 成功...1 失敗...0
   */
  public int update(String id, String name, String nameKana) {
    return jdbcManager.updateBySql("UPDATE M_CMPN SET"
        + " CMPN_NAME = ?, CMPN_NAME_FURI = ?, UPDATE_DATE = ?"
        + " WHERE CMPN_ID = ?",
        String.class,
        String.class,
        Timestamp.class,
        Integer.class)
        .params(name, nameKana, new Timestamp(System.currentTimeMillis()), id).execute();
  }

  /**
   * 削除予定のデータがあらかじめ削除されていないか、再度検索して確認します。
   * @param id 削除予定のデータ
   * @return 異常なし...1 異常あり...0
   */
  public int idCheck(Integer id) {
    return (int)jdbcManager.from(MCmpn.class).where("cmpnId = ?", id).getCount();
  }

  /**
   * 削除対象の会社IDに紐づくレコード数を返却します。 .
   *
   * @param id 削除予定の会社ID
   * @return 該当した件数
   */
  public int idUsedCheck(Integer id) {
    return (int)jdbcManager.from(TProjInfo.class).where("cmpnId= ?", id).getCount();
  }

  /**
   * 選択したデータを削除します。
   * @param id 選択したデータのID
   * @return 異常なし...1 異常あり...0
   */
  public int delete(Integer id) {
    MCmpn entity = new MCmpn();
    entity.cmpnId = id;
    return jdbcManager.delete(entity).execute();
  }
}
