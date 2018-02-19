package org.oosd.project.utils.DAO.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.oosd.project.beans.Owners;
import org.oosd.project.utils.DAO.interfaces.OwnerDAO;


public class OwnerDAOimpl implements OwnerDAO{
        
        private static final String FINDUSERP = "Select * from Owners a where a.userName = ? and a.password= ? ";
        private static final String FINDUSER ="Select * from Owners a where a.userName = ?";
        private static final String INSERTUSER = "Insert into Owners (userName, password, email, nome) values (?, ?, ?, ?)";


        @Override
        public Owners findOwner(Connection conn, String userName, String password) throws SQLException{
                PreparedStatement pstm;
            pstm = conn.prepareStatement(FINDUSERP);
            pstm.setString(1, userName);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String email = rs.getString("email");
                String nome = rs.getString("nome");
                Owners owner = new Owners(userName, password, email, nome);
                return owner;
            }
            return null;
        }        

        @Override
    public Owners findOwner(Connection conn, String userName) throws SQLException {
        PreparedStatement pstm;
        pstm = conn.prepareStatement(FINDUSER);
        pstm.setString(1, userName);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            String password = rs.getString("password");
            String email = rs.getString("email");
            String nome = rs.getString("nome");
            Owners owner = new Owners(userName, password, email, nome);
            return owner;
        }
        return null;
    }

    
        @Override
    public void insertOwner(Connection conn, Owners owner) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(INSERTUSER);
        pstm.setString(1, owner.getUserName());
        pstm.setString(2, owner.getPassword());
        pstm.setString(3, owner.getEmail());
        pstm.setString(4, owner.getNome());
        pstm.executeUpdate();
    }
}
