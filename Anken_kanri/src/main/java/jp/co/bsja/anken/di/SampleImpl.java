package jp.co.bsja.anken.di;

import java.util.List;

import jp.co.bsja.anken.dao.SampleDao;
import jp.co.bsja.anken.entity.MSkill;

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
    List<MSkill> users = dao.findUsers(userName);
    System.out.println(users.get(0));
    return null;
  }
}
