package jp.co.bsja.anken.di;

import jp.co.bsja.anken.dto.SessionDto;
import jp.co.bsja.anken.form.SkillMasterForm;

public interface SkillMasterInterface {
  /**
   * DB内の全てのスキル情報を取得する .
   *
   * @param skillMasterForm スキル管理マスタのアクションフォームクラス
   */
  public void fetchAllSkill(SkillMasterForm skillMasterForm);

  /**
   * 編集対象のスキル情報を取得する .
   *
   * @param skillMasterForm スキル管理マスタのアクションフォームクラス
   * @param sessionDto スキル管理マスタのデータ転送オブジェクトクラス
   */
  public String fetchEditData(SkillMasterForm skillMasterForm, SessionDto sessionDto);

  /**
   * スキル情報の登録、又はスキル名が重複しているとき登録画面に戻る .
   *
   * @param skillMasterForm スキル管理マスタのアクションフォームクラス
   * @param sessionDto スキル管理マスタのデータ転送オブジェクトクラス
   * @return 遷移先jsp
   */
  public String checkOverlapOrSave(SkillMasterForm skillMasterForm, SessionDto sessionDto);

  /**
   * スキル情報の登録を行う .
   *
   * @param skillMasterForm スキル管理マスタのアクションフォームクラス
   * @param sessionDto スキル管理マスタのデータ転送オブジェクトクラス
   * @return 登録処理成功件数
   */
  public int save(SkillMasterForm skillMasterForm, SessionDto sessionDto);

  /**
   * 対象のスキル情報を削除する .
   *
   * @param skillMasterForm スキル管理マスタのアクションフォームクラス
   */
  public void delete(SkillMasterForm skillMasterForm);

  /**
   * 登録処理後の遷移先を判断する .
   *
   * @param result 登録処理成功件数
   * @param skillMasterForm スキル管理マスタのアクションフォームクラス
   * @return 遷移先jsp
   */
  public String decideDestination(int result, SkillMasterForm skillMasterForm);

}
