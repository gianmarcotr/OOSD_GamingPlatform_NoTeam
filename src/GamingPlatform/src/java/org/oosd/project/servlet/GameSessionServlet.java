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
import org.oosd.project.beans.GameSession;
import org.oosd.project.beans.User;
import org.oosd.project.beans.UserAchievement;
import org.oosd.project.utils.MyUtils;

/**
 *
 * @author gimmi
 */
@WebServlet(urlPatterns = { "/gameSession"})
public class GameSessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();  
        User loginedUser =  MyUtils.getLoginedUser(session);
        Connection conn = MyUtils.getStoredConnection(request);
        String idGs = request.getParameter("idG");
        int idG = Integer.parseInt(idGs);
        Game game;
        try {
            game = Game.findGameById(conn, idG);
            
            List<GameSession> gameSession = GameSession.getSessionByUserGame(conn, loginedUser, game);
            if (gameSession.isEmpty()==true){
                //se l'utente è la prima volta che gioca, vengono aggiunti gli obiettivi del gioco nella tabella
                //UserAchievement
                List<Achievements> achis = Achievements.getAchievementsByGame(conn, idG);
                for(Achievements achi: achis){
                     UserAchievement.insertUserAchi(conn, loginedUser, achi);
                }
            }
            //viene inserita la sessione di gioco nel database
            GameSession.insertGameSession(conn, loginedUser, game);
            //recupero gli obiettivi in corso dell'utente
            List<UserAchievement> userAchi = UserAchievement.getUserAchiByGame(conn, loginedUser, game);
            
            request.setAttribute("userAchi", userAchi);
            request.setAttribute("game", game);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/playView.jsp");
            dispatcher.forward(request, response);
         } catch (SQLException ex) {
            Logger.getLogger(GameSessionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
