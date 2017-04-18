package jp.co.bsja.anken.form;


import java.util.List;

import jp.co.bsja.anken.entity.TProjInfo;

import org.seasar.framework.beans.util.BeanMap;

public class AnkenRegisterForm {

  /** スキルID . */
  public String[] skillId;

  /** スキルリスト . */
  public List<BeanMap> skillList;

  /** 担当者リスト . */
  public List<BeanMap> usersList;

  /** 案件登録者ID . */
  public int registantId;

  /** 案件ID . */
  public int id;

  /** 案件リスト . */
  public TProjInfo ankenList;

  /** スキルその他 . */
//  public String skillOther;

  /** 案件名 . */
  public String prjName;

  /** 担当者ID . */
  public int userId;

  /** 会社名 . */
  public String cmpnName;

  /** 発生日 . */
  public String genDate;

  /** 期間FROM . */
  public String periFrom;

  /** 期間TO . */
  public String periTo;

  /** 長期フラグ . */
  public String longTermFlg;

  /** 即日フラグ . */
  public String sameDayFlg;

  /** 随時フラグ . */
  public String anyTimeFlg;

  /** 延長フラグ . */
  public String extentionFlg;

  /** 概要 . */
  public String orverview;

  /** 案件情報その他 . */
  public String prjOther;

  /** 更新フラグ . */
  public int editFlg;

  /** 更新日時 . */
  public String updateDate;

  /** スキルその他フラグ. */
//  public String skillOtherFlg;

  /** スキルその他入力可否フラグ. */
//  public String disabledFlg;

}
