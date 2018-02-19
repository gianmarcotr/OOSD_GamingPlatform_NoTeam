package org.oosd.project.utils.DAO.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

import org.oosd.project.beans.Owners;

public interface OwnerDAO {
        Owners findOwner(Connection conn, String userName, String Password) throws SQLException;
        Owners findOwner(Connection conn, String userName) throws SQLException;
        void insertOwner(Connection conn, Owners user) throws SQLException;
}