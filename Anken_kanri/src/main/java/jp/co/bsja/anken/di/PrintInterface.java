package jp.co.bsja.anken.di;

import java.io.FileNotFoundException;
import java.io.IOException;

import jp.co.bsja.anken.dto.SessionDto;
import jp.co.bsja.anken.form.PrintForm;

public interface PrintInterface {

  public void createPrintList(PrintForm printForm, SessionDto sessionDto);

  public void poiTest() throws FileNotFoundException, IOException;

}
