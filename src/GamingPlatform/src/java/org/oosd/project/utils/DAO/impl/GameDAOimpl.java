package org.oosd.project.utils.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.oosd.project.beans.Game;
import org.oosd.project.utils.DAO.interfaces.GameDAO;


public class GameDAOimpl implements GameDAO {
    private static final String GETGAMES ="Select * from Game";
    private static final String FINDGAME ="Select * from Game a where a.idG = ?";
    private static final String INSERT_GAME = "Insert into Game (idG, nome, descr, genere, Owner) values (?, ?, ?, ?,?)";
    @Override
    public List<Game> getGames(Connection conn) throws SQLException {
        PreparedStatement pstm;
        pstm = conn.prepareStatement(GETGAMES);
        ResultSet rs = pstm.executeQuery();
        List<Game> list = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("idG");
            String nome = rs.getString("nome");
            String descr = rs.getString("descr");
            String genere = rs.getString("genere");
            String Owner = rs.getString("Owner");
            Game game = new Game(id, nome, descr, genere, Owner);
            list.add(game);
        }
        return list;
    }
    
    @Override
    public Game findGameById(Connection conn, int idG) throws SQLException{
        PreparedStatement pstm;
        pstm = conn.prepareStatement(FINDGAME);
        pstm.setInt(1, idG);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("idG");
            String nome = rs.getString("nome");
            String descr = rs.getString("descr");
            String genere = rs.getString("genere");
            Game game = new Game(id, nome, descr, genere, null);
            return game;
        }
        return null;
    }
    
    @Override
    public void insertGame(Connection conn, Game game) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(INSERT_GAME);
        pstm.setInt(1, game.getIdG());
        pstm.setString(2, game.getNome());
        pstm.setString(3, game.getDescr());
        pstm.setString(4, game.getGenere());
        pstm.setString(5, null);
        pstm.executeUpdate();
    }
}
