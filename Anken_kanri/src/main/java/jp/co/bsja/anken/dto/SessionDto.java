package jp.co.bsja.anken.dto;

import java.io.Serializable;
import java.sql.Timestamp;

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
  public Timestamp createDate;
  public Timestamp updateDate;
}
