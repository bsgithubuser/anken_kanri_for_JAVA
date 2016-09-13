package jp.co.bsja.anken.di;

import java.util.List;

import jp.co.bsja.anken.dao.SampleDao;
import jp.co.bsja.anken.entity.MUsers;

public class SampleImpl implements SampleInterface {
  /**
   * サンプルソース .
   *
   * @param userName テスト
   * @return 画面表示情報とか
   */
  @Override
  public List<String> makeSample(String userName) {
    SampleDao dao = new SampleDao();
    List<MUsers> users = dao.findUsers(userName);
    return null;
  }
}
