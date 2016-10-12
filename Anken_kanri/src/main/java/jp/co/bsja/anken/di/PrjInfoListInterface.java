package jp.co.bsja.anken.di;

import java.sql.Date;
import java.util.List;

import jp.co.bsja.anken.dto.SessionDto;
import jp.co.bsja.anken.form.AnkenRegisterForm;
import jp.co.bsja.anken.form.PrjInfoListForm;
import jp.co.bsja.anken.form.SkillMasterForm;

import org.seasar.framework.beans.util.BeanMap;

public interface PrjInfoListInterface {

  public List<String> findMUsersName();

  public List<SkillMasterForm> findSkillInfo();

  public List<PrjInfoListForm> search(PrjInfoListForm prjInfoListForm, SessionDto sessionDto);

  public List<BeanMap> integrateIdentityPrj(List<BeanMap> list);

  public void pullIdToARF(PrjInfoListForm prjInfoListForm, AnkenRegisterForm ankenRegisterForm);

  public void delete(PrjInfoListForm prjInfoListForm, SessionDto sessionDto);

  public Date convertTsIntoDt(Object date);

  public void pushSearchCondition(PrjInfoListForm prjInfoListForm, SessionDto sessionDto);

  public void pullSearchCondition(PrjInfoListForm prjInfoListForm, SessionDto sessionDto);
}
