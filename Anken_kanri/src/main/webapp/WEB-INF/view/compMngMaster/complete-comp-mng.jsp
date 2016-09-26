<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="../css/common.css" />
<title>会社情報管理マスタ</title>
</head>
<body>
  <s:form action="goToEntryJsp" method="Post" />
  <s:form action="edit" method="Post" />
  <div class="header">
    <jsp:include page="../common/header.jsp" />
  </div>
  <br>
  <p class="screenTytle">
    <u><b>会社情報管理マスタ</b></u>
  </p>
  <br>
  <br>
  <br>
  <div class="message">登録が完了しました！</div>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <a href="index" class="listLink"><u><b>一覧画面へ戻る</b></u></a>
  &emsp;&emsp;&emsp;&emsp;
  <a href="javascript:submit('${idItemString}')"><u><b>登録画面へ戻る</b></u></a>
  <br>
  <br>
  <br>
  <br>
  <div class="footer">
    <jsp:include page="../common/footer.jsp" />
  </div>
</body>

<SCRIPT type="text/javascript" language="javascript">
  /*
   * 登録画面に遷移します。会社IDがある場合はそれを基にして入力欄に値を表示させます。
   */
  function submit(num){
    if (num == "") {
      document.forms[0].submit()
    } else {
      var id = document.createElement('input');
      id.type = 'hidden';
      id.name = 'idItemInteger';
      id.value = num;
      document.forms[1].appendChild(id);
      document.forms[1].submit();
    }
  }
</SCRIPT>
</html>