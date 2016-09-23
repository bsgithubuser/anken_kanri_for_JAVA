package jp.co.bsja.anken.form;

import java.sql.Date;
import java.util.List;

import org.seasar.framework.beans.util.BeanMap;

public class AnkenRegisterForm {

  public int[] skill;

  public List<BeanMap> skillList;

  public List<BeanMap> usersList;

  public int id;

  public  List<BeanMap> ankenList;


  public String skillOther;

  public String prjName;

  public int userId;

  public int cmpnId;

  public Date genDate;

  public Date periFrom;

  public Date periTo;

  public boolean longTermFlg;

  public boolean sameDayFlg;

  public boolean anyTimeFlg;

  public boolean extentionFlg;

  public String orverview;

  public String prjOther;

}
