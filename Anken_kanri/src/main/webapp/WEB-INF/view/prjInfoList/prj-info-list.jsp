<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type = "text/css" rel = "stylesheet" href = "../css/common.css"/>
<link rel="stylesheet" href="../tablesorter/themes/blue/style.css" type="text/css" media="print, projection, screen" />
<script src="../tablesorter/jquery-latest.js" type="text/javascript"></script>
<script src="../tablesorter/jquery.tablesorter.min.js" type="text/javascript"></script>
<script src="../tablesorter/jquery.metadata.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready(function()
  {
    $("#searchResults").tablesorter({
        headers: {
            0: { sorter: false },
            1: { sorter: false },
            2: { sorter: false },
            8: { sorter: false },
            9: { sorter: false },
            10: { sorter: false }
        }
    });
  }
);
/*
 * 初期表示時、タブの表示、スキルのチェック状態、延長チェック
 */
function toInitialDisp(){
  var holdTabNM = document.getElementsByName('tabName')[0].value;
  //全部消す
  document.getElementById('tab1').style.display = 'none';
  document.getElementById('tab2').style.display = 'none';
  //指定箇所のみ表示
  document.getElementById(holdTabNM).style.display = 'block';

  var skls = document.getElementsByName('selectedSkills')[0].value;
  var list = skls.split(",");
  var sklList = document.getElementsByName('skillId');
  /* 保持されているIDの数繰り返す */
  for (var i=0; i<list.length; i++){
    if(list[i] != ""){
      /* 表示されているスキルのうち、保持しているIDと一致するものにチェックを入れる */
      for(var j=0; j<sklList.length; j++){
        var id = sklList[j].value;
        if(id == list[i]){
          document.getElementsByName('skillId')[j].checked = true;
        }
      }
    }
  }

}

/*
 * タブ切り替え
 */
function ChangeTab(tabname) {
  // 全部消す
  document.getElementById('tab1').style.display = 'none';
  document.getElementById('tab2').style.display = 'none';
  // 現在のタブ名を保持
  var holdTabNM = document.getElementsByName('tabName');
  var length = holdTabNM.length;
  var i = 0;
  while (i < length) {
    holdTabNM[i].value = tabname;
    i++;
  }
//指定箇所のみ表示
  document.getElementById(tabname).style.display = 'block';
}

function hide(title1,title2){
  document.getElementById(title1).style.display = 'none';
  document.getElementById(title2).style.display = '';
}

/*
 * ダミーフィールドに入力された日付を、実際submitするフィールドに格納する
 */
function setDt(dummy, willSent, index){
  var dummysValue = document.getElementsByName(dummy)[index].value;
  var sentField = document.getElementsByName(willSent)[index];
  sentField.value = dummysValue;
}

/*
 * チェックしたスキルのIDを保持する
 */
 function checkedSkill(checked, value){
    var list = document.getElementsByName('selectedSkills')[0].value;
    if(checked){
      var overlap = list.indexOf(value);
      if(overlap < 0){
        document.getElementsByName('selectedSkills')[0].value += value + ',';
      }
    }

    if(!checked){
      var overlap = list.indexOf(value);
        if(-1 < overlap){
          var newList =list.replace(value + ',' , '' ) ;
          document.getElementsByName('selectedSkills')[0].value = newList;
        }
    }
  }

/*
 * 日付が正しい形式で入力されているかチェックする
 */
function dateCheck(stDt, enDt, index){
  var stDt = document.getElementsByName(stDt)[index].value;
  var enDt = document.getElementsByName(enDt)[index].value;
  var error = '<li>入力形式が正しくありません。「YYYY/MM/DD」形式で入力してください。</li>';
  var submitFlg = true;

  if(!stDt.match(/^\d{4}\/\d{2}\/\d{2}$/)){
    if(!stDt == ""){
      submitFlg = false;
    }
  }
  if(!enDt.match(/^\d{4}\/\d{2}\/\d{2}$/)){
    if(!enDt == ""){
        submitFlg = false;
      }
  }
  if(submitFlg){
    return true;
  }else{
    document.getElementById("formatError").innerHTML = error;
    return false;
  }
}

/*
 * 編集押下時、対象の案件情報IDを送信する
 */
function editPrj(prjId){
  var fieldList = [];
  /* 案件情報ID */
  var hiddenIdField = createHiddenField(prjId, 'prjId');
  fieldList.push(hiddenIdField);

  appendListField(fieldList, 3);
  document.forms[3].submit();
}

/*
 * 削除押下時、対象の案件情報IDを送信する
 */
function deletePrj(prjId, prjName, updtDate){
  if(confirm(prjName +"を削除します。よろしいですか？") == true){
    var fieldList = [];
    /* 案件情報ID */
    var hiddenIdField = createHiddenField(prjId, 'prjId');
    fieldList.push(hiddenIdField);
    /* 案件情報更新日時 */
    var hiddenDtField = createHiddenField(updtDate, 'updtDate');
    fieldList.push(hiddenDtField);
    /* 案件情報名 */
    var hiddenNmField = createHiddenField(prjName, 'prjName');
    fieldList.push(hiddenNmField);

    appendListField(fieldList, 4);
    document.forms[4].submit();
  }
}

/*
 * 渡された値から、hiddenフィールドを作る
 */
function createHiddenField(value, name){
  var hiddenField = document.createElement('input');
  hiddenField.type = 'hidden';
  hiddenField.name = name;
  hiddenField.value = value;

  return hiddenField;
}

/*
 * 渡されたリスト内のフィールドを追加する
 */
function appendListField(fieldList, formIndex){
  for (var i=0; i<fieldList.length; i++) {
    document.forms[formIndex].appendChild(fieldList[i]);
  }
}

</script>

<title>Insert title here</title>
</head>
<body onLoad="toInitialDisp()">

<jsp:include page="../common/header.jsp"/>

<p><u><b>案件情報一覧</b></u></p>
<s:form method="POST">
<div>

  <div id="formatError" style="color:red; position: relative; left: 40px;">
   <html:errors/>
  </div>
  <div id="tab1">
  <!-- 詳細検索  -->
  <p>
    <table border="1" bordercolor="black" rules="none">
    <tr>
      <th>担当者名</th>
      <td><html:select property="userNameList" value=""><html:options property="userNameList"/></html:select></td>
      <td><html:hidden property="tabName"/></td>
      <td></td>
      <td></td>
      <td></td>
    </tr>
    <tr>
      <th>発生日</th>
      <td><html:text property="periStDtDummy" size="20" maxlength="30" onchange="setDt('periStDtDummy', 'periStDt', 0)"/>
          <html:hidden property="periStDt" value=""/></td>
      <td>～</td>
      <td><html:text property="periEnDtDummy" size="20" maxlength="30" onchange="setDt('periEnDtDummy', 'periEnDt', 0)"/>
          <html:hidden property="periEnDt" value=""/>
          </td>
      <td></td>
      <td></td>
    </tr>
    <tr>
      <th>会社名</th>
      <td><html:text property="compName" size="20" maxlength="30"/></td>
      <td></td>
      <td>
      <label><html:checkbox property="lngTrmFlg"/>長期</label>
      <label><html:checkbox property="smDyFlg"/>即日</label>
      <label><html:checkbox property="anyTmFlg"/>随時</label>
      </td>
      <td></td>
      <td></td>
      </tr>
    <tr>
      <th>延長</th>
      <td  colspan="3">
        <label><html:radio property="exteFlg" value="true"/>あり</label>
        <label><html:radio property="exteFlg" value="false"/>なし</label>
        <label><html:radio property="exteFlg" value="none"/>指定しない</label>
      </td>
      <td><html:hidden property="selectedSkills"/></td>
      <td></td>

    </tr>
    <tr>
      <th>スキル</th>
      <td colspan="3">
      <c:forEach var = "info" items = "${skillMasterFormList}" varStatus="infoNo">
        <label><html:checkbox property="skillId" value="${info.skillId}" onchange="checkedSkill(this.checked, this.value);"/>${f:h(info.skillName)}</label>
        <c:if test="${infoNo.count %5 == 0}"></br></c:if>
      </c:forEach>
      </td>
      <td></td>
      <td></td>
    </tr>
    <tr>
      <th>概要</th>
      <td><html:text property="overview" size="20" maxlength="30"/></td>
      <td><html:hidden property="kindOfSerch"  value="detailed"/></td>
      <td align="right"><s:submit value = "検索" onclick="return dateCheck('periStDtDummy', 'periEnDtDummy', 0);" property="select" />
      <input type="button" id="simpleSearch" onclick="location.href='#tab2';ChangeTab('tab2');hide('simpleSearch','detailedSearch');return false;" value="簡易検索" /></td>
      <td></td>
      <td></td>
    </tr>
    </table>
  </p>
  </div>
</s:form>

<s:form method="POST">
  <!-- 簡易検索  -->
  <div id="tab2">
    <p>
    <table border="1" bordercolor="black" rules="none">
    <tr>
    <th>担当者名</th>
    <td><html:select property="userNameList" value=""><html:options property="userNameList"/></html:select></td>
    <td><html:hidden property="tabName"/></td>
    <td>
    </td>
    <td>
    </td>
    </tr>
    <tr>
    <th>発生日</th>
    <td><html:text property="periStDtDummy" size="20" maxlength="30" onchange="setDt('periStDtDummy', 'periStDt', 1)"/>
          <html:hidden property="periStDt" value=""/></td>
      <td>～</td>
      <td><html:text property="periEnDtDummy" size="20" maxlength="30" onchange="setDt('periEnDtDummy', 'periEnDt', 1)"/>
          <html:hidden property="periEnDt" value=""/></td>
      <td></td>
    </tr>
    <tr>
    <th>会社名</th>
    <td><html:text property="compName" size="20" maxlength="30"/></td>
    <td><html:hidden property="kindOfSerch"  value="simple"/></td>
    <td align="right"><s:submit value = "検索"  onclick="return dateCheck('periStDtDummy', 'periEnDtDummy', 1);" property="select" />
    <input type="button" id="detailedSearch" onclick="location.href='#tab1';ChangeTab('tab1');hide('detailedSearch','simpleSearch');return false;" value="詳細検索"/></td>
    <td></td>
    </tr>
    </table>
    </p>
  </div>

</div>
</s:form>

<!-- デフォルトのテーブルとボタンを指定 -->
<script type="text/javascript">

</script>

<div style="height:250px; overflow-y:scroll;">
<table id="searchResults" class="tablesorter">

 <thead>
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
 </thead>

<tbody>
 <c:forEach var = "prjInfo" items = "${prjInfoListFormList}">
  <tr>
    <td align="center">
    <s:form action="print" method="Post">
      <html:checkbox property="printFlg" value="${ f:h(prjInfo.prjId) }"/>
    </s:form>
    </td>
    <td align="center">
    <s:form action="showUpdate" method="Post">
      <a href="javascript:editPrj('${prjInfo.prjId}')">編集</a>
    </s:form>
    </td>
    <td align="center">
    <s:form action="delete" method="Post">
      <a href="javascript:deletePrj(${f:h(prjInfo.prjId)},'${f:h(prjInfo.prjName)}','${f:h(prjInfo.updateDate)}')">削除</a>
    </s:form>
    </td>
    <td align="left"><c:out value = "${prjInfo.prjId}"/></td>
    <td align="left"><c:out value = "${prjInfo.prjName}"/></td>
    <td align="left"><c:out value = "${prjInfo.cmpName}"/></td>
    <td align="left"><c:out value = "${prjInfo.userName}"/></td>
    <td align="left"><c:out value = "${prjInfo.genDate}"/></td>
    <td align="left"><pre><c:out value = "${prjInfo.prjPeriod}"/></pre></td>
    <td align="left"><pre><c:out value = "${prjInfo.skillName}"/></pre></td>
    <td align="left"><c:out value = "${prjInfo.extendFlg}"/></td>
  </tr>
 </c:forEach>
 </tbody>

</table>
</div>
<table>
<s:form method="post">
    <div class="returnButtonPosition">
      <s:submit property="returnMenu" value="戻る"></s:submit>
    </div>
  </s:form>
</table>


<jsp:include page="../common/footer.jsp"/>
</body>
</html>