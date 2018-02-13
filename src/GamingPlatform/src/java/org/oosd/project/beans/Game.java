package org.oosd.project.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.oosd.project.utils.DAO.impl.GameDAOimpl;
import org.oosd.project.utils.DAO.interfaces.GameDAO;


/**
 *
 * @author gimmi
 */
@Entity
@Table(name = "Game")

public class Game implements Serializable {

    @OneToMany(mappedBy = "game")
    private Collection<GameSession> gameSessionCollection;

    @OneToMany(mappedBy = "game")
    private Collection<Review> reviewCollection;

    @OneToMany(mappedBy = "gioco")
    private Collection<Achievements> achievementsCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idG")
    private Integer idG;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descr")
    private String descr;
    @Column(name = "genere")
    private String genere;
    private static GameDAO gameDao = new GameDAOimpl();

    public Game(int idG, String nome, String descr, String genere) {
        this.idG = idG;
        this.nome = nome;
        this.descr = descr;
        this.genere = genere;
    }

    public Game() {
        
    }

    public Integer getIdG() {
        return idG;
    }

    public void setIdG(Integer idG) {
        this.idG = idG;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    
    public static List<Game> getGames(Connection conn) throws SQLException{
        return gameDao.getGames(conn);
    }
    
    public static Game findGameById(Connection conn, int idG) throws SQLException{
        return gameDao.findGameById(conn, idG);
    }
    
    public static void insertGame(Connection conn, Game game) throws SQLException{
        gameDao.insertGame(conn, game);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idG != null ? idG.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Game)) {
            return false;
        }
        Game other = (Game) object;
        if ((this.idG == null && other.idG != null) || (this.idG != null && !this.idG.equals(other.idG))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.oosd.project.beans.Game[ idG=" + idG + " ]";
    }

    @XmlTransient
    public Collection<Achievements> getAchievementsCollection() {
        return achievementsCollection;
    }

    public void setAchievementsCollection(Collection<Achievements> achievementsCollection) {
        this.achievementsCollection = achievementsCollection;
    }

    @XmlTransient
    public Collection<Review> getReviewCollection() {
        return reviewCollection;
    }

    public void setReviewCollection(Collection<Review> reviewCollection) {
        this.reviewCollection = reviewCollection;
    }

    @XmlTransient
    public Collection<GameSession> getGameSessionCollection() {
        return gameSessionCollection;
    }

    public void setGameSessionCollection(Collection<GameSession> gameSessionCollection) {
        this.gameSessionCollection = gameSessionCollection;
    }
    
}
