<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type = "text/css" rel = "stylesheet" href = "../css/common.css"/>
<title>案件情報一覧・簡易印刷画面</title>
</head>
<body onLoad="window.print();">
<jsp:include page="../common/PrintHeader.jsp"/>

 <table bgcolor = "white" border="1" bordercolor="black" cellspacing="0" width="100%" style= "table-layout: fixed;">
  <thead>
   <tr bgcolor = "lightgreen">
    <th width="30%">案件情報</th>
    <th width="15%">発生日/期間</th>
    <th width="55%">概要</th>
   </tr>
  </thead>

  <tbody>
   <c:forEach items="${printPrjList}" var="list">
     <tr>
      <td bgcolor = "white" valign="top" style= "word-break : break-all;">
      【案件名】<c:out value = "${f:h(list.prjName)}"/><BR>
      【会社名】<c:out value = "${f:h(list.cmpnName)}"/><BR>
      【スキル】<c:out value = "${f:h(list.skillName)}"/><BR>
      【担当】  <c:out value = "${f:h(list.userName)}"/>
      </td>
      <td bgcolor = "white" valign="top">
      【発生日】<BR>
       <c:out value = "${f:h(list.genDate)}"/><BR>
      【期間】<BR>
       <c:out value = "${f:h(list.periFrom)}"/>
       <BR>&nbsp;&nbsp;&nbsp;&nbsp;～<BR>
       <c:out value = "${f:h(list.periTo)}"/>
      </td>
      <td bgcolor = "white" valign="top" style= "word-break : break-all;"><c:out value = "${f:h(list.orverview)}"/></td>
     </tr>
    </c:forEach>
  </tbody>
 </table>

</body>
</html>