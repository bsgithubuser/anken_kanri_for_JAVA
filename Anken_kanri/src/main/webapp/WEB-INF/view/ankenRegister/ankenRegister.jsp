<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Language" content="ja" />
    <meta http-equiv="Content-Style-Type" content="text/css" />
    <link rel="stylesheet" type="text/css" href="../css/common.css" />
    <link type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/hot-sneaks/jquery-ui.css" rel="stylesheet" />
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>
<title>案件情報登録</title>

    <script type="text/javascript">
    <!--
        function del() {
            if(confirm("<bean:message key="MSG_I00012" />") == true) {
                var form = document.forms[1];
                form.prjName.value = '';
                form.genDate.value = '${f:h(genDate)}';
                form.cmpnName.value = '';
                form.periFrom.value = '';
                form.periTo.value = '';
                form.longTermFlg.checked = false;
                form.sameDayFlg.checked = false;
                form.anyTimeFlg.checked = false;
                form.extentionFlg[1].checked = true;
                form.orverview.value = '';
                form.prjOther.value = '';

                var userId = form.userId.getElementsByTagName('option');
                for (i = 0; i < userId.length; i++) {
                    if (userId[i].text == 'admin') {
                        userId[i].selected = true;
                        break;
                    }
                }
                var skillId = form.skillId;
                for (i = 0; i < skillId.length; i++) {
                    skillId[i].checked = false;
                }
            }
        }

        function back() {
            if(confirm("<bean:message key="MSG_I00008" />") == true) {
                document.forms[0].submit();
            }
        }

        function backPrjInfoList() {
            if(confirm("<bean:message key="MSG_I00013" />") == true) {
                document.forms[2].submit();
            }
        }

        function connecttext( textid, ischecked ) {
            if( ischecked == true ) {
                // チェックが入っていたら有効化
                document.getElementById(textid).disabled = false;
            } else {
                // チェックが入っていなかったら無効化
                document.getElementById(textid).value="";
                document.getElementById(textid).disabled = true;
            }
        }

        $(function(){$("#genDate").datepicker();});
        $(function(){$("#periTo").datepicker();});
        $(function(){$("#periFrom").datepicker();});
    -->
    </script>
</head>
<body>
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
            <html:select property="userId" value="${f:h(userId)}">
            <c:forEach items="${usersList}" var="data" >
              <html:option value="${f:h(data.userId)}">${f:h(data.userName)}</html:option>
            </c:forEach>
            </html:select>
          </dd>

        </dl>
        <dl>
          <dt>
            発生日<span class="required">*</span>
          </dt>
          <dd>
            <input type="text" id="genDate" name="genDate" value="${f:h(genDate)}" />
          </dd>
        </dl>
        <dl>
          <dt>
            会社名<span class="required">*</span>
          </dt>
          <dd>
            <html:select property="cmpnId" value="${f:h(cmpnId)}">
            <c:forEach items="${cmpnList}" var="data" >
              <html:option value="${f:h(data.cmpnId)}">${f:h(data.cmpnName)}</html:option>
            </c:forEach>
            </html:select>
          </dd>

        </dl>
        <dl>
          <dt>期間</dt>
          <dd>
            <input type="text" id="periFrom" name="periFrom" value="${f:h(periFrom)}" />&ensp;～&ensp;<input type="text" id="periTo" name="periTo" value="${f:h(periTo)}" />

            <input type="checkbox" name="longTermFlg" ${f:h(longTermFlg)} />長期&ensp;
            <input type="checkbox" name="sameDayFlg" ${f:h(sameDayFlg)} />即日&ensp;
            <input type="checkbox" name="anyTimeFlg" ${f:h(anyTimeFlg)} />随時
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
                <!--
                <br />
                                <input type="checkbox" name="skillId" value="-1"name="skillOther" ${f:h(skillOtherFlg)}
                                onclick="connecttext('skillOther',this.checked);" />
                その他
                 <span	class="skill">
                    <input type="text" id="skillOther"	name="skillOther" class="skill-text"
                    value="${f:h(skillOther)}" ${f:h(disabledFlg)} />
                   </span>
                 -->
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
          <input type="button" onClick="backPrjInfoList()"  value="戻る" />
      </c:if>

          <input type="button" onClick="del()"  value="クリア" />

      </s:form>
            </div>
    </div>
  </div>

  <div class="footer">
    <jsp:include page="../common/footer.jsp" />
  </div>

<s:form action="/prjInfoList" method="post"></s:form>
</body>
</html>