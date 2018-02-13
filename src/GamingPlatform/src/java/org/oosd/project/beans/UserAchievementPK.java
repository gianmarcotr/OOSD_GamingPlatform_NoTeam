
package org.oosd.project.beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author gimmi
 */
@Embeddable
public class UserAchievementPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "user")
    private String user;
    @Basic(optional = false)
    @Column(name = "achi")
    private int achi;

    public UserAchievementPK() {
    }

    public UserAchievementPK(String user, int achi) {
        this.user = user;
        this.achi = achi;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getAchi() {
        return achi;
    }

    public void setAchi(int achi) {
        this.achi = achi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (user != null ? user.hashCode() : 0);
        hash += (int) achi;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserAchievementPK)) {
            return false;
        }
        UserAchievementPK other = (UserAchievementPK) object;
        if ((this.user == null && other.user != null) || (this.user != null && !this.user.equals(other.user))) {
            return false;
        }
        if (this.achi != other.achi) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.oosd.project.beans.UserAchievementPK[ user=" + user + ", achi=" + achi + " ]";
    }
    
}
