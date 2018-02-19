
package org.oosd.project.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
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

@WebServlet(urlPatterns = { "/play"})
public class PlayServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();  
        User loginedUser =  MyUtils.getLoginedUser(session);
        Connection conn = MyUtils.getStoredConnection(request);
        String idGs = request.getParameter("idG");
        int idG = Integer.parseInt(idGs);
        String successLvl = "n", successAchi="n";
        Random random = new Random();
        Game game;
        
        try {
            game = Game.findGameById(conn, idG);
            //
            List<UserAchievement> userAchi = UserAchievement.getUserAchiByGame(conn, loginedUser, game);
            //Simulo una sessione assegnando un punteggio casuale agli achievements dell'utente
            //Se ha raggiunto l'obiettivo, contrassegno l'achievement come completato e aggiorno il db 
            List<UserAchievement> userAchiCompleted = new ArrayList<>();
            List<UserAchievement> userAchiInCompleted = new ArrayList<>();
      
            
            for(UserAchievement ua: userAchi){
                if(ua.getCompleted()==0){
                    int n = ua.getAchievements().getPunti();
                    int k = random.nextInt(n);
                    int punteggio = ua.getPunteggio() + k;
                    if(punteggio >= ua.getAchievements().getPunti()){
                        ua.setPunteggio(ua.getAchievements().getPunti());
                        ua.setCompleted(1);
                        UserAchievement.updateUserAchi(conn, ua);
                        successAchi="Complimenti!Obiettivo completato";
                        loginedUser.setXp(loginedUser.getXp()+ua.getAchievements().getPremioXP());
                        if(loginedUser.getXp() >= loginedUser.getXPnextLvl()){
                            loginedUser.setLvl(loginedUser.getLvl() + 1);
                            loginedUser.setXPnextLvl(loginedUser.getXPnextLvl() + 1000);
                            successLvl="Complimenti sei salito di livello!";
                        }
                        //aggiorno dati utente
                        User.updateUser(conn, loginedUser);
                    }   
                    else{
                        ua.setPunteggio(punteggio);
                        ua.setCompleted(0);
                        UserAchievement.updateUserAchi(conn, ua);
                    }
                }
            }
            userAchi = UserAchievement.getUserAchiByGame(conn, loginedUser, game);
             for(UserAchievement ua: userAchi){
                if(ua.getCompleted()==1)
                    userAchiCompleted.add(ua);
                else
                    userAchiInCompleted.add(ua);
            }
            request.setAttribute("userAchi", userAchi);
            request.setAttribute("userAchiC", userAchiCompleted);
            request.setAttribute("userAchiI", userAchiInCompleted);
            request.setAttribute("game", game);
            request.setAttribute("successLvl", successLvl);
            request.setAttribute("successAchi", successAchi);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/playView.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PlayServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
