/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.oosd.project.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.oosd.project.utils.DAO.interfaces.UserDAO;
import org.oosd.project.utils.DAO.impl.UserDAOimpl;

/**
 *
 * @author gimmi
 */
@Entity
@Table(name = "User")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findByUserName", query = "SELECT u FROM User u WHERE u.userName = :userName")
    , @NamedQuery(name = "User.findByNome", query = "SELECT u FROM User u WHERE u.nome = :nome")
    , @NamedQuery(name = "User.findByCognome", query = "SELECT u FROM User u WHERE u.cognome = :cognome")
    , @NamedQuery(name = "User.findByLvl", query = "SELECT u FROM User u WHERE u.lvl = :lvl")
    , @NamedQuery(name = "User.findByXp", query = "SELECT u FROM User u WHERE u.xp = :xp")
    , @NamedQuery(name = "User.findByModeratore", query = "SELECT u FROM User u WHERE u.moderatore = :moderatore")
    , @NamedQuery(name = "User.findByAdmin", query = "SELECT u FROM User u WHERE u.admin = :admin")
    , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")})
public class User implements Serializable {

    @OneToMany(mappedBy = "user")
    private Collection<GameSession> gameSessionCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user1")
    private Collection<UserAchievement> userAchievementCollection;

    @OneToMany(mappedBy = "user")
    private Collection<Review> reviewCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "userName")
    private String userName;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cognome")
    private String cognome;
    @Column(name = "lvl")
    private Integer lvl;
    @Column(name = "XP")
    private Integer xp;
    @Column(name = "moderatore")
    private Integer moderatore;
    @Column(name = "admin")
    private Integer admin;
    @Column(name = "password")
    private String password;
    @Column(name = "XPnextLvl")
    private int XPnextLvl;

    private static UserDAO userDao = new UserDAOimpl();

    public User() {
    }

    public User(String userName, String nome, String cognome, int lvl, int XP, int moderatore, int admin, String password, int XPnextLvl) {
        this.userName = userName;
        this.nome=nome;
        this.cognome=cognome;
        this.lvl=lvl;
        this.xp=XP;
        this.moderatore=moderatore;
        this.admin=admin;
        this.password=password;
        this.XPnextLvl=XPnextLvl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Integer getLvl() {
        return lvl;
    }

    public void setLvl(Integer lvl) {
        this.lvl = lvl;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public Integer getModeratore() {
        return moderatore;
    }

    public void setModeratore(Integer moderatore) {
        this.moderatore = moderatore;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getXPnextLvl(){
        return XPnextLvl;
    }
    public void setXPnextLvl(int XPnextLvl){
        this.XPnextLvl= XPnextLvl;
    }
    
    public static User findUser(Connection conn, String userName, String password) throws SQLException{
        return userDao.findUser(conn, userName, password);
        
    }
    public static User findUser(Connection conn, String userName) throws SQLException{
    	return userDao.findUser(conn, userName);
    }
    public static void updateUser(Connection conn, User user) throws SQLException{
    	userDao.updateUser(conn, user);
    }
    public static void insertUser(Connection conn, User user) throws SQLException{
        userDao.insertUser(conn, user);
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userName == null && other.userName != null) || (this.userName != null && !this.userName.equals(other.userName))) {
            return false;
        }
        return true;
    }

   
    
    
    @Override
    public String toString() {
        return "org.oosd.project.beans.User[ userName=" + userName + " ]";
    }

    @XmlTransient
    public Collection<Review> getReviewCollection() {
        return reviewCollection;
    }

    public void setReviewCollection(Collection<Review> reviewCollection) {
        this.reviewCollection = reviewCollection;
    }

    @XmlTransient
    public Collection<UserAchievement> getUserAchievementCollection() {
        return userAchievementCollection;
    }

    public void setUserAchievementCollection(Collection<UserAchievement> userAchievementCollection) {
        this.userAchievementCollection = userAchievementCollection;
    }

    @XmlTransient
    public Collection<GameSession> getGameSessionCollection() {
        return gameSessionCollection;
    }

    public void setGameSessionCollection(Collection<GameSession> gameSessionCollection) {
        this.gameSessionCollection = gameSessionCollection;
    }
    
}
