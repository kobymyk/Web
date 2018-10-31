package com.db2.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditTaskServlet extends CommonTaskServlet {
    // todo: URL + ".html"
    private static final String FILE_NAME = "edit-task.html";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // load by id
        loadById(req);
        String page = pageGenerator.makePage(FILE_NAME, variables);
        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.add(req);

        resp.sendRedirect("/main");
    }
}
