<%@ page import="kr.mjc.leejunho.web.dao.User" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<%
    User user = (User) session.getAttribute("USER");
    Date nowTime = new Date();
    SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일 a hh:mm:ss");
%>
<html>
<head>
    <meta http-equiv="refresh" content="3;url=/mvc/article/articleList">
</head>
<body>
<h2>사용자 정보</h2>
<h3><%= user.getName() %>님, 반갑습니다. <br></h3>
<p>
    오늘 날짜: <%=sf.format(nowTime)%> <br>
    3초 뒤 자동으로 게시글 목록으로 이동됩니다.
</p>
</body>
</html>