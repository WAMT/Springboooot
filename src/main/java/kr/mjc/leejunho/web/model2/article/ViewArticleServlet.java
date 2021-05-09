package kr.mjc.leejunho.web.model2.article;

import kr.mjc.leejunho.web.dao.Article;
import kr.mjc.leejunho.web.dao.ArticleDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet("/model2/article/getArticle")
public class ViewArticleServlet extends HttpServlet {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     int articleId= Integer.parseInt(request.getParameter("articleId"));

     try{
         Article article = articleDao.getArticle(articleId);
         log.info("글제목: {}", article.getTitle());
         request.setAttribute("articleId", article.getArticleId());
     }catch (Exception e){
        e.printStackTrace();
     }
        request.getRequestDispatcher("/WEB-INF/jsp/model2/article/viewArticle.jsp")
                .forward(request, response);
    }
}
