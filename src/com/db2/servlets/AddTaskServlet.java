package com.db2.servlets;

import com.db2.entity.TaskEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddTaskServlet extends CommonTaskServlet {
    // todo: URL + ".html"
    private static final String FILE_NAME = "add-task.html";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        // nothing to load
        // todo: put appName
        String page = pageGenerator.makePage(FILE_NAME, null);
        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        // request has strange Map<S, S[]>
        // todo: create Map for TaskEntity
        TaskEntity item = map(request);
        // request -> item
        provider.addRecord(item);

        resp.sendRedirect("/main");
    }
}
