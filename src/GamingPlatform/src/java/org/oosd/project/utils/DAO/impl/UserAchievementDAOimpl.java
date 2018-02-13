package org.oosd.project.utils.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.oosd.project.beans.Achievements;
import org.oosd.project.beans.Game;
import org.oosd.project.beans.User;
import org.oosd.project.beans.UserAchievement;
import org.oosd.project.utils.DAO.interfaces.UserAchievementDAO;


public class UserAchievementDAOimpl implements UserAchievementDAO  {
    private static final String GET_USER_ACHI ="Select * from UserAchievement a where a.user = ?";
    private static final String UPDATE_USER_ACHI = "Update UserAchievement a Set a.punteggio = ? , a.completed = ? where a.user = ? and a.achi= ?";
    private static final String INSERT_USER_ACHI = "Insert into UserAchievement(user, achi, punteggio, completed) values (?,?,?,?)";

    @Override
    public List<UserAchievement> getUserAchi(Connection conn, User user) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(GET_USER_ACHI);
        pstm.setString(1, user.getUserName());
        ResultSet   rs = pstm.executeQuery();
        List<UserAchievement> list = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("achi");
            Achievements achi = Achievements.findAchievementsById(conn, id);
            int punteggio = rs.getInt("punteggio");
            int completed = rs.getInt("completed");
            UserAchievement userAchi = new UserAchievement();
            userAchi.setUser1(user);
            userAchi.setAchievements(achi);
            userAchi.setPunteggio(punteggio);
            userAchi.setCompleted(completed);
            list.add(userAchi);
        }
        return list;
}
    @Override
    public List<UserAchievement> getUserAchiByGame (Connection conn, User user, Game game) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(GET_USER_ACHI);
        pstm.setString(1, user.getUserName());
        ResultSet   rs = pstm.executeQuery();
        List<UserAchievement> list = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("achi");
            Achievements achi = Achievements.findAchievementsById(conn, id);
            if(Objects.equals(achi.getGioco().getIdG(), game.getIdG())){
                int punteggio = rs.getInt("punteggio");
                int completed = rs.getInt("completed");
                UserAchievement userAchi = new UserAchievement();
                userAchi.setUser1(user);
                userAchi.setAchievements(achi);
                userAchi.setPunteggio(punteggio);
                userAchi.setCompleted(completed);
                list.add(userAchi);
            }
        }
        return list;
    }
    
    @Override
    public void insertUserAchi(Connection conn, User user, Achievements achi) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(INSERT_USER_ACHI);
        pstm.setString(1, user.getUserName());
        pstm.setInt(2, achi.getIdA());
        pstm.setInt(3, 0);
        pstm.setInt(4, 0);
        pstm.executeUpdate();
    }
    
    @Override
    public void updateUserAchi(Connection conn, UserAchievement userAchi) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(UPDATE_USER_ACHI);
        pstm.setInt(1, userAchi.getPunteggio());
        pstm.setInt(2, userAchi.getCompleted());
        pstm.setString(3, userAchi.getUser1().getUserName());
        pstm.setInt(4, userAchi.getAchievements().getIdA());
        pstm.executeUpdate();
    }
}
