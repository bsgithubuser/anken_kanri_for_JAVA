<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type = "text/css" rel = "stylesheet" href = "../css/common.css"/>
        <link type = "text/css" rel = "stylesheet" href = "../css/skillMaster.css"/>
        <title>Insert title here</title>
    </head>
    <body>
        <jsp:include page="../common/header.jsp"/>
        <p class="screenTytle"><u><b>案件情報登録</b></u></p>

        <div class="success">
           <b>登録が完了しました!</b>
        </div>

        <div class="links">
            <div class="sideBySide1">
                <s:link href="一覧画面へ">一覧画面へ戻る</s:link>
            </div>
            <div class="sideBySide2">
                <s:link href="index">登録画面へ戻る</s:link>
            </div>
        </div>

        <div class="footerPosition2">
            <jsp:include page="../common/footer.jsp"/>
        </div>
    </body>
</html>