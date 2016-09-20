<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../common/common.jsp"/>
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
<html:errors />
<s:form method="POST">
<table>
  <tr>
    <th>担当者ID:</th>
    <td><html:text styleClass =""  property="userId" size="20" readonly="true" value="${userId}"/></td>
  </tr>
  <tr>
    <th width="200">担当者名:<font color="#FF0000">*</font></th>
    <td width="200"><html:text property="userName" size="20" maxlength="30" value="${userName}" /></td>
  </tr>
  <tr>
    <th width="200">パスワード:<font color="#FF0000">*</font></th>
    <td width="200"><html:password property="passWord" size="20" maxlength="8" value="${passWord}" /></td>
  </tr>
  <tr>
    <th width="200">確認用:<font color="#FF0000">*</font></th>
    <td width="200"><html:password property="certifiedPass" size="20" maxlength="8"/></td>
  </tr>
  <tr>
    <th width="200">管理者権限:</th>
    <td width="200">
      <html:select property="adminiRight" value="${adminiRight}">
      <html:option value="0">管理者</html:option>
      <html:option value="1">一般</html:option>
      <html:option value="9">開発者</html:option>
      </html:select>
    </td>
  </tr>
</table>
<br>
${f:h(errorMessage)}
<br>
  <html:hidden property="mode" value="${mode}" />
  <s:submit value="登録" property="entryUpdate"/><s:submit value="戻る" property="index" /><s:submit  value="クリア" property="clear" />
  </s:form>
  <jsp:include page="../common/footer.jsp"/>
</body>
</html>