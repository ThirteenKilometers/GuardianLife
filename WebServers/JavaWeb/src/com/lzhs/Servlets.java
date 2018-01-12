package com.lzhs;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by liuchen on 2017/12/24.
 */
@WebServlet(name = "Servlets")
public class Servlets extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out =response.getWriter();
        out.println("<html>");
        out.println("<head><title>Servlet展示页</title></head>");
        out.println("<body>name:" + "<br/> sex: " +"</body>");
        out.println("</html>");
    }
}
