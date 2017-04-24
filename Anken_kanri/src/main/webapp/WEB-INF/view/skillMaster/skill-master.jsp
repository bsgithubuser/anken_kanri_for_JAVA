<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type = "text/css" rel = "stylesheet" href = "../css/common.css"/>
        <link type = "text/css" rel = "stylesheet" href = "../css/skillMaster.css"/>
        <link rel="stylesheet" href="../tablesorter/themes/blue/style.css" type="text/css" media="print, projection, screen" />
        <title>Insert title here</title>
        <script src="../tablesorter/jquery-latest.js" type="text/javascript"></script>
        <script src="../tablesorter/jquery.tablesorter.min.js" type="text/javascript"></script>
        <script src="../tablesorter/jquery.metadata.js" type="text/javascript"></script>
        <script type="text/javascript" language=javascript>
            $(document).ready(function()
                {
                  $("#skillList").tablesorter({
                      headers: {
                          3: { sorter: false },
                          4: { sorter: false }
                      }
                  });
                }
              );

            /*
             * 編集押下時、対象のスキルIDをFormに渡す
             */
            function editSkill(skillId,skillName){
              var hiddenField = document.createElement('input');
              hiddenField.type = 'hidden';
              hiddenField.name = 'skillId';
              hiddenField.value = skillId;

              var hiddenNMField = document.createElement('input');
              hiddenNMField.type = 'hidden';
              hiddenNMField.name = 'skillName';
              hiddenNMField.value = skillName;

              document.forms[1].appendChild(hiddenField);
              document.forms[1].appendChild(hiddenNMField);
              document.forms[1].submit();
            }

            /*
             * 削除押下時、対象のスキルIDとスキル名をFormに渡す
             */
            function deleteSkill(skillId,skillName){
              if(confirm(skillName +"を削除します。よろしいですか？") == true){
                var hiddenIdField = document.createElement('input');
                  hiddenIdField.type = 'hidden';
                  hiddenIdField.name = 'skillId';
                  hiddenIdField.value = skillId;

                  var hiddenNMField = document.createElement('input');
                  hiddenNMField.type = 'hidden';
                  hiddenNMField.name = 'skillName';
                  hiddenNMField.value = skillName;

                  document.forms[2].appendChild(hiddenIdField);
                  document.forms[2].appendChild(hiddenNMField);
                  document.forms[2].submit();
              }

            }

        </script>
    </head>
    <body>
        <jsp:include page="../common/header.jsp"/>
        <p class="screenTytle"><u><b>スキル管理マスタ</b></u></p>

        <div class="errorDisplay">
          <html:errors />
        </div>

        <table class="tablePosition">
            <s:form method="post">
                <tr class="buttonPosition">
                    <td>
                        <s:submit property="regist" value="新規作成"></s:submit>
                    </td>
                </tr>
            </s:form>
            <tr>
                <td>
                    <table id="skillList" class="skillTableSorter">
                      <thead>
                        <tr class="listTytle">
                            <th class="titleStyle">スキル管理ID</th>
                            <th class="titleStyle">スキル名</th>
                            <th class="titleStyle">並び順</th>
                            <th class="titleStyle">編集</th>
                            <th class="titleStyle">削除</th>
                        </tr>
                      </thead>
                        <c:forEach var="date" items="${skillList}">
                            <tr class="columnStyle">
                                <td class="listStyle">${date.skillId}</td>
                                <td class="listStyle">${date.skillName}</td>
                                <td class="listStyle">${date.skillNumber}</td>
                                <s:form action="regist" method="post">
                                    <td class="listStyle"><a href="#" onclick="editSkill(${f:h(date.skillId)},'${f:h(date.skillName)}');">編集</a></td>
                                </s:form>
                                <s:form action="delete" method="post">
                                    <td class="listStyle"><a href="#" onclick="deleteSkill(${f:h(date.skillId)},'${f:h(date.skillName)}');">削除</a></td>
                                </s:form>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
            </tr>
            <s:form method="post">
                <tr class="buttonPosition">
                    <td>
                       <s:submit property="returnMenu" value="戻る"></s:submit>
                    </td>
                </tr>
            </s:form>
        </table>
        <div class="footerPosition">
            <jsp:include page="../common/footer.jsp"/>
        </div>
    </body>
</html>