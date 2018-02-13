package org.oosd.project.utils.DAO.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.oosd.project.beans.Review;


public interface ReviewDAO {
     List<Review> getReviewByGame(Connection conn, int idG) throws SQLException; 
     void insertReview(Connection conn, Review rev) throws SQLException;
     List<Review> getReviewOnHold(Connection conn) throws SQLException;
     void acceptReview(Connection conn, int idR) throws SQLException;
     void rejectReview(Connection conn, int idR) throws SQLException;
     
}
