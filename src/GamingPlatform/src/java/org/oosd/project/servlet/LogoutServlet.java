package org.oosd.project.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.oosd.project.utils.MyUtils;

@WebServlet(urlPatterns={"/logout"})
public class LogoutServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    public LogoutServlet(){
        super();
    }
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/views/homeView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        MyUtils.deleteLoginedUser(session);
        MyUtils.deleteLoginedOwner(session);
        response.sendRedirect(request.getContextPath() + "/home");
    }

}
