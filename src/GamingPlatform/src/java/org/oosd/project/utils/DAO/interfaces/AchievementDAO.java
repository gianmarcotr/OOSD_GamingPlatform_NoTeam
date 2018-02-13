package org.oosd.project.utils.DAO.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.oosd.project.beans.Achievements;

public interface AchievementDAO {
    List<Achievements> getAchievementsByGame(Connection conn, int idG) throws SQLException; 
    Achievements findAchievementById(Connection conn, int idA) throws SQLException;
    void insertAchievements(Connection conn, Achievements achi) throws SQLException;
}
