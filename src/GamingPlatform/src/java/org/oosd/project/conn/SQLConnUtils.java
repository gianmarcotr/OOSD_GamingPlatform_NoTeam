package org.oosd.project.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnUtils {

    public static Connection getSQLConnection() throws ClassNotFoundException, SQLException{
        String hostName = "localhost";
        String dbName = "GamingPlatformDB";
        String userName = "root";
        String password = "mhmvq5ps";
        return getSQLConnection(hostName, dbName, userName, password);
    }
    
    public static Connection getSQLConnection(String hostName, String dbName, String userName, String password) throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }
}