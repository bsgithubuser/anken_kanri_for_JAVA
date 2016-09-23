<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="../css/common.css"/>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<p class="screenTytle"><u><b>メニュー</b></u></p>
<br>
<div class="menuItem" style="line-height:0"><a href="../prjList/"><u><b>・案件情報一覧</b></u></a></div>
<br>
<div class="menuItem" style="line-height:0"><a href="../prjRegist/"><u><b>・案件情報登録</b></u></a></div>
<br>
<div class="menuItem" style="line-height:0"><a href="../prjInfoBatch/"><u><b>・案件方法取込処理</b></u></a></div>
<br>
<c:if test="${sessionDto.admin == 0}">
<div class="menuItem" style="line-height:0"><a href="../masterMenu/"><u><b>・マスタ管理</b></u></a></div>
</c:if>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>