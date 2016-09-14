package jp.co.bsja.anken.dao;

import static org.seasar.extension.jdbc.parameter.Parameter.*;

import java.util.List;

import jp.co.bsja.anken.SqlFiles;
import jp.co.bsja.anken.entity.MSkill;
import jp.co.bsja.anken.entity.MUsers;

import org.seasar.framework.beans.util.BeanMap;

public class SampleDao extends Dao {
  /**
   * DBアクセスのパターンいろいろを記載します .
   *
   * @param userName ユーザ名
   * @return ユーザ情報リスト
   */
  public List<MUsers> findSampls(String userName) {
    // SQL文を直接指定するパターン(親クラスを使う例)
    List<BeanMap> list = selectBySql("SELECT * FROM M_USERS");
    // SQL文を直接指定するパターン(親クラスを使わない例)
    List<BeanMap> listNotPrnt = jdbcManager
        .selectBySql(BeanMap.class, "SELECT * FROM M_USERS")
        .getResultList();

    // SQLファイル名を指定するパターン(親クラスを使う例)
    List<BeanMap> listMap = selectBySqlFile(SqlFiles.FIND_M_USERS_BY_USER_ID_SAMPLE,
        params("userName", "test"));
    // SQLファイル名を指定するパターン(親クラスを使わない例)
    List<BeanMap> listMapNotPrnt = jdbcManager
        .selectBySqlFile(BeanMap.class, PATH + SqlFiles.FIND_M_USERS_BY_USER_ID_SAMPLE,
            params("userName", "test").$())
        .getResultList();

    // 実行結果のオブジェクトにアクセスする例
    for (BeanMap elm : listMapNotPrnt) {
      System.out.println(elm.get("userName"));
    }

    // シーケンス
    //List<BeanMap> list = selectBySql("SELECT * FROM M_USERS");
    // ゼロスクのパターン
    //return jdbcManager.from(MUsers.class).getResultList();
    return null;

  }

  public int insert(MSkill entity) {
    return jdbcManager.insert(entity).execute();
  }

  public int uptate(MSkill entity) {
    return jdbcManager.update(entity).execute();

  }

  public int delete(MSkill entity) {
    return jdbcManager.delete(entity).execute();
  }
}

