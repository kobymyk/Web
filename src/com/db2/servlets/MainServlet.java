package com.db2.servlets;

import com.db2.templates.CommonGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends CommonTaskServlet {
    private static final String FILE_NAME = "main.html";

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        // load request params and data
        loadAll(request);
        // for debug
        String page = pageGenerator.makePage(FILE_NAME, variables);
        response.getWriter().write(page);
    }
    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        // nothing to post currently
        response.getWriter().write(pageGenerator.makePage(FILE_NAME, variables));
    }
}
