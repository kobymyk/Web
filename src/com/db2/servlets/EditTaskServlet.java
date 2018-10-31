package com.db2.servlets;

import com.db2.entity.TaskEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditTaskServlet extends CommonTaskServlet {
    // todo: URL + ".html"
    private static final String FILE_NAME = "edit-task.html";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        // load by id
        int id = Integer.parseInt(request.getParameter("id"));
        TaskEntity item = (TaskEntity) provider.getRecord(id);
        variables.put("task", item);

        String page = pageGenerator.makePage(FILE_NAME, variables);
        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        TaskEntity item = map(request);
        //provider.editRecord(item);

        resp.sendRedirect("/main");
    }
}
