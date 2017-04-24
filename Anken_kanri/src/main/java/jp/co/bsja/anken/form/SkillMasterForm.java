package jp.co.bsja.anken.form;

import java.sql.Timestamp;
import java.util.List;

import org.seasar.framework.beans.util.BeanMap;
import org.seasar.struts.annotation.Arg;
import org.seasar.struts.annotation.Mask;
import org.seasar.struts.annotation.Maxlength;
import org.seasar.struts.annotation.Msg;
import org.seasar.struts.annotation.Required;

public class SkillMasterForm {
  /** スキルID . */
  public String skillId;

  /** スキル名 . */
  @Required(arg0 = @Arg(key = "スキル名", resource = false ))
  @Maxlength(maxlength = 40, arg0 = @Arg(key = "スキル名", resource = false ))
  public String skillName;

  /** スキル番号 . */
  @Mask(mask = "^[0-9]+$", msg = @Msg(key = "MSG_E00020", resource = true))
  public String skillNumber;

  /** スキル名重複数 . */
  public int overlap;

  /** DBに登録されているスキルの一覧情報 . */
  public List<BeanMap> skillList;

  /** 編集対象のスキルの情報 . */
  public BeanMap fetchSkillData;

  /** スキル情報の作成日時 . */
  public Timestamp createDate;

  /** スキル情報の更新日時 . */
  public Timestamp updateDate;
}
