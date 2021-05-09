package kr.mjc.leejunho.web.mvc.user;
import kr.mjc.leejunho.web.dao.Article;
import kr.mjc.leejunho.web.dao.ArticleDao;
import kr.mjc.leejunho.web.dao.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Slf4j
public class ArticleController {

    private final ArticleDao articleDao;

    @Autowired
    public ArticleController(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    /**
     * 게시글 목록 화면
     */
    public void articleList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("articleList", articleDao.listArticles(0, 100));

        request.getRequestDispatcher("/WEB-INF/jsp/model2/article/articleList.jsp")
                .forward(request, response);
    }

    /**
     * 게시글 등록 화면
     */
    public void articleForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/model2/article/articleForm.jsp")
                .forward(request, response);
    }

    /**
     * 글 보기 화면 (한 건 조회)
     */
    public void viewArticle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String articleId = request.getParameter("id");
        Article article = articleDao.getArticle(Integer.parseInt(articleId));
        request.setAttribute("ARTICLE", article);
        request.getRequestDispatcher("/WEB-INF/jsp/model2/article/viewArticle.jsp")
                .forward(request, response);
    }

    /**
     * 게시글 등록 액션
     */
    public void addArticle(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        Article article = new Article();
        User user = (User) session.getAttribute("USER");
        article.setUserId(user.getUserId());
        article.setTitle(request.getParameter("title"));
        article.setContent(request.getParameter("content"));
        article.setName(user.getName());


        try {
            articleDao.addArticle(article);
            response.sendRedirect(request.getContextPath() + "/mvc/article/articleList");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() +
                    "/mvc/article/articleList?msg=Something Wrong. Try this again.");
        }
    }

    /**
     * 게시글 수정
     */
    public void modifyArticle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("USER");

//        String title = request.getParameter("title");
//        String content = request.getParameter("content");
        String articleUserId = request.getParameter("userId");
        int sessionUserId = user.getUserId();
        if(Integer.parseInt(articleUserId) == sessionUserId)
            request.getRequestDispatcher("/WEB-INF/jsp/model2/article/modifyArticle.jsp")
                    .forward(request, response);

    }

    /**
     * 게시글 삭제
     */
    public void deleteArticle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("USER");
        int sessionUserId = user.getUserId();
        int articleUserId = Integer.parseInt(request.getParameter("userId"));
        int articleId = Integer.parseInt(request.getParameter("articleId"));

        if(sessionUserId == articleUserId){
            articleDao.deleteArticle(articleId, sessionUserId);
            response.sendRedirect(request.getContextPath() + "/mvc/article/articleList");
        }
    }

    /**
     * 게시글 수정 액션
     */
    public void editArticle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Article article = new Article();
        article.setTitle(request.getParameter("title"));
        article.setContent(request.getParameter("content"));
        article.setArticleId(Integer.parseInt(request.getParameter("articleId")));
        article.setUserId(Integer.parseInt(request.getParameter("userId")));
        articleDao.updateArticle(article);
        response.sendRedirect(request.getContextPath() + "/mvc/article/articleList");

    }

}
