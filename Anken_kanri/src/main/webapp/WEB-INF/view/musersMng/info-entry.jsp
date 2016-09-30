<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../common/common.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type = "text/css" rel = "stylesheet" href = "../css/common.css"/>
<title>担当者管理マスタ</title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<p><u><b>担当者管理マスタ</b></u></p>
<html:errors />

<s:form method="POST">
<table>
  <tr>
    <th>担当者ID:</th>
    <c:if test="${userId == 0}" var="flg">
    <td><html:text styleClass =""  property="userId" size="20" readonly="true" value=""/></td>
    </c:if>
    <c:if test="${!flg}">
    <td><html:text styleClass =""  property="userId" size="20" readonly="true" value="${userId}"/></td>
    </c:if>
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
    <td width="200"><html:password property="certifiedPass" size="20" maxlength="8" value="${certifiedPass}"/></td>
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
                   <input type="hidden" name="date" value="${date}">
<br>
${f:h(errorMessage)}
<br>
  <html:hidden property="mode" value="${mode}" />
  <s:submit value="登録" property="entryUpdate"/><input type="button" value="戻る" onclick="location.href='index'" /><input type="button"  value="クリア" onclick="clearText()" />
  </s:form>
  <jsp:include page="../common/footer.jsp"/>
</body>

<SCRIPT type="text/javascript" language="javascript">
/*
 * クリアボタン押下後、入力欄を空白にします。
 */
function clearText(){
  document.forms[0].userName.value="";
  document.forms[0].passWord.value="";
  document.forms[0].certifiedPass.value="";
}
</SCRIPT>
</html>