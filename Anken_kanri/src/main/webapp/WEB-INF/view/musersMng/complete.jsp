<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type = "text/css" rel = "stylesheet" href = "../css/common.css"/>
<title>Insert title here</title>
</head>
<body>
  <img src = "../img/bs_logo.jpg">
  <jsp:include page="../common/header.jsp"/>
  <h2>登録が完了しました！</h2><br>
  <html:link href = "index" >一覧画面に戻る</html:link>
  &nbsp;&nbsp;&nbsp;&nbsp;
  <html:link href = "showEntry" >登録画面に戻る</html:link>
  <jsp:include page="../common/footer.jsp"/>
</body>
</html>