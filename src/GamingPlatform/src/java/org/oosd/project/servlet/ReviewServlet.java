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
import org.oosd.project.beans.Review;
import org.oosd.project.beans.User;
import org.oosd.project.utils.MyUtils;

/**
 *
 * @author gimmi
 */
@WebServlet(urlPatterns = { "/review"}) 
public class ReviewServlet extends HttpServlet {
    /**
     * Metodo per l'inserimento di una recensione nel DB
     */
    protected void insertRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection conn = MyUtils.getStoredConnection(request);
            HttpSession session = request.getSession();

            String text = request.getParameter("text");
            String votos = request.getParameter("voto");
            int voto = Integer.parseInt(votos);
            String idGs = request.getParameter("idG");
            int idG = Integer.parseInt(idGs);
            Game game = Game.findGameById(conn, idG);
            User user = MyUtils.getLoginedUser(session);
            Review rev = new Review(0, text, voto, 0, user, game);
            Review.insertReview(conn, rev);   
            //request.getSession().setAttribute("rev", rev);
            response.sendRedirect("game?idG="+idG);

        } catch (SQLException ex) {
            Logger.getLogger(ReviewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Accetta una recensione
    **/
    protected void acceptRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException{
            String idRs = request.getParameter("idR");
            int idR = Integer.parseInt(idRs);
            Connection conn = MyUtils.getStoredConnection(request);
            Review.acceptReview(conn, idR);
           getRequest(request, response);
    }
    
    /**
     * Rifiuta una recensione
    **/
    protected void rejectRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException{
            String idRs = request.getParameter("idR");
            int idR = Integer.parseInt(idRs);
            Connection conn = MyUtils.getStoredConnection(request);
            Review.rejectReview(conn, idR);
            getRequest(request, response);
    }

    /**
     * Ritrona una lista della recensioni in attesa di essere accettate o rifiutate da un moderatore
     *
     * @return lista delle recensioni in attesa di esssere accettate
     */
    protected void getRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException{
            Connection conn = MyUtils.getStoredConnection(request);
            HttpSession session = request.getSession();
            List<Review> rev = Review.getReviewOnHold(conn);
            request.setAttribute("rev", rev);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/reviewView.jsp");
            dispatcher.forward(request, response);
    }
   
    /**
     * Controllo tipo di richiesta: InsertReview, AcceptReview, RejectReview;
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String check = request.getParameter("check");
            
            switch (check){
                case ("insert"):
                        insertRequest(request, response);
                        break;
                case ("accept"):
                        acceptRequest(request, response);
                        break;
                case ("reject"):
                        rejectRequest(request, response);
                        break;
                default:
                        getRequest(request, response);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReviewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

}
