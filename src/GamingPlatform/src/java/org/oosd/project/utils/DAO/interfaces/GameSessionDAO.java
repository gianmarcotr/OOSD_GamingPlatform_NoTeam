package org.oosd.project.utils.DAO.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.oosd.project.beans.Game;
import org.oosd.project.beans.GameSession;
import org.oosd.project.beans.User;

public interface GameSessionDAO {
    List<GameSession> getSessionByUserGame(Connection conn, User user, Game game) throws SQLException;
    void insertGameSession(Connection conn, User user, Game game) throws SQLException;
}
