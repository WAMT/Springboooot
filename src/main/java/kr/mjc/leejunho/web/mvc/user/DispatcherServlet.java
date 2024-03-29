package kr.mjc.leejunho.web.mvc.user;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 모든 요청을 받아서 uri에 따라 컨트롤러 메서드를 호출한다.
 */
@WebServlet("/mvc/*")
public class DispatcherServlet extends HttpServlet {

    @Autowired
    UserController userController;
    @Autowired
    ArticleController articleController;

    @Override
    protected void service(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();

        switch (uri) {
            case "/mvc/user/userList" -> userController.userList(request, response);
            case "/mvc/user/userForm" -> userController.userForm(request, response);
            case "/mvc/user/loginForm" -> userController.loginForm(request, response);
            case "/mvc/user/userInfo" -> userController.userInfo(request, response);
            case "/mvc/user/addUser" -> userController.addUser(request, response);
            case "/mvc/user/login" -> userController.login(request, response);
            case "/mvc/article/articleList" -> articleController.articleList(request,response);
            case "/mvc/article/addArticle" -> articleController.addArticle(request,response);
            case "/mvc/article/getArticle" -> articleController.viewArticle(request,response);
            case "/mvc/article/articleForm" -> articleController.articleForm(request,response);
            case "/mvc/article/modifyArticle" -> articleController.modifyArticle(request,response);
            case "/mvc/article/deleteArticle" -> articleController.deleteArticle(request,response);
            case "/mvc/article/editArticle" -> articleController.editArticle(request,response);


            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}