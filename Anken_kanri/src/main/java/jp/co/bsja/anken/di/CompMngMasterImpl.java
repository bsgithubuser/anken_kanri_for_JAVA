package jp.co.bsja.anken.di;

import java.sql.Timestamp;
import java.util.List;

import jp.co.bsja.anken.dao.CompMngMasterDao;
import jp.co.bsja.anken.dao.Dao;
import jp.co.bsja.anken.entity.MCmpn;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.struts.util.ActionMessagesUtil;
import org.seasar.struts.util.RequestUtil;

public class CompMngMasterImpl implements CompMngMasterInterface {

  @Override
  public List<MCmpn> searchListData() {
    CompMngMasterDao dao = new CompMngMasterDao();
    return dao.search();
  }

  @Override
  public  MCmpn search(Integer id, String name) {
    CompMngMasterDao dao = new CompMngMasterDao();
    ActionMessages errors = new ActionMessages();
    MCmpn searchResult = dao.search(id);
    if (searchResult == null) {
      errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("MSG_E00009", name));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
    }
    return searchResult;
  }

  @Override
  public int entry(String name, String nameKana, String id, int flgAfterClickDialog) {
    ActionMessages errors = new ActionMessages();
    if (checkSameName(name) != 0 && flgAfterClickDialog == 0) {
      //登録画面に戻ってダイアログを表示
      return 2;
    } else {
      //登録処理
      if (executeEntry(name, nameKana) == 1) {
        return 0;
      } else {
        //登録時に何かあった場合の登録エラー
        errors.add(ActionMessages.GLOBAL_MESSAGE,
            new ActionMessage("MSG_E00012", name));
        ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
        return 1;
      }
    }
  }

  @Override
  public int update(String name, String nameKana, String id, Timestamp time) {
    ActionMessages errors = new ActionMessages();
    if (checkLock(time,id) == 1) {
      //更新済みエラー
      errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("MSG_E00006", name));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
      return 1;
    } else {
      //更新処理
      if (executeUpdate(id, name, nameKana) == 1) {
        return 0;
      } else {
        //更新済みエラー以外で何かあった場合の更新エラー
        errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("MSG_E00012", name));
        ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
        return 1;
      }
    }
  }

  /*
   * 排他制御をするメソッドです。
   * @param time 最終更新日時
   * @param id 更新対象のID
   * @return 1...エラー それ以外...異常なし
   */
  private int checkLock(Timestamp time, String id) {
    Dao dao = new Dao();
    String fromName = "M_CMPN";
    String idName = "CMPN_ID";
    int count = dao.hasLocked(fromName, idName, id, time);
    return count;
  }

  /*
   *更新をするメソッドです。
   * @param id 会社ID
   * @param name 会社名
   * @param nameKana フリガナ
   * @return
   */
  private int executeUpdate(String id, String name, String nameKana) {
    CompMngMasterDao dao = new CompMngMasterDao();
    return dao.update(id, name, nameKana);
  }

  /*
   * 登録の際に同じ会社名がデータベースにいくつあるか検索するメソッドです。
   * @param name 会社名
   * @return 検索してヒットした数
   */
  private int checkSameName(String name) {
    CompMngMasterDao dao = new CompMngMasterDao();
    int count = dao.checkSameName(name);
    return count;
  }

  /*
   *登録処理をするメソッドです。
   * @param name 会社名
   * @param nameKana フリガナ
   * @return
   */
  private int executeEntry(String name, String nameKana) {
    CompMngMasterDao dao = new CompMngMasterDao();
    return dao.entry(name, nameKana);
  }

  @Override
  public void delete(Integer id, String name) {
    if (confirmExists(id, name) == 1 && checkUsed(id, name) == 0) {
      executeDelete(id, name);
    }
    return;
  }

  /*
   * 削除対象のデータが既に削除されていないかどうか確認します。
   * @param id 削除対象のID
   * @param name 削除対象の会社名
   * @return 1...異常なし 0...削除済みエラー
   */
  private int confirmExists(Integer id, String name) {
    CompMngMasterDao dao = new CompMngMasterDao();
    ActionMessages errors = new ActionMessages();
    int count = dao.idCheck(id);
    if (count == 1) {
      return 1;
    } else {
      errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("MSG_E00009", name));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
      return 0;
    }
  }

  /*
   * 削除対象のデータが他のテーブルで使われていないかどうか確認します。
   *
   * @param id 削除対象のID
   * @param name 削除対象の会社名
   * @return 0...異常なし 1...他のテーブルで使用されているので削除できないエラー
   */
  private int checkUsed(Integer id, String name) {
    CompMngMasterDao dao = new CompMngMasterDao();
    ActionMessages errors = new ActionMessages();
    int countUsed = dao.idUsedCheck(id);
    if (countUsed >= 1) {
      errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("MSG_E00005", name));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
      return 1;
    } else {
      return 0;
    }
  }

  /*
   * 削除処理をするメソッドです。
   * @param id 削除対象のID
   * @param name 削除対象の会社名
   */
  private void executeDelete(Integer id, String name) {
    CompMngMasterDao dao = new CompMngMasterDao();
    ActionMessages errors = new ActionMessages();
    int count = dao.delete(id);
    if (count == 1) {
      //削除成功
      return;
    } else {
      //何らかの理由で削除ができなかった場合はこちらに分岐します。
      errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("MSG_E00013", name));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
      return ;
    }
  }
}
