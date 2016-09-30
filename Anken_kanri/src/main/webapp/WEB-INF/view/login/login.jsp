<!-- <%@ page language="java"  pageEncoding="UTF-8"%> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Language" content="ja" />
    <meta http-equiv="Content-Style-Type" content="text/css" />
    <link rel="stylesheet" type="text/css" href="../css/common.css" />
  <title>ログイン</title>
    <script type="text/javascript">
      /* テキストボックスの文字を消去 */
      function clr(){
        document.loginActionForm.userId.value="";
        document.loginActionForm.password.value="";
      }

    </script>
  </head>

  <body>
  <div class="wrap" >

  <header>
    <jsp:include page= "../common/header.jsp" />
  </header>

    <div class="contents">
    <s:form method="POST">
      <div class="login_form">

      <table>
        <tr>
          <td><u><b>ログイン画面</b></u><br></td>
        </tr>
        <tr>
          <td>担当者ID：</td>
          <td><html:text property="userId" size="30" maxlength="9" style="ime-mode:disabled;" /></td>
        </tr>
        <tr>
          <td>パスワード：</td>
          <td><html:password property="password" size="30" maxlength="8" style="ime-mode:disabled;" /></td>
        </tr>
      </table><br>

      <html:errors/><br><br>

        <div>
          <s:submit property="login" value="ログイン" />&nbsp;&nbsp;&nbsp;
          <input type="button" value="クリア" onclick="clr()" />
        </div>

      </div>
    </s:form>
    </div>

  <footer>
    <jsp:include page= "../common/footer.jsp" />
  </footer>

  </div>
  </body>
</html>