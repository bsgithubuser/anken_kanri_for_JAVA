<td>
    <img src="/Anken_kanri/img/bs_logo.jpg" width="250" height="40">
</td>
<div class="tytle"><b>案件情報管理システム</b></div>
<br>
<div class="userNameAndLogOut"><c:if test="${ sessionDto.userName != null }">ユーザー名:${sessionDto.userName}</c:if></div>
<br>
<div class="userNameAndLogOut"><c:if test="${ sessionDto.userName != null }"><html:link page="/login/logout"><u>ログアウト</u></html:link></c:if></div>
<hr>