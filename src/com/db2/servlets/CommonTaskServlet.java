package com.db2.servlets;

import com.db2.entity.*;
import com.db2.templates.CommonGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public abstract class CommonTaskServlet extends HttpServlet {
    protected CommonGenerator pageGenerator = CommonGenerator.instance();
    // migrated: private EntityProvider provider;
    protected static final EntityProvider provider = new JdbcTaskProvider(); // keep one connection
    protected Map<String, Object> variables = new HashMap<>();;

    // Map<String, String[]> getParameterMap()
    // todo: move to Entity
    protected TaskEntity map(HttpServletRequest request) {
        TaskEntity result = new TaskEntity();
        // note: "id" auto
        result.setName(request.getParameter("name"));
        result.setPriority(Integer.parseInt(request.getParameter("priority")));
        // expected yyyy-mm-dd
        //SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        result.setDueDate(LocalDate.parse(request.getParameter("dueDate")));

        return result;
    }

    protected void loadRequestVariables(HttpServletRequest request) {
        //Map<String, Object> pageVariables = new HashMap<>();
        variables.put("method", request.getMethod());
        variables.put("URL", request.getRequestURL().toString());
        variables.put("pathInfo", request.getPathInfo());
        variables.put("sessionId", request.getSession().getId());
        // Map<String, String[]>
        variables.put("parameters", request.getParameterMap().toString());
    }
}
