package jp.co.bsja.anken.di;

import static jp.co.bsja.anken.common.CommonFunction.*;

import java.util.List;

import jp.co.bsja.anken.dao.Dao;
import jp.co.bsja.anken.dao.SampleDao;

import org.seasar.framework.beans.util.BeanMap;

public class SampleImpl implements SampleInterface {
  /**
   * サンプルソース .
   *
   * @param userName テスト
   * @return 画面表示情報とか
   */
  @Override
  public List<String> makeSample(String userName) {
    // ここにビジネスロジックを作りこむ
    SampleDao dao = new SampleDao();
    //List<MUsers> users = dao.findSampls(userName);

    String str = dao.selectBySqlSingleString("SELECT nextval('PRJ_ID_SEQ')");
    //シーケンス番号取得サンプル
    int seq1 = dao.getPrjIdSeq();
    int seq2 = dao.getPrjSkillIdSeq();
    int seq3 = dao.getCmpnIdSeq();
    int seq4 = dao.getSkillIdSeq();
    int seq5 = dao.getUserIdSeq();
    List<BeanMap> list = dao.selectAll(dao.TABLE_M_SKILL);

    //登録サンプル
//    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//    MSkill skillEntity = new MSkill();
//    skillEntity.skillId = seq4;
//    skillEntity.skillName = "スキル名";
//    skillEntity.createDate = timestamp;
//    skillEntity.updateDate = timestamp;
//    int insertCount = dao.insert(skillEntity);
//
//    //更新サンプル
//    MSkill skillUpdateEntity = new MSkill();
//    skillUpdateEntity.skillId = 1;
//    skillUpdateEntity.skillName = "技能";
//    skillUpdateEntity.createDate = timestamp;
//    skillUpdateEntity.updateDate = timestamp;
//    int updateCount = dao.uptate(skillUpdateEntity);
//
//    //削除サンプル
//    MSkill skillDeleteEntity = new MSkill();
//    skillDeleteEntity.skillId = 2;
//    dao.delete(skillDeleteEntity);

    //存在チェックサンプル
    int count = dao.existCheck(Dao.TABLE_M_SKILL, "SKILL_ID", 1);
    int locked= dao.hasLocked(Dao.TABLE_T_PROJ_INFO, "PRJ_ID", "1", getBaseDt());
    return null;
  }
}
