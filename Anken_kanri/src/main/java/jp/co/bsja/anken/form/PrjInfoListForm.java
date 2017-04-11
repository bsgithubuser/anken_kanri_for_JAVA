package jp.co.bsja.anken.form;

import java.sql.Timestamp;
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

  /** 画面表示用期間開始日 . */
  public String periStDtDummy;

  /** 期間開始日 . */
  public Timestamp periStDt;

  /** 画面表示用期間終了日 . */
  public String periEnDtDummy;

  /** 期間終了日 . */
  public Timestamp periEnDt;

  /** 会社名 . */
  public String compName;

  /** 長期フラグ . */
  public Boolean lngTrmFlg;

  /** 即日フラグ . */
  public Boolean smDyFlg;

  /** 随時フラグ . */
  public Boolean anyTmFlg;

  /** 概要 . */
  public String serchOverview;

  /** 概要 . */
  public String overview;

  /** 一覧表示用概要 . */
  public String displayOverview;

  /** スキルID . */
  public List<String> skillId;

  /** スキルID . */
  public String selectedSkills;

  /** 延長 . */
  public String exteFlg = "none";

  /** 検索方法 . */
  public String kindOfSerch;

  /** 検索したスキル名 . */
  public String skillName;

  /** 期間 . */
  public String prjPeriod;

  /** 延長マーク . */
  public String extendFlg;

  /** 印刷 . */
  public String printFlg;

  /** 画面再表示用_タブ名 . */
  public String tabName = "tab2";

  /** 更新日時 . */
  public Timestamp updateDate;

  /** 画面から受け取る更新日時 . */
  public String updtDate;

}
