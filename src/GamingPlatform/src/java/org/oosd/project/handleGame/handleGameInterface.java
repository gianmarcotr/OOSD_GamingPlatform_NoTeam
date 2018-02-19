
package org.oosd.project.handleGame;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.oosd.project.beans.Achievements;
import org.oosd.project.beans.Game;
import org.oosd.project.beans.Owners;

public interface handleGameInterface {
    
    //CREATE
    int insertGame(Connection conn, Game game) throws SQLException;
    void insertAchievement(Connection conn, Achievements achievement)throws SQLException;
    
    //READ
    List<Game> getMyGames(Connection conn, Owners owner)throws SQLException;
    List<Achievements> getMyGameAchievements(Connection conn, Game game)throws SQLException;
    
    //UPDATE
    void updateGame(Connection conn, Game game) throws SQLException;
    void updateAchievement(Connection conn, Achievements achi) throws SQLException;
    
    //DELETE
    void removeGame(Connection conn, Game game)throws SQLException;
    void removeAchievement(Connection conn, Achievements achievement)throws SQLException;
    
}
