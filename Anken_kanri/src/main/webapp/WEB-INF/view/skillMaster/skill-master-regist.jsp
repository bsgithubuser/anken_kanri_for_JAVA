<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type = "text/css" rel = "stylesheet" href = "../css/common.css"/>
        <link type = "text/css" rel = "stylesheet" href = "../css/skillMaster.css"/>
        <title>Insert title here</title>
        <script type="text/javascript" language=javascript>
            /*
             * スキル名の入力欄を空にする
             */
            function clearSkillName(){
                document.getElementById("skillName").value = "";
                document.getElementById("skillNumber").value = "";
            }

            /*
             * 重複するスキル名を登録していいか確認する
             */
/*
            function checkOverlap(){
              var overlap = document.getElementById("overlap").value;
              var name = document.getElementById("skillName").value;
              if(0 < overlap){
                if(confirm("登録しようとした「"+ name +"」と同じ名前が存在します。よろしいですか？") == true){
                        document.forms[1].submit();
                }
            }
            }
*/
        </script>
    </head>
    <body onLoad="checkOverlap()">
        <jsp:include page="../common/header.jsp"/>
        <p class="screenTytle"><u><b>スキル管理マスタ</b></u></p>

        <div class="errorDisplay">
          <html:errors/>
        </div>

        <table class="tablePosition">
        <s:form method="post">
            <tr>
                <td colspan="3">
                    <table>
                      <c:if test="${f:h(fetchSkillData != null)}">
                        <tr>
                            <th class="inputTytle">スキル管理ID</th>
                            <td class="inputPosition">
                                <input type="text" id="dispSkillId" name="dispSkillId" disabled="disabled" value="${f:h(fetchSkillData.skillId)}">
                                <input type="hidden" id="skillId" name="skillId" value="${f:h(fetchSkillData.skillId)}">
                            </td>
                        </tr>
                        <tr>
                            <th class="inputTytle">スキル名</th>
                            <td class="inputPosition">
                                <input type="text" id="skillName" name="skillName" style="ime-mode: active;" value="${f:h(fetchSkillData.skillName)}">
                            </td>
                        </tr>
                         <tr>
                            <th class="inputTytle">並び順</th>
                            <td class="inputPosition">
                                <input type="text" id="skillNumber" name="skillNumber" style="ime-mode: inactive;" maxlength="3"
                                 value="${f:h(fetchSkillData.skillNumber)}"/>
                            </td>
                        </tr>
                      </c:if>
                      <c:if test="${f:h(fetchSkillData == null)}">
                        <tr>
                            <th class="inputTytle">スキル管理ID</th>
                            <td class="inputPosition">
                                <input type="text" id="dispSkillId" name="dispSkillId" disabled="disabled" value="${f:h(skillId)}">
                                <input type="hidden" id="skillId" name="skillId" value="${f:h(skillId)}">
                            </td>
                        </tr>
                        <tr>
                            <th class="inputTytle">スキル名</th>
                            <td class="inputPosition">
                                <input type="text" id="skillName" name="skillName" style="ime-mode: active;" value="${f:h(skillName)}">
                            </td>
                        </tr>
                        <tr>
                            <th class="inputTytle">並び順</th>
                            <td class="inputPosition">
                                <input type="text" id="skillNumber" name="skillNumber" style="ime-mode: inactive;"  maxlength="3"
                                 value="${f:h(skillNumber)}"/>
                            </td>
                        </tr>
                      </c:if>
                      <tr>
                        <td>
                          <input type="hidden" id="overlap" name="overlap" value="${f:h(overlap)}">
                        </td>
                      </tr>
                    </table>
                </td>
            </tr>

            <tr class="buttons">
                <td class="saveButton">
                    <s:submit property="save" value="登録"></s:submit>
                </td>
                <td class="returnButton">
                    <s:submit property="index" value="戻る"></s:submit>
                </td>
                <td class="clearButton">
                    <html:button property="clear" value="クリア" onclick="clearSkillName()"/>
                </td>
            </tr>
        </s:form>
        <s:form action="saveOverlapName" method="post"></s:form>
        </table>

        <div class="footerPosition">
            <jsp:include page="../common/footer.jsp"/>
        </div>
    </body>
</html>