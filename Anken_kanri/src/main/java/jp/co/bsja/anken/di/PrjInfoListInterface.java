package jp.co.bsja.anken.di;

import java.util.List;

import jp.co.bsja.anken.form.PrjInfoListForm;
import jp.co.bsja.anken.form.SkillMasterForm;

public interface PrjInfoListInterface {

  public List<String> findMUsersName();

  public List<SkillMasterForm> findSkillInfo();

  public List<PrjInfoListForm> simpleSearch(PrjInfoListForm prjInfoListForm);
}
