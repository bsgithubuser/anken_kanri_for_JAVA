package jp.co.bsja.anken.action;

import javax.annotation.Resource;

import jp.co.bsja.anken.di.SkillMasterInterface;
import jp.co.bsja.anken.dto.SessionDto;
import jp.co.bsja.anken.form.SkillMasterForm;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;


public class SkillMasterAction {

  public SessionDto sessionDto;

  @ActionForm
  @Resource
  protected SkillMasterForm skillMasterForm;

  /**
   * スキル管理マスタ一覧画面への実行メソッド .
   * @return 遷移先jsp
   */
  @Execute(validator = false)
  public String index() {
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    SkillMasterInterface skillMaster =
              (SkillMasterInterface)container.getComponent("SkillMasterImpl");
    skillMaster.fetchAllSkill(skillMasterForm);

    return "skill-master.jsp";
  }

  /**
   * マスタメニュー画面への遷移 実行メソッド .
   * @return 遷移先jsp
   */
  @Execute(validator = false)
  public String returnMenu() {
    return "/masterMenu/";
  }

  /**
   * スキル管理マスタ 登録画面への遷移 実行メソッド .
   * @return 遷移先jsp
   */
  @Execute(validator = false)
  public String regist() {
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    SkillMasterInterface skillMaster =
              (SkillMasterInterface)container.getComponent("SkillMasterImpl");
    String destination = skillMaster.fetchEditData(skillMasterForm,sessionDto);

    return destination;
  }

  /**
   * スキル管理マスタ 登録処理/スキル名重複判定 実行メソッド .
   * @return 遷移先jsp
   */
  @Execute(validator = true,input = "skill-master-regist.jsp")
  public String save() {

    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    SkillMasterInterface skillMaster =
              (SkillMasterInterface)container.getComponent("SkillMasterImpl");
    String destination = skillMaster.checkOverlapOrSave(skillMasterForm, sessionDto);

    return destination;
  }

  /**
   * スキル管理マスタ 重複スキル名登録処理 実行メソッド .
   * @return 遷移先jsp
   */
  @Execute(validator = false)
  public String saveOverlapName() {
    //セッションに格納したIDとスキル名とスキル番号を受け取る
    skillMasterForm.skillId = sessionDto.skillId;
    skillMasterForm.skillName = sessionDto.skillName;
    skillMasterForm.skillNumber = sessionDto.skillNumber;

    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    SkillMasterInterface skillMaster =
              (SkillMasterInterface)container.getComponent("SkillMasterImpl");
    int save = skillMaster.save(skillMasterForm,sessionDto);
    String destination = skillMaster.decideDestination(save,skillMasterForm);

    return destination;
  }

  /**
   * スキル管理マスタ 削除処理 実行メソッド .
   * @return 遷移先jsp
   */
  @Execute(validator = false)
  public String delete() {
    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    SkillMasterInterface skillMaster =
              (SkillMasterInterface)container.getComponent("SkillMasterImpl");
    skillMaster.delete(skillMasterForm);

    return "/skillMaster/";
  }
}
