<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Language" content="ja" />
    <meta http-equiv="Content-Style-Type" content="text/css" />
    <link rel="stylesheet" type="text/css" href="../css/common.css" />
<title>案件情報登録</title>

    <script type="text/javascript">
    <!--
        function del(code) {
      if(confirm("入力中の内容が初期化されますがよろしいですか？") == true) {
        var id = document.createElement('input');
        id.type = 'hidden';
        id.name = 'id';
        id.value = code;
        document.forms[0].appendChild(id);
        document.forms[0].submit();

            }
        }

    function back() {
        if(confirm("メニュー画面へ戻りますがよろしいですか？") == true) {
            document.forms[1].submit();

            }
        }

    function connecttext( textid, ischecked ) {
         if( ischecked == true ) {
            // チェックが入っていたら有効化
            document.getElementById(textid).disabled = false;
         }
         else {
            // チェックが入っていなかったら無効化
            document.getElementById(textid).value="";
            document.getElementById(textid).disabled = true;

         }
      }

    -->
    </script>
</head>
<body>
<s:form action="clear" method="post"></s:form>
<s:form action="/menu" method="post"></s:form>
  <div>
    <jsp:include page="../common/header.jsp" />
  </div>

  <div>
    <div class="center">
      <div>
        <h2>案件情報登録</h2>
      </div>
      <div>
        <html:errors/>
      </div>


      <div class="div-table">
                <s:form method="post">
        <dl>
          <dt>案件ID</dt>
          <dd>
            <input type="text" value="${f:h(id)}" disabled="disabled" />
            <input type="hidden" name="id" value="${f:h(id)}">
          </dd>
        </dl>
        <dl>
          <dt>
            案件名<span class="required">*</span>
          </dt>
          <dd>
            <input type="text"  name="prjName" value="${f:h(prjName)}" />
          </dd>
        </dl>

        <dl>
          <dt>
            担当者<span class="required">*</span>
          </dt>

          <dd>
            <select name="userId" class="tantou">
            <c:forEach items="${usersList}" var="data" >
              <option size="1" value="${f:h(data.userId)}">${f:h(data.userName)}</option>
            </c:forEach>
            </select>
          </dd>

        </dl>
        <dl>
          <dt>
            発生日<span class="required">*</span>
          </dt>
          <dd>
            <input type="text" name="genDate" value="${f:h(genDate)}" />
          </dd>
        </dl>
        <dl>
          <dt>
            会社名<span class="required">*</span>
          </dt>
          <dd>
            <input type="text" name="cmpnName" value="${f:h(cmpnName)}" />
          </dd>
        </dl>
        <dl>
          <dt>期間</dt>
          <dd>
            <input type="text" name="periFrom" value="${f:h(periFrom)}" />&ensp;～&ensp;<input type="text" name="periTo" value="${f:h(periTo)}" />
            <input type="hidden" name="longTermFlg" value="1">
            <input type="checkbox" name="longTermFlg" value="0" ${f:h(longTermFlg)} />長期&ensp;
            <input type="hidden" name="sameDayFlg" value="1">
            <input type="checkbox" name="sameDayFlg" value="0" ${f:h(sameDayFlg)} />即日&ensp;
            <input type="hidden" name="anyTimeFlg" value="1">
            <input type="checkbox" name="anyTimeFlg" value="0" ${f:h(anyTimeFlg)} />随時
          </dd>
        </dl>
        <dl>
          <dt>延長</dt>
          <dd>
          <c:if test="${f:h((extentionFlg) == 0)}">
            <input name="extentionFlg" type="radio" value="1" />あり
            <input type="radio" name="extentionFlg" value="0" checked  />なし
            </c:if>
            <c:if test="${f:h((extentionFlg) == 1)}">
            <input name="extentionFlg" type="radio" value="1" checked />あり
                        <input type="radio" name="extentionFlg" value="0"  />なし
                    </c:if>
          </dd>
        </dl>
        <dl>

            <dt>スキル</dt>
            <dd>
              <span id="skillId">
<%
  int i = 0;
 %>
                                <c:forEach items="${skillList}" var="list">
                    <input type="checkbox" name="skillId"value="${f:h(list.skillId)}" ${f:h(list.checked)} />
                                        ${f:h(list.skillName)}&ensp;
<%
  i++;
%>
<%
  if (i == 5) {
%>
                  <br />
<%
  }
%>
                </c:forEach>
                <br />
                                <input type="checkbox" name="skillId" value="-1"name="skillOther" ${f:h(skillOtherFlg)}
                                onclick="connecttext('skillOther',this.checked);" />
                その他
                 <span	class="skill">
                    <input type="text" id="skillOther"	name="skillOther" class="skill-text"
                    value="${f:h(skillOther)}" ${f:h(disabledFlg)} />
                   </span>
              </span>
            </dd>
          </dl>
        <dl>
          <dt>概要</dt>
          <dd>
            <textarea cols=40 rows=5  name="orverview">${f:h(orverview)}</textarea>
          </dd>
        </dl>
        <dl>
          <dt>その他</dt>
          <dd>
            <textarea cols=40 rows=5 name="prjOther">${f:h(prjOther)}</textarea>
          </dd>
        </dl>
        <html:hidden property="updateDate" value="${f:h(updateDate)}" />
      <c:if test="${f:h((editFlg) == 0)}">
          <s:submit property="entry" value="登録"></s:submit>
          <input type="button" onClick="back()"  value="戻る" />
      </c:if>
      <c:if test="${f:h((editFlg) == 1)}">
          <s:submit property="edit" value="登録"></s:submit>
          <input type="button" onClick="処理"  value="戻る" />
      </c:if>

          <input type="button" onClick="del(${f:h(id)})"  value="クリア" />

      </s:form>
            </div>
    </div>
  </div>

  <div class="footer">
    <jsp:include page="../common/footer.jsp" />
  </div>


</body>
</html>