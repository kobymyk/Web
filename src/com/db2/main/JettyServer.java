package com.db2.main;

import com.db2.entity.TaskEntityProvider;
import com.db2.servlets.*;
// jetty
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class JettyServer {
    public static void main(String[] args) {
        // read console: -s[top]
        try {
            JettyServer.run(8080);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void run(int port) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        // should be one, mock on create
        TaskEntityProvider mockProvider = new TaskEntityProvider();
        // main.html
        MainServlet mainServlet = new MainServlet();
        mainServlet.setProvider(mockProvider);
        context.addServlet(new ServletHolder(mainServlet), "/main");
        // add-task.html
        AddTaskServlet addTaskServlet = new AddTaskServlet();
        addTaskServlet.setProvider(mockProvider);
        context.addServlet(new ServletHolder(addTaskServlet), "/main/add-task");

        Server server = new Server(port);
        server.setHandler(context);

        server.start();
    }
}