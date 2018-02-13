
package org.oosd.project.utils.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.oosd.project.beans.Achievements;
import org.oosd.project.beans.Game;
import org.oosd.project.utils.DAO.interfaces.AchievementDAO;


public class AchievementDAOimpl implements AchievementDAO{
    private static final String GETACHIEV_GAME ="Select * from Achievements a where a.gioco = ?";
    private static final String FIND_ACHI_BY_ID ="Select * from Achievements a where a.idA = ?";
    private static final String INSERT_ACHI = "Insert into Achievements (idA, nome, descr, punti, premioXP, gioco) values (?, ?, ?, ?, ?, ?)";
    @Override
    public List<Achievements> getAchievementsByGame(Connection conn, int idG) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(GETACHIEV_GAME);
        pstm.setInt(1, idG);
        ResultSet rs = pstm.executeQuery();
        List<Achievements> list = new ArrayList<>();
        Game game = Game.findGameById(conn, idG);
        while(rs.next()){
            int id = rs.getInt("idA");
            String nome = rs.getString("nome");
            String descr = rs.getString("descr");
            int punti = rs.getInt("punti");
            int premioXP = rs.getInt("premioXP");
            Achievements  achi = new Achievements(id, nome, descr, punti, premioXP, game);        
            list.add(achi);
        }
        return list;
    }
    public Achievements findAchievementById(Connection conn, int idA) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(FIND_ACHI_BY_ID);
        pstm.setInt(1, idA);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            String nome = rs.getString("nome");
            String descr = rs.getString("descr");
            int punti = rs.getInt("punti");
            int premioXP = rs.getInt("premioXP");
            int idG = rs.getInt("gioco");
            Game game = Game.findGameById(conn, idG);
            Achievements  achi = new Achievements(idA, nome, descr, punti, premioXP, game);        
            return achi;
        }
        return null;
    }
    
    public void insertAchievements(Connection conn, Achievements achi) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(INSERT_ACHI);
        pstm.setInt(1, achi.getIdA());
        pstm.setString(2, achi.getNome());
        pstm.setString(3, achi.getDescr());
        pstm.setInt(4, achi.getPunti());
        pstm.setInt(5, achi.getPremioXP());
        pstm.setInt(6, achi.getGioco().getIdG());
        pstm.executeUpdate();
    }
}
