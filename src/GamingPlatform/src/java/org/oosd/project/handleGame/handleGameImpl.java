
package org.oosd.project.handleGame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.oosd.project.beans.Achievements;
import org.oosd.project.beans.Game;
import org.oosd.project.beans.Owners;



public class handleGameImpl implements handleGameInterface {
    private static final String GETACHIEV_GAME ="Select * from Achievements a where a.gioco = ?";
    private static final String INSERT_GAME = "Insert into Game (idG, nome, descr, genere, Owner) values (?, ?, ?, ?, ?)";
    private static final String GET_MY_GAMES = "Select * from Game g where g.Owner = ?";
    private static final String INSERT_ACHI = "Insert into Achievements (idA, nome, descr, punti, premioXP, gioco) values (?, ?, ?, ?, ?, ?)";
    private static final String REMOVE_GAME = "Delete from Game g where g.idG = ?";
    private static final String REMOVE_ALL_ACHI_BY_GAME = "Delete from Achievements a where a.gioco = ?";
    private static final String REMOVE_ACHI = "Delete from Achievements a where a.idA = ?";
    private static final String UPDATE_GAME = "Update Game g set g.nome = ?, g.descr = ?, g.genere = ? where g.idG = ?";
    private static final String UPDATE_ACHI = "Update Achievement a set a.nome = ?, a.descr = ?, a.punti = ?, a.premioXP = ? where a.idA = ?";
    
    @Override
    public int insertGame(Connection conn, Game game) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(INSERT_GAME);
        pstm.setInt(1, game.getIdG());
        pstm.setString(2, game.getNome());
        pstm.setString(3, game.getDescr());
        pstm.setString(4, game.getGenere());
        pstm.setString(5, game.getOwner());
        pstm.executeUpdate();
        
        PreparedStatement pstm2 = conn.prepareStatement("select a.idG from Game a order by a.idG desc limit 1");
        ResultSet rs = pstm2.executeQuery();
        int idG=0; 
        if(rs.next())
             idG = rs.getInt("idG");
        
        return idG;
            
                
    }

    @Override
    public void insertAchievement(Connection conn, Achievements achi) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(INSERT_ACHI);
        pstm.setInt(1, achi.getIdA());
        pstm.setString(2, achi.getNome());
        pstm.setString(3, achi.getDescr());
        pstm.setInt(4, achi.getPunti());
        pstm.setInt(5, achi.getPremioXP());
        pstm.setInt(6, achi.getGioco().getIdG());
        pstm.executeUpdate();
    }

    @Override
    public void removeGame(Connection conn, Game game) throws SQLException {
        PreparedStatement pstm1 = conn.prepareStatement(REMOVE_GAME);
        PreparedStatement pstm2 = conn.prepareStatement(REMOVE_ALL_ACHI_BY_GAME);
        
        pstm1.setInt(1, game.getIdG());
        pstm2.setInt(1, game.getIdG());
        
        pstm2.executeUpdate();
        pstm1.executeUpdate();
        
    }

    @Override
    public void removeAchievement(Connection conn, Achievements achi) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(REMOVE_ACHI);
        pstm.setInt(1, achi.getIdA());
        pstm.executeUpdate();
    }

    @Override
    public List<Game> getMyGames(Connection conn, Owners owner) throws SQLException {
        PreparedStatement pstm;
        pstm = conn.prepareStatement(GET_MY_GAMES);
        pstm.setString(1, owner.getUserName());
        ResultSet rs = pstm.executeQuery();
        List<Game> list = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("idG");
            String nome = rs.getString("nome");
            String descr = rs.getString("descr");
            String genere = rs.getString("genere");
            Game game = new Game(id, nome, descr, genere, owner.getUserName());
            list.add(game);
        }
        return list;
    }

    @Override
    public List<Achievements> getMyGameAchievements(Connection conn, Game game) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(GETACHIEV_GAME);
        pstm.setInt(1, game.getIdG());
        ResultSet rs = pstm.executeQuery();
        List<Achievements> list = new ArrayList<>();
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
    
    @Override
    public void updateGame(Connection conn, Game game) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(UPDATE_GAME);
        pstm.setString(1, game.getNome());
        pstm.setString(2, game.getDescr());
        pstm.setString(3, game.getGenere());
        pstm.setInt(4, game.getIdG());
        
        pstm.executeUpdate();
    }
    
    @Override
    public void updateAchievement(Connection conn, Achievements achi) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(UPDATE_ACHI);
        pstm.setString(1, achi.getNome());
        pstm.setString(2, achi.getDescr());
        pstm.setInt(3, achi.getPunti());
        pstm.setInt(4, achi.getPremioXP());
        pstm.setInt(5, achi.getIdA());
        
        pstm.executeUpdate();
        
    }
}
