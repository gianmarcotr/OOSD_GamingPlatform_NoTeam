package org.oosd.project.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.oosd.project.utils.DAO.impl.UserAchievementDAOimpl;
import org.oosd.project.utils.DAO.interfaces.UserAchievementDAO;

/**
 *
 * @author gimmi
 */
@Entity
@Table(name = "UserAchievement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserAchievement.findAll", query = "SELECT u FROM UserAchievement u")
    , @NamedQuery(name = "UserAchievement.findByUser", query = "SELECT u FROM UserAchievement u WHERE u.userAchievementPK.user = :user")
    , @NamedQuery(name = "UserAchievement.findByAchi", query = "SELECT u FROM UserAchievement u WHERE u.userAchievementPK.achi = :achi")
    , @NamedQuery(name = "UserAchievement.findByPunteggio", query = "SELECT u FROM UserAchievement u WHERE u.punteggio = :punteggio")
    , @NamedQuery(name = "UserAchievement.findByCompleted", query = "SELECT u FROM UserAchievement u WHERE u.completed = :completed")})
public class UserAchievement implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserAchievementPK userAchievementPK;
    @Column(name = "punteggio")
    private Integer punteggio;
    @Column(name = "completed")
    private Integer completed;
    @JoinColumn(name = "user", referencedColumnName = "userName", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user1;
    @JoinColumn(name = "achi", referencedColumnName = "idA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Achievements achievements;
    private static UserAchievementDAO userAchiDao = new UserAchievementDAOimpl();

    public UserAchievement() {
    }

    public UserAchievement(UserAchievementPK userAchievementPK) {
        this.userAchievementPK = userAchievementPK;
    }

    public UserAchievement(String user, int achi) {
        this.userAchievementPK = new UserAchievementPK(user, achi);
    }

    public UserAchievementPK getUserAchievementPK() {
        return userAchievementPK;
    }

    public void setUserAchievementPK(UserAchievementPK userAchievementPK) {
        this.userAchievementPK = userAchievementPK;
    }

    public Integer getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(Integer punteggio) {
        this.punteggio = punteggio;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public Achievements getAchievements() {
        return achievements;
    }

    public void setAchievements(Achievements achievements) {
        this.achievements = achievements;
    }

    public static List<UserAchievement> getUserAchi(Connection conn, User user) throws SQLException{
        return userAchiDao.getUserAchi(conn, user);
    }    
     public static void insertUserAchi(Connection conn, User user, Achievements achi) throws SQLException{
        userAchiDao.insertUserAchi(conn, user, achi);
     }
     public static List<UserAchievement> getUserAchiByGame (Connection conn, User user, Game game) throws SQLException{
        return userAchiDao.getUserAchiByGame(conn, user, game);
     }
     public static void updateUserAchi(Connection conn, UserAchievement userAchi) throws SQLException{
         userAchiDao.updateUserAchi(conn, userAchi);
     }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userAchievementPK != null ? userAchievementPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserAchievement)) {
            return false;
        }
        UserAchievement other = (UserAchievement) object;
        if ((this.userAchievementPK == null && other.userAchievementPK != null) || (this.userAchievementPK != null && !this.userAchievementPK.equals(other.userAchievementPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.oosd.project.beans.UserAchievement[ userAchievementPK=" + userAchievementPK + " ]";
    }
    
}
