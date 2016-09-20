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

<jsp:include page="../common/header.jsp"/>
<p><u><b>担当者管理マスタ</b></u></p>
 <div align="right">
   <s:form method="POST">
     <s:submit value = "新規作成" property="showEntry" />
   </s:form>
 </div>
<table border="1" cellspacing="0" width="90%">
  <tr bgcolor = "lightgreen">
    <th>担当者ID</th>
    <th>担当者名</th>
    <th>管理者権限</th>
    <th>編集</th>
    <th>削除</th>
  </tr>
  <c:forEach var = "info" items = "${personalFormList}">
  <tr>
    <td align="center"><c:out value = "${info.userId}"/></td>
    <td align="center"><c:out value = "${info.userName}"/></td>
    <td align="center"><c:out value = "${info.adminiRight}"/></td>
    <td align="center">
    <s:form action="showUpdate" method="Post">
      <a href="javascript:edit('edit','${info.userId}')">編集</a>
    </s:form>
    </td>
    <td align="center">
    <s:form action="delete" method="Post">
      <a href="javascript:del('delete','${info.userId}')">削除</a>
    </s:form>
    </td>
  </tr>
  </c:forEach>
</table>
<s:form action="/menu/" method="Post">
  <br>
  <div align="right"><s:submit value="戻る"/></div>
</s:form>
<jsp:include page="../common/footer.jsp"/>
</body>
 <SCRIPT type="text/javascript" language="javascript">
  function edit(text, userId) {
    if (text == 'edit') {
      var id = document.createElement('input');
      id.type = 'hidden';
      id.name = 'userId';
      id.value = userId;
      document.forms[1].appendChild(id);
      document.forms[1].submit();
    }
  }
  function del(text, userId) {
    if (text == 'delete') {
      var test = document.createElement('input');
      test.type = 'hidden';
      test.name = 'userId';
      test.value = userId;
      document.forms[2].appendChild(test);
      document.forms[2].submit();
    }
  }
 </SCRIPT>
</html>