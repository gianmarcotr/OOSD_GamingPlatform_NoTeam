package org.oosd.project.utils.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.oosd.project.beans.Game;
import org.oosd.project.beans.GameSession;
import org.oosd.project.beans.User;
import org.oosd.project.utils.DAO.interfaces.GameSessionDAO;

public class GameSessionDAOimpl implements GameSessionDAO{
    
    private static final String GET_SESSION_USER_GAME ="Select * from GameSession a where a.user = ? and a.game = ?";
    private static final String INSERT_GAME_SESSION = "Insert into GameSession(idGS, user, game, dateGS) values (?,?,?,?)";
    
    @Override
    public List<GameSession> getSessionByUserGame(Connection conn, User user, Game game) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(GET_SESSION_USER_GAME);
        pstm.setString(1, user.getUserName() );
        pstm.setInt(2, game.getIdG());
        ResultSet rs = pstm.executeQuery();
        List<GameSession> list = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("idGS");
            Date dateGS = rs.getDate("dateGS");
            GameSession gameSession = new GameSession(id, dateGS, user, game);      
            list.add(gameSession);
        }
        return list;
    }
    
    @Override
    public void insertGameSession(Connection conn, User user, Game game) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(INSERT_GAME_SESSION);
        pstm.setInt(1, 0);
        pstm.setString(2, user.getUserName());
        pstm.setInt(3, game.getIdG());
        Calendar cal = Calendar.getInstance();
        pstm.setDate(4, new java.sql.Date(cal.getTimeInMillis()));
        pstm.executeUpdate();
    }
}
