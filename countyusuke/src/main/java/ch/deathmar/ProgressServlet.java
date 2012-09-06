package ch.deathmar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ProgressServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String callback = request.getParameter("callback");
        CountYusuke countYusuke = (CountYusuke) session.getAttribute("progress");
        PrintWriter writer = response.getWriter();
        if (null != callback) {
            writer.print(callback);
            writer.print("(");
        }
        writer.print("{\"failed\":" + countYusuke.isFailed() + ",\"progress\":" + countYusuke.getProgress());

        writer.print(",\"total\":"+countYusuke.total);
        writer.print(",\"screenName\":\""+countYusuke.screenName+"\"");
        writer.print(",\"yusukeCount\":"+countYusuke.yusukes.size());
        writer.print(",\"yusuke\":");

        writer.print(join(countYusuke.yusukes));
        writer.print("}");
        if (null != callback) {
            writer.print(");");
        }
    }

    public static String join(List<String> yusukes) {
        StringBuilder buf = new StringBuilder(11 * yusukes.size());
        buf.append("[");
        if(yusukes.size() != 0){
            buf.append("\"");
        }
        for (String str : yusukes) {
            if (0 != buf.length()) {
                buf.append("\",\"");
            }
            buf.append(str);
        }
        if(yusukes.size() != 0){
            buf.append("\"");
        }
        buf.append("]");
        return buf.toString();
    }
}
