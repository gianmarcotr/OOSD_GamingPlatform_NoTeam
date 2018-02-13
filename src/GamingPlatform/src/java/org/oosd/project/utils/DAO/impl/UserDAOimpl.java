package org.oosd.project.utils.DAO.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.oosd.project.beans.User;
import org.oosd.project.utils.DAO.interfaces.UserDAO;


public class UserDAOimpl implements UserDAO{
    
        private static final String FINDUSERP ="Select * from User a where a.userName = ? and a.password = ?";
        private static final String FINDUSER ="Select * from User a where a.userName = ?";
        private static final String UPDATEUSER = "Update User a set a.lvl = ? , a.XP = ?, a.XPnextLvl = ? where a.userName = ?";
        private static final String INSERTUSER = "Insert into User (userName, nome, cognome, lvl, XP, moderatore, admin, password, XPnextLvl) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        @Override
        public User findUser(Connection conn, String userName, String password) throws SQLException {
        PreparedStatement pstm;
        pstm = conn.prepareStatement(FINDUSERP);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            String nome = rs.getString("nome");
            String cognome = rs.getString("cognome");
            int lvl = rs.getInt("lvl");
            int XP = rs.getInt("XP");
            int moderatore = rs.getInt("moderatore");
            int admin = rs.getInt("admin");
            int XPnextLvl = rs.getInt("XPnextLvl");
            User user = new User(userName, nome, cognome, lvl, XP, moderatore, admin, password, XPnextLvl);
            return user;
        }
        return null;
    }
        

        @Override
    public User findUser(Connection conn, String userName) throws SQLException {
        PreparedStatement pstm;
        pstm = conn.prepareStatement(FINDUSER);
        pstm.setString(1, userName);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            String nome = rs.getString("nome");
            String cognome = rs.getString("cognome");
            int lvl = rs.getInt("lvl");
            int XP = rs.getInt("XP");
            int moderatore = rs.getInt("moderatore");
            int admin = rs.getInt("admin");
            String password = rs.getString("password");
            int XPnextLvl = rs.getInt("XPnextLvl");
            User user = new User(userName, nome, cognome, lvl, XP, moderatore, admin, password, XPnextLvl);
            return user;
        }
        return null;
    }
        @Override
    public void updateUser(Connection conn, User user) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(UPDATEUSER);
        pstm.setInt(1, user.getLvl());
        pstm.setInt(2, user.getXp());
        pstm.setInt(3, user.getXPnextLvl());
        pstm.setString(4, user.getUserName());
        pstm.executeUpdate();
    }
    
        @Override
    public void insertUser(Connection conn, User user) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(INSERTUSER);
        pstm.setString(1, user.getUserName());
        pstm.setString(2, user.getNome());
        pstm.setString(3, user.getCognome());
        pstm.setInt(4, user.getLvl());
        pstm.setInt(5, user.getXp() );
        pstm.setInt(6, user.getModeratore());
        pstm.setInt(7, user.getAdmin());
        pstm.setString(8, user.getPassword());
        pstm.setInt(9, user.getXPnextLvl());
        pstm.executeUpdate();
    }
}
