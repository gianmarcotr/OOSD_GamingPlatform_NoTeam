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
import org.oosd.project.beans.Achievements;
import org.oosd.project.beans.Game;
import org.oosd.project.beans.User;
import org.oosd.project.utils.MyUtils;

/**
 *
 * @author gimmi
 */
@WebServlet(urlPatterns = { "/admin"})
public class AdminServlet extends HttpServlet {

    protected void insertGameRequest(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
       
        Connection conn = MyUtils.getStoredConnection(request);
        String nome = request.getParameter("nome");
        String descr= request.getParameter("descr");
        String genere = request.getParameter("genere");
        Game game = new Game(0, nome, descr, genere, null);
        Game.insertGame(conn, game);
        
    }
    protected void insertAchiRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Connection conn = MyUtils.getStoredConnection(request);
        String nome = request.getParameter("nome");
        String descr= request.getParameter("descr");
        String puntis = request.getParameter("punti");
        int punti = Integer.parseInt(puntis);
        String premioXPs = request.getParameter("premioXP");
        int premioXP = Integer.parseInt(premioXPs);
        String idGs = request.getParameter("idG");
        int idG = Integer.parseInt(idGs);
        
        Game game = Game.findGameById(conn, idG);
        Achievements achi = new Achievements(0, nome, descr, punti, premioXP, game);
        Achievements.insertAchievements(conn, achi);
        
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
         try {
            Connection conn = MyUtils.getStoredConnection(request);
            HttpSession session = request.getSession();  
            User loginedUser =  MyUtils.getLoginedUser(session);
            //Controllo se l'utente è effivamente un admin, in caso negativo reindirizzo nella pagina profilo
            if(loginedUser.getAdmin() != 1){
                request.getSession().setAttribute("user", loginedUser);
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/profileView.jsp");
                dispatcher.forward(request, response);
            }
            //Controllo quale operazione intende svolgere l'admin: insertGame, insertAchi
            String check = request.getParameter("check");
            
            switch(check){
                case("game"):{
                    insertGameRequest(request, response);
                    break;
                }
                case("achi"):{
                    insertAchiRequest(request, response);
                    break;
                }
            }
            //ritorno lista gei giochi presenti nel sistema e utente loggato
            List<Game> games = Game.getGames(conn);
            request.setAttribute("games", games);
            request.getSession().setAttribute("user", loginedUser);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/adminView.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
