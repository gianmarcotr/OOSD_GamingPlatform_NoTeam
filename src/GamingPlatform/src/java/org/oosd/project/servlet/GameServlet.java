package org.oosd.project.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.oosd.project.beans.Achievements;
import org.oosd.project.beans.Review;
import org.oosd.project.beans.User;
import org.oosd.project.utils.MyUtils;

/**
 *
 * @author gimmi
 */

@WebServlet(urlPatterns = { "/game"})
public class GameServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();  
        User loginedUser =  MyUtils.getLoginedUser(session);
        //Se l'utente non è loggato non può accedere al dettaglio del gioco
        if(loginedUser==null){
            String error = "Accedi per continuare";
            request.setAttribute("error", error);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/home");
            dispatcher.forward(request, response);
        }
        else{
            String idGs = request.getParameter("idG");
            int idG = Integer.parseInt(idGs);
            Connection conn = MyUtils.getStoredConnection(request);

            try {
                Game game = Game.findGameById(conn, idG);
                List<Achievements> achis = Achievements.getAchievementsByGame(conn, idG);
                List<Review> revs = Review.getReviewByGame(conn, idG);
                request.setAttribute("game", game);
                request.setAttribute("achis", achis);
                request.setAttribute("revs", revs);
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/gameView.jsp");
                dispatcher.forward(request, response);

            } catch (SQLException ex) {
                Logger.getLogger(GameServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
