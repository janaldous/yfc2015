package yfcdb.member;

/**
 * Created by janaldoustorres on 19/05/15.
 */
public class YFCGroup {
    private String sector, cluster;

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
