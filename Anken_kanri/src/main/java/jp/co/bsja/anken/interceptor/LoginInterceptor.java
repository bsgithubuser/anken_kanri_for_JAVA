package jp.co.bsja.anken.interceptor;

import javax.annotation.Resource;

import jp.co.bsja.anken.dto.SessionDto;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.struts.annotation.Execute;

public class LoginInterceptor extends AbstractInterceptor {
  @Resource
  protected SessionDto sessionDto;

  /**
   * @Executeアノテーションにて
   * セッション情報があるかチェックを行う .
   *
   * @return リクエスト or /login/index
   */
  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    if (invocation.getMethod().isAnnotationPresent(Execute.class)) {
      if (sessionDto.userId == null
          && sessionDto.userName == null
          && sessionDto.password == null) {
        return "/login/index";
      }
    }
    return invocation.proceed();
  }
}