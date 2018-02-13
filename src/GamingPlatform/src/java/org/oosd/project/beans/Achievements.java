package org.oosd.project.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;
import org.oosd.project.utils.DAO.impl.AchievementDAOimpl;
import org.oosd.project.utils.DAO.interfaces.AchievementDAO;

/**
 *
 * @author gimmi
 */
@Entity
@Table(name = "Achievements")
public class Achievements implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "achievements")
    private Collection<UserAchievement> userAchievementCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idA")
    private Integer idA;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descr")
    private String descr;
    @Column(name = "punti")
    private Integer punti;
    @Column(name = "premioXP")
    private Integer premioXP;
    @JoinColumn(name = "gioco", referencedColumnName = "idG")
    @ManyToOne
    private Game gioco;
    
    private static AchievementDAO achiDao = new AchievementDAOimpl();

    public Achievements(int idA, String nome, String descr, int punti, int premioXP, Game game) {
        this.idA = idA;
        this.nome = nome;
        this.descr = descr;
        this.punti = punti;
        this.premioXP = premioXP;
        this.gioco = game;
    }

    public Achievements() {
        
    }

    public Integer getIdA() {
        return idA;
    }

    public void setIdA(Integer idA) {
        this.idA = idA;
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

    public Integer getPunti() {
        return punti;
    }

    public void setPunti(Integer punti) {
        this.punti = punti;
    }

    public Integer getPremioXP() {
        return premioXP;
    }

    public void setPremioXP(Integer premioXP) {
        this.premioXP = premioXP;
    }

    public Game getGioco() {
        return gioco;
    }

    public void setGioco(Game gioco) {
        this.gioco = gioco;
    }

    
    public static List<Achievements> getAchievementsByGame(Connection conn, int idG) throws SQLException{
        return achiDao.getAchievementsByGame(conn, idG);
    }
    public static Achievements findAchievementsById(Connection conn, int idA) throws SQLException{
        return achiDao.findAchievementById(conn, idA);
    }
    public static void insertAchievements(Connection conn, Achievements achi) throws SQLException{
        achiDao.insertAchievements(conn, achi);
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idA != null ? idA.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Achievements)) {
            return false;
        }
        Achievements other = (Achievements) object;
        if ((this.idA == null && other.idA != null) || (this.idA != null && !this.idA.equals(other.idA))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.oosd.project.beans.Achievements[ idA=" + idA + " ]";
    }

    @XmlTransient
    public Collection<UserAchievement> getUserAchievementCollection() {
        return userAchievementCollection;
    }

    public void setUserAchievementCollection(Collection<UserAchievement> userAchievementCollection) {
        this.userAchievementCollection = userAchievementCollection;
    }
    
}
