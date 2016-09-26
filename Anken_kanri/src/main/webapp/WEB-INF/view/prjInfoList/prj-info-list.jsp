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
<p><u><b>案件情報一覧</b></u></p>
<s:form method="POST">
<table Border Bordercolor="black" Rules="NONE">
  <tr>
    <th>担当者名</th>
    <td><html:select property="userNameList" value="" ><html:options property="userNameList"/></html:select></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <th>発生日</th>
    <td><html:text property="periStDt" size="20" maxlength="30"/></td>
    <td>～</td>
    <td><html:text property="periEnDt" size="20" maxlength="30"/></td>
  </tr>
  <tr>
    <th>会社名</th>
    <td><html:text property="compName" size="20" maxlength="30"/></td>
    <td></td>
    <td>
    <div id='visi'>
    <html:checkbox property="periFlg" value="0"/>長期
    <html:checkbox property="periFlg" value="1"/>即日
    <html:checkbox property="periFlg" value="2"/>随時
    </div>
    </td>
     <s:submit value = "検索" property="select" />
      <input type="button" value="詳細検索" onclick="hyoji2(0)">
  </tr>
  <div id='visi'>
    <tr>
      <th>延長</th>
      <td></td>
      <td></td>
      <td><html:checkbox property="exteFlg" value="1"/>あり<html:checkbox property="exteFlg" value="0"/>なし</td>
    </tr>
    <tr>
      <th>スキル</th>
        <c:forEach var = "info" items = "${skillMasterFormList}" varStatus="infoNo">
          <html:checkbox property="exteFlg" value="${info.skillId}"/>${f:(info.skillId)}
          <c:if test="${infoNo.count==5}">
            <br>
          </c:if>
        </c:forEach>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
      <th>概要</th>
      <td><html:text property="periStDt" size="20" maxlength="30"/></td>
      <td></td>
      <td></td>
    </tr>
  </div>
</table>
<table>
  <tr bgcolor = "lightgreen">
    <th>印刷</th>
    <th>編集</th>
    <th>削除</th>
    <th>案件ID</th>
    <th>案件名</th>
    <th>会社名</th>
    <th>担当</th>
    <th>発生日</th>
    <th>期間</th>
    <th>スキル</th>
    <th>延長</th>
  </tr>
  <c:forEach var = "prjInfo" items = "${prjInfoListFormList}">
  <tr>
    <td align="center">
    <html:checkbox property="exteFlg" value="1"/>印刷
    </td>
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
    <td align="center"><c:out value = "${prjInfo.prjId}"/></td>
    <td align="center"><c:out value = "${prjInfo.prjName}"/></td>
    <td align="center"><c:out value = "${prjInfo.cmpName}"/></td>
    <td align="center"><c:out value = "${prjInfo.userName}"/></td>
    <td align="center"><c:out value = "${prjInfo.genDate}"/></td>
    <td align="center"><c:out value = "${prjInfo.periFlg}"/></td>
    <td align="center"><c:out value = "${prjInfo.skillName}"/></td>
    <td align="center"><c:out value = "${prjInfo.exteFlg}"/></td>

  </tr>
  </c:forEach>
</table>
 <SCRIPT type="text/javascript" language="javascript">
 function hyoji2(num)
 {
   if (num == 0)
   {
     document.getElementById("visi").style.visibility="visible";
   }
   else
   {
     document.getElementById("visi").style.visibility="hidden";
   }
 }
 </SCRIPT>

</s:form>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>