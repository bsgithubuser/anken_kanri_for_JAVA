<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type = "text/css" rel = "stylesheet" href = "../css/common.css"/>

<script type="text/javascript">

function ChangeTab(tabname) {
  // 全部消す
  document.getElementById('tab1').style.display = 'none';
  document.getElementById('tab2').style.display = 'none';
  // 指定箇所のみ表示
  document.getElementById(tabname).style.display = 'block';
}

function hide(title1,title2){
  document.getElementById(title1).style.display = 'none';
  document.getElementById(title2).style.display = '';
}

</script>

<title>Insert title here</title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>

<p><u><b>案件情報一覧</b></u></p>
<s:form method="POST">
<div>

  <div id="tab1">
  <!-- 詳細検索  -->
  <p>
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
      <html:checkbox property="periFlg" value="0"/>長期
      <html:checkbox property="periFlg" value="1"/>即日
      <html:checkbox property="periFlg" value="2"/>随時
      </td>
      </tr>
    <tr>
      <th>延長</th>
      <td></td>
      <td></td>
      <td><html:checkbox property="exteFlg" value="1"/>あり<html:checkbox property="exteFlg" value="0"/>なし</td>
    </tr>
    <tr>
      <th>スキル</th>
      <td colspan="3">
      <c:forEach var = "info" items = "${skillMasterFormList}" varStatus="infoNo">
      <html:checkbox property="exteFlg" value="${info.skillId}"/>${f:h(info.skillName)}
      <c:if test="${infoNo.count %5 == 0}"></br></c:if>
      </c:forEach>
      </td>
    </tr>
    <tr>
      <th>概要</th>
      <td><html:text property="periStDt" size="20" maxlength="30"/></td>
      <td></td>
      <td align="right"><s:submit value = "検索" property="select" />
      <input type="button" id="simpleSearch" onclick="location.href='#tab2';ChangeTab('tab2');hide('simpleSearch','detailedSearch');return false;" value="簡易検索" /></td>
    </tr>
    </table>
  </p>
  </div>

  <!-- 簡易検索  -->
  <div id="tab2">
    <p>
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
    <td align="right"><s:submit value = "検索" property="select" />
    <input type="button" id="detailedSearch" onclick="location.href='#tab1';ChangeTab('tab1');hide('detailedSearch','simpleSearch');return false;" value="詳細検索"/></td>
    </tr>
    </table>
    </p>
  </div>

</div>

<!-- デフォルトのテーブルとボタンを指定 -->
<script type="text/javascript">
   ChangeTab('tab1');
   hide('detailedSearch','simpleSearch');
</script>

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

</s:form>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>