package org.oosd.project.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.oosd.project.utils.DAO.impl.GameSessionDAOimpl;
import org.oosd.project.utils.DAO.interfaces.GameSessionDAO;

/**
 *
 * @author gimmi
 */
@Entity
@Table(name = "GameSession")
public class GameSession implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGS")
    private Integer idGS;
    @Column(name = "dateGS")
    @Temporal(TemporalType.DATE)
    private Date dateGS;
    @JoinColumn(name = "user", referencedColumnName = "userName")
    @ManyToOne
    private User user;
    @JoinColumn(name = "game", referencedColumnName = "idG")
    @ManyToOne
    private Game game;
    private static GameSessionDAO gameSessionDao = new GameSessionDAOimpl();

    public GameSession(int idGS, Date dateGS, User user, Game game) {
        this.idGS=idGS;
        this.dateGS=dateGS;
        this.user=user;
        this.game=game;
       
    }

    public GameSession() {
        
    }

    public Integer getIdGS() {
        return idGS;
    }

    public void setIdGS(Integer idGS) {
        this.idGS = idGS;
    }

    public Date getDateGS() {
        return dateGS;
    }

    public void setDateGS(Date dateGS) {
        this.dateGS = dateGS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public static List<GameSession> getSessionByUserGame(Connection conn, User user, Game game) throws SQLException{
        return gameSessionDao.getSessionByUserGame(conn, user, game);
    }
    
     public static void insertGameSession(Connection conn, User user, Game game) throws SQLException{
        gameSessionDao.insertGameSession(conn, user, game);
     }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGS != null ? idGS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GameSession)) {
            return false;
        }
        GameSession other = (GameSession) object;
        if ((this.idGS == null && other.idGS != null) || (this.idGS != null && !this.idGS.equals(other.idGS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.oosd.project.beans.GameSession[ idGS=" + idGS + " ]";
    }
    
}
