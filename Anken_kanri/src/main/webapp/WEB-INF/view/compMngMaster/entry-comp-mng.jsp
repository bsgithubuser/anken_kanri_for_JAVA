<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="../css/common.css" />
<title>会社情報管理マスタ</title>
</head>
<c:if test="${sameNameFlag == 1}" var="flg">
  <body onload="showPopUp('${popUpData}')">
</c:if>
<c:if test="${!flg}">
  <body>
</c:if>
<div class="header">
  <jsp:include page="../common/header.jsp" />
</div>
<s:form action="/compMngMaster/entry" method="Post">
  <br>
  <p class="screenTytle">
    <u><b>会社情報管理マスタ</b></u>
  </p>
  <p class="inputItem">
    会社ID
    <html:text styleClass="IDTextForm" property="idItemString" value="${idItemString}"
      readonly="true" />
  </p>
  <p class="inputItem">
    会社名<font class="requiredMark">*</font> <input type="text"
      class="nameTextForm" id="nameItem" name="nameItem"
      value="${nameItem}" maxlength="60">
  </p>
  <p class="inputItem">
    フリガナ<font class="requiredMark">*</font> <input type="text"
      class="nameKanaTextForm" id="nameKanaItem"
      name="nameKanaItem" value="${nameKanaItem}" maxlength="60">
  </p>
  <div class="entryErrorMessage">
    <html:errors />
  </div>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <input type="hidden" name="date" value="${date}">
  <div class="buttonPosition" style="float: left">
    <s:submit value="登録" styleClass="buttonSize" />
  </div>
</s:form>
<s:form action="/compMngMaster/" method="Post">
  <div class="buttonPosition" style="float: left">
    <s:submit value="戻る" styleClass="buttonSize" />
  </div>
</s:form>
<div class="buttonPosition" style="float: left">
  <input type="button" value="クリア" onClick="clearText()"
    class="buttonSize" />
</div>
<br>
<br>
<div class="footer">
  <jsp:include page="../common/footer.jsp" />
</div>
</body>

<SCRIPT type="text/javascript" language="javascript">
  /*
   * 登録時、同名のデータがあった場合、確認ダイアログを表示させます。
   * @param afterClickPopUpFlg ダイアログで「はい」を押下した際に1になります(Actionの分岐で使用します)。
   */
  function showPopUp(data){
    if(confirm(data) == true){
      var afterClickPopUpFlg = document.createElement('input')
      afterClickPopUpFlg.value = 1;
      afterClickPopUpFlg.name = 'flgAfterClickDialog';
      afterClickPopUpFlg.type = 'hidden'
      document.forms[0].appendChild(afterClickPopUpFlg);
      document.forms[0].submit();
    }
  }
</SCRIPT>
<SCRIPT type="text/javascript" language="javascript">
  /*
   * クリアボタン押下後、入力欄を空白にします。
   */
  function clearText(){
    document.forms[0].nameItem.value="";
    document.forms[0].nameKanaItem.value="";
  }
</SCRIPT>
</html>