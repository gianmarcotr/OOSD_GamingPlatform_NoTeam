package org.oosd.project.utils.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.oosd.project.beans.Game;
import org.oosd.project.beans.Review;
import org.oosd.project.beans.User;
import org.oosd.project.utils.DAO.interfaces.ReviewDAO;

/**
 *
 * @author gimmi
 */
public class ReviewDAOimpl implements ReviewDAO {
        
    private static final String GET_REVIEW_GAME ="Select * from Review a where a.game = ? and a.stato = '1'";
    private static final String INSERT_REVIEW = "Insert into Review (idR, testo, voto, stato, user, game) values (?, ?, ?, ?, ?, ?)";
    private static final String GET_REVIEW_HOLD ="Select * from Review a where a.stato = '0'";
    private static final String ACCEPT_REVIEW = "Update Review a Set a.stato = '1' where a.idR = ? ";
    private static final String REJECT_REVIEW = "Update Review a Set a.stato = '2' where a.idR = ? ";
    
    @Override
    public List<Review> getReviewByGame(Connection conn, int idG) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(GET_REVIEW_GAME);
        pstm.setInt(1, idG);
        ResultSet   rs = pstm.executeQuery();
        List<Review> list = new ArrayList<>();
        Game game = Game.findGameById(conn, idG);
        while(rs.next()){
            int id = rs.getInt("idR");
            String testo = rs.getString("testo");
            int voto = rs.getInt("voto");
            int stato = rs.getInt("stato");
            String userN = rs.getString("user");
            User user = User.findUser(conn, userN);
            Review  rev = new Review(id, testo, voto, stato, user, game);        
            list.add(rev);
        }
        return list;
    }
    
    @Override
    public void insertReview(Connection conn, Review rev) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(INSERT_REVIEW);
        pstm.setInt(1, 0);
        pstm.setString(2, rev.getTesto());
        pstm.setInt(3, rev.getVoto());
        pstm.setInt(4, rev.getStato());
        pstm.setString(5, rev.getUser().getUserName());
        pstm.setInt(6, rev.getGame().getIdG());
        pstm.executeUpdate();
        
    }
    
    @Override
    public List<Review> getReviewOnHold(Connection conn) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(GET_REVIEW_HOLD);
        ResultSet   rs = pstm.executeQuery();
        List<Review> list = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("idR");
            String testo = rs.getString("testo");
            int voto = rs.getInt("voto");
            int stato = rs.getInt("stato");
            String userN = rs.getString("user");
            int idG = rs.getInt("game");
            User user = User.findUser(conn, userN);
            Game game = Game.findGameById(conn, idG);
            Review  rev = new Review(id, testo, voto, stato, user, game);     
            list.add(rev);
        }
        return list;
        }
    
    @Override
    public void acceptReview(Connection conn, int idR) throws SQLException{
          PreparedStatement pstm = conn.prepareStatement(ACCEPT_REVIEW);
          pstm.setInt(1, idR);
          pstm.executeUpdate();
    }
    @Override
    public void rejectReview(Connection conn, int idR) throws SQLException{
          PreparedStatement pstm = conn.prepareStatement(REJECT_REVIEW);
          pstm.setInt(1, idR);
          pstm.executeUpdate();
    }
}
