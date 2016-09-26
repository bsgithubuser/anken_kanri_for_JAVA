<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="../css/common.css" />
<title>会社情報管理マスタ</title>
</head>
<body>
  <s:form action="edit" method="Post" />
  <s:form action="delete" method="Post" />
  <div class="header">
    <jsp:include page="../common/header.jsp" />
  </div>
  <br>
  <p class="screenTytle">
    <u><b>会社情報管理マスタ</b></u>
  </p>
  <div class="errorMessagePosition" style="float: left">
    <html:errors />
  </div>
  <br>
  <s:form action="goToEntryJsp" method="Post">
    <div class="entryButtonPosition">
      <s:submit value="新規作成" property="" />
    </div>
  </s:form>

  <table class="list">
    <tbody>
      <tr>
        <th class="companyID">会社ID</th>
        <th class="companyName">会社名</th>
        <th class="companyNameKana">フリガナ</th>
        <th class="edit">編集</th>
        <th class="delete">削除</th>
      </tr>

      <c:forEach var="list" items="${searchResultList}">
        <tr>
          <td class="companyIDSearchResult"><c:out
              value="${list.cmpnId}" /></td>
          <td class="companyNameSearchResult"><c:out
              value="${list.cmpnName}" /></td>
          <td class="companyNameKanaSearchResult"><c:out
              value="${list.cmpnNameFuri}" /></td>
          <td class="editAndDeleteLink"><a
            href="javascript:link('edit', '${list.cmpnId}', '')">編集</a></td>
          <td class="editAndDeleteLink"><a
            href="javascript:link('delete', ${list.cmpnId}, '${list.cmpnName}')">削除</a>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
  <s:form action="../masterMenu/" method="Post">
    <br>
    <div class="returnButtonPosition">
      <s:submit property="../masterMenu/" value="戻る" />
    </div>
  </s:form>
  <br>
  <div class="footer">
    <jsp:include page="../common/footer.jsp" />
  </div>
</body>

<SCRIPT type="text/javascript" language="javascript">
  /*
   * 編集ボタンを押した際は編集画面に遷移するメソッドに、削除ボタンを押したら削除処理に遷移するメソッドにとびます。
   */
  function link(text, idItem, name) {
    if (text == 'edit') {
      var id = document.createElement('input');
        id.type = 'hidden';
        id.name = 'idItemInteger';
        id.value = idItem;
        document.forms[0].appendChild(id);
        document.forms[0].submit();
    } else {
      if (confirm(name + 'を削除します。よろしいですか？') == true) {
        var deleteId = document.createElement('input');
        deleteId.type = 'hidden';
        deleteId.name = 'deleteId';
        deleteId.value = idItem;

        var deleteName = document.createElement('input');
        deleteName.type = 'hidden';
        deleteName.name = 'deleteName';
        deleteName.value = name;
        document.forms[1].appendChild(deleteId);
        document.forms[1].appendChild(deleteName);
        document.forms[1].submit();
      }
    }
  }
</SCRIPT>
</html>