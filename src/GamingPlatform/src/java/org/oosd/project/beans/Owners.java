/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oosd.project.beans;

import org.oosd.project.handleGame.handleGameImpl;
import org.oosd.project.handleGame.handleGameInterface;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.oosd.project.utils.DAO.impl.OwnerDAOimpl;
import org.oosd.project.utils.DAO.interfaces.OwnerDAO;

/**
 *
 * @author gimmi
 */
@Entity
@Table(name = "Owners")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Owners.findAll", query = "SELECT o FROM Owners o")
    , @NamedQuery(name = "Owners.findByUserName", query = "SELECT o FROM Owners o WHERE o.userName = :userName")
    , @NamedQuery(name = "Owners.findByPassword", query = "SELECT o FROM Owners o WHERE o.password = :password")
    , @NamedQuery(name = "Owners.findByEmail", query = "SELECT o FROM Owners o WHERE o.email = :email")
    , @NamedQuery(name = "Owners.findByNome", query = "SELECT o FROM Owners o WHERE o.nome = :nome")})
public class Owners implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "userName")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "nome")
    private String nome;

    private static handleGameInterface handleGame = new handleGameImpl();
    private static OwnerDAO ownerDao = new OwnerDAOimpl();
    
    public Owners() {
    }

    public Owners(String userName, String password, String email, String nome) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.nome = nome;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static Owners findOwner(Connection conn, String userName) throws SQLException{
    	return ownerDao.findOwner(conn, userName);
    }
    public static void insertOwner(Connection conn, Owners owner) throws SQLException{
        ownerDao.insertOwner(conn, owner);
    }
    
    public static Owners findOwner(Connection conn, String userName, String password) throws SQLException{
        return ownerDao.findOwner(conn, userName, password);
    }
    
    public static int insertGame(Connection conn, Game game) throws SQLException {
        return handleGame.insertGame(conn, game);
    }
    
    public static void insertAchievement(Connection conn, Achievements achi) throws SQLException {
        handleGame.insertAchievement(conn, achi);
    }
    
    public static void removeGame(Connection conn, Game game) throws SQLException {
        handleGame.removeGame(conn, game);
    }
    
    public static void removeAchievement(Connection conn, Achievements achi) throws SQLException {
        handleGame.removeAchievement(conn, achi);
    }
    
    public static List<Game> getMyGames(Connection conn, Owners owner) throws SQLException {
        return handleGame.getMyGames(conn, owner);
    }
    public static List<Achievements> getMyGameAchievements(Connection conn, Game game) throws SQLException {
        return handleGame.getMyGameAchievements(conn, game);
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userName != null ? userName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Owners)) {
            return false;
        }
        Owners other = (Owners) object;
        if ((this.userName == null && other.userName != null) || (this.userName != null && !this.userName.equals(other.userName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.oosd.project.beans.Owners[ userName=" + userName + " ]";
    }
    
}
