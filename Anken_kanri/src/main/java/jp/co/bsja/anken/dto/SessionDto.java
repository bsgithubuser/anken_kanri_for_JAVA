package jp.co.bsja.anken.dto;

import java.io.Serializable;

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
}
