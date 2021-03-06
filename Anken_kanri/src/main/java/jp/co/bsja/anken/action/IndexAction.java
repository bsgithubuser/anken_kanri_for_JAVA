package jp.co.bsja.anken.action;

import java.util.List;

import javax.annotation.Resource;

import jp.co.bsja.anken.di.SampleInterface;
import jp.co.bsja.anken.dto.SessionDto;
import jp.co.bsja.anken.form.IndexForm;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;


/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
public class IndexAction {

  @Resource
  protected SessionDto sessionDto;

  @ActionForm
  @Resource
  protected IndexForm indexForm;


  /**
   * indexのコントローラ処理を実施します .
   *
   * @return 遷移先JSP名
   */
  @Execute(validator = false)
  public String index() {

    sessionDto.str = "せっしょん";

    indexForm.sessionDto = sessionDto;

    SingletonS2ContainerFactory.init();
    S2Container container = SingletonS2ContainerFactory.getContainer();
    SampleInterface sample = (SampleInterface)container.getComponent("SampleImpl");
    List<String> list = sample.makeSample("test");

    return "index.jsp";
  }

  /**
   * 動作検証用テストメソッド .
   * @return 遷移先JSP名
   */
  @Execute(validator = false)
  public String test() {


    indexForm.sessionDto = sessionDto;



    return "index.jsp";
  }

}
