package jp.co.bsja.anken.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.seasar.framework.beans.util.BeanMap;
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

@Component(instance = InstanceType.SESSION)
public class SessionDto implements Serializable {
  private static final long serialVersionUID = 1L;

  public String str = "";

  /* ログイン情報 */
  public String userId;
  public String userName;
  public String password;
  public boolean loginState;
  public String admin;

  /* スキル管理マスタ */
  public String skillId;
  public String skillName;
  public String skillNumber;
  public Timestamp createDate;
  public Timestamp updateDate;

  /* 案件情報一覧 */
  public List<String> userNameList;
  public List<String> prjSkillId;
  public String periStDtDummy;
  public Timestamp periStDt;
  public String periEnDtDummy;
  public Timestamp periEnDt;
  public String compName;
  public Boolean lngTrmFlg;
  public Boolean smDyFlg;
  public Boolean anyTmFlg;
  public String overview;
  public String selectedSkills;
  public String exteFlg;
  public String kindOfSerch;
  public String tabName;
  public String serchOverview;
  /* 印刷用 */
  public List<BeanMap> searchResultList;
  public String printAnknIds;
  public Boolean bulkFlag;

  /* 案件情報登録 */
  public List<BeanMap> skillList;
  public List<BeanMap> usersList;
  public List<BeanMap> cmpnList;
  public int editFlg;
}
