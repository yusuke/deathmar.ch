package ch.deathmar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class YusukeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String screenName = request.getPathInfo().substring(1);
        List<String> yusukes = (List<String>)Store.get(screenName);
        if(yusukes == null){
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            request.setAttribute("yusukes", yusukes);
            request.setAttribute("screenName", screenName);
        }
        request.getRequestDispatcher("/yusuke.jsp").forward(request, response);
    }
}
