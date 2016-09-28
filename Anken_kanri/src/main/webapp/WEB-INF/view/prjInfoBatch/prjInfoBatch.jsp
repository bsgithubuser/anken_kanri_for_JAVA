<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${f:url("/css/common.css")}" />
<title>案件情報取込処理</title>
</head>
<body>
<jsp:include page="../common/header.jsp" />
<s:form method="POST" enctype="multipart/form-data">
<div class="menuItem">
  <h2>案件情報取込処理</h2>
  <p><html:file property="formFile" /></p>
  <p><html:errors/></p>
  <p>
    <c:if test="${f:h(errorMsg) != null}">
      ${f:br(errorMsg)}
    </c:if>
  </p>
  <p>
    <s:submit value="開始" property="upload"
      onclick="if(confirm('一括登録しますか？')) {
        return true;
      } else {
        return false;}" />
    <input type="button" value="戻る" onclick="location.href='../menu'" />
  </p>
</div>
</s:form>
<jsp:include page="../common/footer.jsp" />
</body>
</html>