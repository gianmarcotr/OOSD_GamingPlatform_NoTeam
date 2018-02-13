package org.oosd.project.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.oosd.project.utils.DAO.impl.ReviewDAOimpl;
import org.oosd.project.utils.DAO.interfaces.ReviewDAO;

/**
 *
 * @author gimmi
 */
@Entity
@Table(name = "Review")

public class Review implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idR")
    private Integer idR;
    @Column(name = "testo")
    private String testo;
    @Column(name = "voto")
    private Integer voto;
    @Column(name = "stato")
    private Integer stato;
    @JoinColumn(name = "user", referencedColumnName = "userName")
    @ManyToOne
    private User user;
    @JoinColumn(name = "game", referencedColumnName = "idG")
    @ManyToOne
    private Game game;
    private static ReviewDAO reviewDao = new ReviewDAOimpl();

    public Review() {
    }

    public Review(Integer idR, String testo, Integer voto, Integer stato, User user, Game game) {
        this.idR = idR;
        this.testo = testo;
        this.voto = voto;
        this.stato = stato;
        this.user = user;
        this.game = game;
               
    }

    public Integer getIdR() {
        return idR;
    }

    public void setIdR(Integer idR) {
        this.idR = idR;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Integer getVoto() {
        return voto;
    }

    public void setVoto(Integer voto) {
        this.voto = voto;
    }

    public Integer getStato() {
        return stato;
    }

    public void setStato(Integer stato) {
        this.stato = stato;
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

    public static List<Review> getReviewByGame(Connection conn, int idG) throws SQLException{
        ReviewDAO reviewDao = new ReviewDAOimpl();
        return reviewDao.getReviewByGame(conn, idG);
        
    }
    
    public static void insertReview(Connection conn, Review rev) throws SQLException{
        reviewDao.insertReview(conn, rev);
    }
    
    public static List<Review> getReviewOnHold(Connection conn) throws SQLException{
        return reviewDao.getReviewOnHold(conn);
    }
    
    public static void acceptReview(Connection conn, int idR) throws SQLException{
        reviewDao.acceptReview(conn, idR);
    }
    public static void rejectReview(Connection conn, int idR) throws SQLException{
        reviewDao.rejectReview(conn, idR);
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idR != null ? idR.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Review)) {
            return false;
        }
        Review other = (Review) object;
        if ((this.idR == null && other.idR != null) || (this.idR != null && !this.idR.equals(other.idR))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.oosd.project.beans.Review[ idR=" + idR + " ]";
    }
    
}
