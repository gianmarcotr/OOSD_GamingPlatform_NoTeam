package org.oosd.project.utils.DAO.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.oosd.project.beans.Achievements;
import org.oosd.project.beans.Game;
import org.oosd.project.beans.User;
import org.oosd.project.beans.UserAchievement;

public interface UserAchievementDAO {
    List<UserAchievement> getUserAchi (Connection conn, User user) throws SQLException;
    List<UserAchievement> getUserAchiByGame (Connection conn, User user, Game game) throws SQLException;
    void insertUserAchi(Connection conn, User user, Achievements achi) throws SQLException;
    void updateUserAchi(Connection conn, UserAchievement userAchi) throws SQLException;
}
