/*
Copyright (c) 2012, Yusuke Yamamoto
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the Yusuke Yamamoto nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY Yusuke Yamamoto ``AS IS'' AND ANY
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL Yusuke Yamamoto BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

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
