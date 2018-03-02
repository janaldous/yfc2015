package yfcdb.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by janaldoustorres on 19/05/15.
 */
@Entity
public class YFCGroup {
	@Id
	@GeneratedValue
	private int id;
	
    @Column(name="sector")
	private String sector;
	
    @Column(name="cluster")
	private String cluster;
    
    public YFCGroup() {
    	
    }
    
    public YFCGroup(String sector, String cluster) {
        this.sector = sector;
        this.cluster = cluster;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }
}
