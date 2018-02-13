package org.oosd.project.utils.DAO.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.oosd.project.beans.Game;

public interface GameDAO {
    List<Game> getGames(Connection conn) throws SQLException;
    Game findGameById(Connection conn, int idG) throws SQLException;
    void insertGame(Connection conn, Game game) throws SQLException;
}
