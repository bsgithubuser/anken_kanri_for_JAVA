<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type = "text/css" rel = "stylesheet" href = "../css/common.css"/>
<link type = "text/css" rel = "stylesheet" href = "../css/modal.css"/>
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
function toInitialDisp(prjList){
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

  /* 検索結果がある場合、一括印刷ボタンを表示 */
  if(prjList){
      document.getElementsByName("bulkPrint")[0].style.display = '';
  }

  /* 印刷画面の表示制御 */
  var isDisplayPrintPage = document.getElementsByName('isDisplayPrintPage')[0].value;
  if(isDisplayPrintPage){
      window.open("../print/", "print", "scrollbars=1, menubar=0, toolbar=0");
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

/*
 * 概要モーダルを表示する
 */
function openModal(index){
    var hiddenOrverview = document.getElementsByName('overview')[index].value;
    document.getElementById("modal-text").innerHTML = hiddenOrverview;

    /* モーダルの座標 */
    var modalX = 0;
    var modalY = 0;

    /* 画面からフォーカスを外す */
    $(this).blur();

    /* オーバーレイ用のHTMLコードを、[body]内の最後に生成する */
    $("body").append('<div id="modal-overlay"></div>');
    $("#modal-overlay").fadeIn("normal");

    /* センタリング */
    centeringModalSyncer();
    $("#modal-content").fadeIn("normal");

    /* closeボタンがクリックされたときの処理 */
    $("#modal-close").unbind().click(closeModal);

    /* 画面サイズ変更時 */
    $(window).resize(centeringModalSyncer) ;

    /* ドラッグ可能領域でマウスが押下されたとき */
    $('#draggable').mousedown(moveModal);

    /*
     * モーダルをセンタリングする
     */
    function centeringModalSyncer(){
        $("#modal-text-area").attr('style', '');

         var width = $(window).width();
         var height = $(window).height();

         var cw = $("#modal-content").outerWidth();
         var ch = $("#modal-content").outerHeight();

         if(height < ch || height-ch < 50){
             $("#modal-text-area").css({"height": height - 250 + "px"});
             ch = $("#modal-content").outerHeight();
         }

         modalX = ((width - cw)/2);
         modalY = ((height - ch)/2);

         $("#modal-content").css({"left": modalX + "px", "top": modalY + "px"});
    }

    /*
     * モーダルを閉じる
     */
    function closeModal(){
        $("#modal-content,#modal-overlay").fadeOut("normal", function(){
            $("#modal-overlay").remove();
        });
    }

    /*
     * モーダルを移動させる
     */
    function moveModal(event){
        var mouseX = event.pageX;
        var mouseY = event.pageY;

        /* マウス移動時、マウスの左ボタンが上がった時のイベント追加 */
        $(document)
            .bind('mousemove.modal', function(event){
                modalX += event.pageX - mouseX;
                modalY += event.pageY - mouseY;
                var modalState = restrictDisplayArea(modalX, modalY);

                $("#modal-content").css({"left": modalState.x + "px", "top": modalState.y + "px"});

                mouseX = event.pageX;
                mouseY = event.pageY;

                return false;
            })
            .bind('mouseup', function(){
                $(document).unbind('mousemove.modal');
            });

        return false;
    }

    /*
     * モーダルが画面からはみ出す時、画面内に維持するよう値を修正する。
     */
    function restrictDisplayArea(modalX, modalY){
        var obj = new Object();
        obj.x = modalX;
        obj.y = modalY;

        var modalWidth = document.getElementById("modal-content").offsetWidth;
        var modalHeight = document.getElementById("modal-content").offsetHeight;

        var maxHorizontal = $(window).width() - modalWidth;
        var maxVertical = $(window).height() - modalHeight;

        if(modalX < 1){
            obj.x = 0;
        }else if (maxHorizontal <= modalX){
            obj.x = maxHorizontal;
        }
        if(modalY < 1){
            obj.y = 0;
        }else if(maxVertical <= modalY){
            obj.y = maxVertical;
        }
        return obj;
    }
}

/*
 * close部分にマウスが乗った時、スタイルを変更する
 */
function mouseOver(param){
    param.style.backgroundColor='#8be42b';
    param.style.color='white';
}

/*
 * close部分からマウスが離れたとき、スタイルを初期に戻す
 */
function mouseOut(param){
    param.style.backgroundColor='lightgreen';
    param.style.color='black';
}

/*
 * チェックされた値を保持する
 */
function createCheckList(fieldName, checked, value){
    var willPrintList = document.getElementsByName(fieldName)[0].value;
    if(checked){
        document.getElementsByName(fieldName)[0].value += value + ',';
    }
    if(!checked){
        var newList =willPrintList.replace(value + ',' , '' );
        document.getElementsByName(fieldName)[0].value = newList;
    }
}

/* 印刷チェックを確認する */
function checkIsPrint(checked, value) {
    createCheckList('printAnknIds', checked, value);
    var printIds = document.getElementsByName('printAnknIds')[0].value;
    /* チェックされた案件が1件以上あるとき、印刷ボタンを表示する */
    if (printIds) {
        document.getElementsByName("print")[0].style.display = '';
    } else {
        document.getElementsByName("print")[0].style.display = 'none';
    }
}

</script>

<title>Insert title here</title>
</head>
<body onLoad="toInitialDisp('${prjInfoListFormList}')">

<jsp:include page="../common/header.jsp"/>

<s:form method="POST">

<p>
    <u><b>案件情報一覧</b></u>
</p>

<div>

 <!-- 概要モーダル -->
  <div id="modal-content">
   <div id="draggable"></div>
   <div id="scroll">
    <div id="modal-text-area">
     <pre id="modal-text"><!-- ここに概要の詳細を表示します --></pre>
    </div>
   </div>
   <div id="modal-close" onmouseover="mouseOver(this)" onmouseout="mouseOut(this)">
    close
   </div>
  </div>

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
        <label><html:checkbox property="skillId" value="${info.skillId}" onchange="createCheckList('selectedSkills', this.checked, this.value);"/>${f:h(info.skillName)}</label>
        <c:if test="${infoNo.count %5 == 0}"></br></c:if>
      </c:forEach>
      </td>
      <td></td>
      <td></td>
    </tr>
    <tr>
      <th>概要</th>
      <td><html:text property="serchOverview" size="20" maxlength="30"/></td>
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

<div style="height:500px; overflow-y:scroll;">
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
    <th>概要</th>
  </tr>
 </thead>

<tbody>
 <c:forEach var = "prjInfo" items = "${prjInfoListFormList}" varStatus = "status">
  <tr>
    <td align="center">
    <s:form action="print" method="Post">
      <input type="checkbox" name="printFlg" onchange="checkIsPrint(this.checked, ${prjInfo.prjId});">
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
    <td align="left">
        <a id="modal-open" href="javascript:openModal(${status.index})">${prjInfo.displayOverview}</a>
        <html:hidden property="overview" value="${prjInfo.overview}"/>
    </td>
  </tr>
 </c:forEach>
 </tbody>
</table>
</div>

<table>
 <s:form method="post">
  <div class="bottomButtons">
   <div class="bottomButtonPosition">
     <s:submit property="bulkPrint" value="一括印刷" style="display: none"></s:submit>
   </div>
   <div class="bottomButtonPosition">
     <s:submit property="print" value="印刷" style="display: none"></s:submit>
   </div>
   <div class="bottomButtonPosition">
     <s:submit property="returnMenu" value="戻る"></s:submit>
   </div>
  </div>

  <html:hidden property="isDisplayPrintPage" value="${isDisplayPrintPage}"/>
  <html:hidden property="printAnknIds" value=""/>
 </s:form>
</table>

<div class="footer">
 <jsp:include page="../common/footer.jsp"/>
</div>
</body>
</html>