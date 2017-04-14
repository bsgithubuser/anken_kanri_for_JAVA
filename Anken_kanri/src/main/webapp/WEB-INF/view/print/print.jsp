<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type = "text/css" rel = "stylesheet" href = "../css/common.css"/>
<title>Insert title here</title>
</head>
<!-- onLoadより、印刷ボタンとかをつけたほうがいいかも -->
<!-- 画面が開いてすぐに印刷Dが表示されるので、画面外に項目が表示されるとスクロールして確認できない -->
<body onLoad="window.print();">
<jsp:include page="../common/PrintHeader.jsp"/>

 <table bgcolor = "black">
  <thead>
   <tr bgcolor = "lightgreen">
    <th>案件名</th>
    <th>会社名</th>
    <th>担当</th>
    <th>発生日</th>
    <th>期間</th>
    <th>スキル</th>
    <th>概要</th>
   </tr>
  </thead>

  <tbody>
   <c:forEach items="${printPrjList}" var="list">
     <tr>
      <td bgcolor = "white"><pre><c:out value = "${f:h(list.prjName)}"/></pre></td>
      <td bgcolor = "white"><c:out value = "${f:h(list.cmpnName)}"/></td>
      <td bgcolor = "white"><c:out value = "${f:h(list.userName)}"/></td>
      <td bgcolor = "white"><c:out value = "${f:h(list.genDate)}"/></td>
      <!-- TODO 期間 -->
      <td bgcolor = "white"><c:out value = ""/></td>
      <td bgcolor = "white"><c:out value = "${f:h(list.skillName)}"/></td>
      <td bgcolor = "white"><pre><c:out value = "${f:h(list.orverview)}"/></pre></td>
     </tr>
    </c:forEach>
  </tbody>
 </table>

</body>
</html>