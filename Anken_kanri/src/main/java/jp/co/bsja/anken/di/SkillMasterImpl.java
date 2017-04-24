package jp.co.bsja.anken.di;

import static jp.co.bsja.anken.common.CommonFunction.*;

import java.sql.Timestamp;
import java.util.List;

import jp.co.bsja.anken.dao.SkillMasterDao;
import jp.co.bsja.anken.dto.SessionDto;
import jp.co.bsja.anken.entity.MSkill;
import jp.co.bsja.anken.form.SkillMasterForm;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.util.BeanMap;
import org.seasar.struts.util.ActionMessagesUtil;
import org.seasar.struts.util.RequestUtil;

public class SkillMasterImpl implements SkillMasterInterface {

  @Override
  public void fetchAllSkill(SkillMasterForm skillMasterForm) {
    //検索処理
    SkillMasterDao dao = new SkillMasterDao();
    List<BeanMap> list = dao.findAllSkill("M_SKILL", "skill_id");

    skillMasterForm.skillList = list;
  }

  @Override
  public String fetchEditData(SkillMasterForm skillMasterForm, SessionDto sessionDto) {
    //skillIdに値が入っている = 編集
    //値が入っていなければ新規なので、何もしない
    SkillMasterDao dao = new SkillMasterDao();
    String destination = "skill-master-regist.jsp";
    if (!empty(skillMasterForm.skillId)) {
      //スキルIDから、スキル名を検索する
      String sql = "SELECT * FROM M_SKILL WHERE skill_id = " + skillMasterForm.skillId;
      BeanMap data = dao.fetchData(sql);

      if (!empty(data)) {
        skillMasterForm.fetchSkillData = data;
        sessionDto.createDate = (Timestamp) data.get("createDate");
        sessionDto.updateDate = (Timestamp) data.get("updateDate");
      } else {
        ActionMessages errors = new ActionMessages();
        errors.add(ActionMessages.GLOBAL_MESSAGE,
                            new ActionMessage("MSG_E00009", skillMasterForm.skillName));
        ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
        destination = "/skillMaster/";
      }

    }
    return destination;
  }

  @Override
  public String checkOverlapOrSave(SkillMasterForm skillMasterForm, SessionDto sessionDto) {
	    //重複確認処理
	    SkillMasterDao dao = new SkillMasterDao();
	    int count = dao.checkOverlap("M_SKILL", "skill_name", skillMasterForm.skillName);
	    String destination = "skill-master-regist.jsp";

	    //返された count の値が1以下なら、登録処理を開始する
	    if (count < 1) {
	      int save = save(skillMasterForm, sessionDto);
	      destination = decideDestination(save, skillMasterForm);
	    } else {
	      //overlap が1以上なら、重複するスキル名で登録するか確認
	      //一度登録画面に戻る
	      sessionDto.skillId = skillMasterForm.skillId;
	      sessionDto.skillName = skillMasterForm.skillName;
	      sessionDto.skillNumber = skillMasterForm.skillNumber;

	      skillMasterForm.overlap = count;
	    }

	    return destination;
  }

  @Override
  public int save(SkillMasterForm skillMasterForm, SessionDto sessionDto) {
    //entityインスタンス
    MSkill entity = new MSkill();
    SkillMasterDao dao = new SkillMasterDao();

    Timestamp baseDt = getBaseDt();

    //入力されたスキル名と更新日時をentityに格納する
    entity.skillName = skillMasterForm.skillName;
    int number = 0;
    if (!empty(skillMasterForm.skillNumber)) {
      // DBに登録するため、スキル番号をint型に修正する
      number = Integer.parseInt(skillMasterForm.skillNumber);
    }
    entity.skillNumber = number;
    entity.updateDate = baseDt;
    int result = 0;

    //スキルIDがないとき = 新規登録時
    //スキルID と 作成日時を取得し、entityに格納する
    String skillId = skillMasterForm.skillId;
    if (empty(skillId)) {
      //シーケンス番号取得
      int seq = dao.getSkillIdSeq();
      entity.skillId = seq;
      entity.createDate = baseDt;
      //登録処理
      result = dao.insert(entity);

    } else {
      //編集時
      //編集画面時点でのデータの更新日時
      Timestamp beforeDate = sessionDto.updateDate;

      //排他制御確認
      //0 が返却されたときのみ更新処理を行う
      int isLocked = dao.hasLocked("M_SKILL", "skill_id", skillId, beforeDate);
      if (isLocked == 0) {
      //DBに登録するため、スキルIDをint型に修正する
        int id = Integer.parseInt(skillId);
        entity.skillId = id;
        entity.createDate = sessionDto.createDate;
        //更新処理
        result = dao.uptate(entity);
        //result = 0のとき、削除済み
        if (result == 0) {
          result = -1;
        }
      }
    }

    return result;
  }

  @Override
  public String decideDestination(int result, SkillMasterForm skillMasterForm) {
    String destination = null;
    //resultの値が0でなければ登録成功
    //登録成功画面に遷移する
    if (0 < result) {
      destination =  "skill-master-saved.jsp";
    } else {
      //登録失敗 登録画面に戻り、エラーを表示する
      //result = -1 のとき、削除済みエラー
      if (result == -1) {
        ActionMessages errors = new ActionMessages();
        errors.add(ActionMessages.GLOBAL_MESSAGE,
                            new ActionMessage("MSG_E00009", skillMasterForm.skillName));
        ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
      } else {
      //排他制御エラー
        ActionMessages errors = new ActionMessages();
        errors.add(ActionMessages.GLOBAL_MESSAGE,
                            new ActionMessage("MSG_E00006", skillMasterForm.skillName));
        ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
      }
      destination =  "skill-master-regist.jsp";
    }
    return destination;
  }

  @Override
  public void delete(SkillMasterForm skillMasterForm) {
    //案件スキルテーブル検索
    SkillMasterDao dao = new SkillMasterDao();
    //検索用に、スキルIDをint型に直す
    int skillId = Integer.parseInt(skillMasterForm.skillId);
    int existCnt = dao.existCheck("T_PROJ_SKILL", "skl_id", skillId);

    //existCnt で返却された値が0なら削除処理を行う
    if (existCnt < 1) {
      MSkill entity = new MSkill();
      //エンティティにスキルIDを格納
      entity.skillId = skillId;
      //削除処理
      int delResult = dao.delete(entity);
      //delResult = 0 なら削除済み
      if (delResult == 0) {
        ActionMessages errors = new ActionMessages();
        errors.add(ActionMessages.GLOBAL_MESSAGE,
                            new ActionMessage("MSG_E00009", skillMasterForm.skillName));
        ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
      }

    } else {
      //1 以上の場合、エラーを表示する
      ActionMessages errors = new ActionMessages();
      errors.add(ActionMessages.GLOBAL_MESSAGE,
                          new ActionMessage("MSG_E00005", skillMasterForm.skillName));
      ActionMessagesUtil.addErrors(RequestUtil.getRequest(), errors);
    }
  }
}
