package jp.co.bsja.anken.di;

import java.util.List;

import jp.co.bsja.anken.form.PersonalForm;

public interface MUsersMngInterface {
  public List<PersonalForm> showMusers();

  public PersonalForm showMuser(PersonalForm form);

  public int registrationUpdate(PersonalForm form);

  public void deleteUser(PersonalForm form);

}
