package jp.co.bsja.anken.form;

import java.util.List;

public class PrjInfoListForm {

  /** 担当者名一覧保持変数 . */
  public List<String> userNameList;

  /** スキル管理マスタ一覧情報保持変数 . */
  public List<SkillMasterForm> skillMasterFormList;

  /** 案件情報一覧情報保持変数 . */
  public List<PrjInfoListForm> prjInfoListFormList;

  /** 案件情報ID . */
  public String prjId;

  /** 案件名 . */
  public String prjName;

  /** 会社ID . */
  public String cmpId;

  /** 会社名 . */
  public String cmpName;

  /** 担当者名 . */
  public String userName;

  /** 発生日 . */
  public String genDate;

  /** 期間開始日 . */
  public String periStDt;

  /** 期間終了日 . */
  public String periEnDt;

  /** 会社名 . */
  public String compName;

  /** 期間フラグ . */
  public String periFlg;

  /** スキル名 . */
  public String skillName;

  /** 延長 . */
  public String exteFlg;


}
