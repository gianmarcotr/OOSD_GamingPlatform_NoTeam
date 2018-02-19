package org.oosd.project.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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

import org.oosd.project.beans.User;
import org.oosd.project.beans.UserAchievement;
import org.oosd.project.utils.MyUtils;

@WebServlet(urlPatterns = { "/profile"})
public class ProfileServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        HttpSession session = request.getSession();
        User loginedUser =  MyUtils.getLoginedUser(session);
        
        try {
            List<UserAchievement> userAchi = UserAchievement.getUserAchi(conn, loginedUser);
            List<UserAchievement> userAchiCompleted = new ArrayList<>();
            List<UserAchievement> userAchiInCompleted = new ArrayList<>();
            for(UserAchievement ua: userAchi){
                if(ua.getCompleted()==1)
                    userAchiCompleted.add(ua);
                else
                    userAchiInCompleted.add(ua);
            }
            if(userAchiCompleted.isEmpty()==true){
                request.setAttribute("completedEmpty", true);
            }
            
            if(userAchiInCompleted.isEmpty()==true){
                request.setAttribute("incompletedEmpty", true);
            }
            request.getSession().setAttribute("user", loginedUser);
            request.setAttribute("userAchiC", userAchiCompleted);
            request.setAttribute("userAchiI", userAchiInCompleted);
            
            
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/profileView.jsp");

            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
