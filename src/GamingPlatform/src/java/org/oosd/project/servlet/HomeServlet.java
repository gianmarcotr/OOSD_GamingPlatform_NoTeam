package org.oosd.project.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.oosd.project.beans.Game;
 
import org.oosd.project.utils.MyUtils;
import org.oosd.project.beans.User;

@WebServlet(urlPatterns = { "/home"})
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public HomeServlet() {
       super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to /WEB-INF/views/homeView.jsp
        HttpSession session = request.getSession();  
        User loginedUser =  MyUtils.getLoginedUser(session);
        Connection conn = MyUtils.getStoredConnection(request);
        List<Game> games;
        try {
            games = Game.getGames(conn);
            request.setAttribute("games", games);
        } catch (SQLException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getSession().setAttribute("user", loginedUser);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }


}
