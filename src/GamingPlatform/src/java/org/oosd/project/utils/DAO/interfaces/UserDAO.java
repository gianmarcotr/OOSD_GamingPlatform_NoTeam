package org.oosd.project.utils.DAO.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

import org.oosd.project.beans.User;

public interface UserDAO {
        User findUser(Connection conn, String userName, String password) throws SQLException;
        User findUser(Connection conn, String userName) throws SQLException;
        void updateUser(Connection conn, User user) throws SQLException;
        void insertUser(Connection conn, User user) throws SQLException;
}