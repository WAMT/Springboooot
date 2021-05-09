<%@ page import="kr.mjc.leejunho.web.dao.Article" %>
<!DOCTYPE html>
<%
    Article article =  (Article) request.getAttribute("ARTICLE");

%>
<html>
<title>Article Information</title>
<body>
<h2>Article Information</h2>
<h3><%=article.getTitle() %></h3>

    Author : <%=article.getName()%>  <br>
    내용 : <%=article.getContent()%>

<button onclick="location='modifyArticle?title=<%=article.getTitle()%>&content=<%=article.getContent()%>&userId=<%=article.getUserId()%>&articleId=<%=article.getArticleId()%>'">글 수정하기</button>
<button onclick="location='deleteArticle?userId=<%=article.getUserId()%>&articleId=<%=article.getArticleId()%>'">글 삭제하기</button>


</body>
</html>