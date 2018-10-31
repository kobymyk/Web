package com.db2.servlets;

import com.db2.entity.*;
import com.db2.templates.CommonGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

// todo=rename: TaskEntity(<E>) = Task
// Loks like force child
public abstract class CommonTaskServlet extends HttpServlet {
    protected CommonGenerator pageGenerator = CommonGenerator.instance();
    // common for: MainServlet, AddTaskServlet
    private EntityProvider provider;
    protected Map<String, Object> variables = new HashMap<>();;

    public void setProvider(EntityProvider provider) {
        this.provider = provider;
    }

    public void edit(HttpServletRequest request) {
        TaskEntity item = map(request);
        //provider.editRecord(item);
    }

    public void loadAllTasks() {
        //ArrayList<TaskEntity> items = provider.getRecords();
        List<TaskEntity> items = provider.getRecords();
        variables.put("tasks", items);
    }

    public void loadById(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        TaskEntity item = (TaskEntity) provider.getRecord(id);
        variables.put("task", item);
    }

    public void loadAll(HttpServletRequest request) {
        loadAllTasks();
        loadRequestVariables(request);
    }

    private TaskEntity map(HttpServletRequest request) {
        TaskEntity result = new TaskEntity();
        // note: "id" auto
        result.setName(request.getParameter("name"));
        result.setPriority(Integer.parseInt(request.getParameter("priority")));
        // expected yyyy-mm-dd
        //SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        result.setDueDate(LocalDate.parse(request.getParameter("dueDate")));

        return result;
    }

    // transform to entity
    public void add(HttpServletRequest request) {
        TaskEntity item = map(request);

        provider.addRecord(item);
    }

    private void loadRequestVariables(HttpServletRequest request) {
        //Map<String, Object> pageVariables = new HashMap<>();
        variables.put("method", request.getMethod());
        variables.put("URL", request.getRequestURL().toString());
        variables.put("pathInfo", request.getPathInfo());
        variables.put("sessionId", request.getSession().getId());
        variables.put("parameters", request.getParameterMap().toString());
    }
}
