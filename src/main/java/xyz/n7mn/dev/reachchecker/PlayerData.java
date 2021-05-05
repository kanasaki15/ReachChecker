package xyz.n7mn.dev.reachchecker;

import java.util.UUID;


public class PlayerData {

    //public static HashMap<UUID, PlayerData> playerData = new HashMap<>();

    private final UUID uuid;
    private int vlA;
    private int vlB;
    private int cpsL;
    private int cpsR;
    private int maxcpsL;
    private int maxcpsR;
    private double maxreach;
    private double lastreach;
    private boolean alert;
    private boolean viewcps;

    public PlayerData(UUID uuid){
        this.uuid = uuid;
        this.vlA = 0;
        this.vlB = 0;
        this.cpsL = 0;
        this.cpsR = 0;
        this.maxcpsL = 0;
        this.maxcpsR = 0;
        this.maxreach = 0.0;
        this.lastreach = 0.0;
        this.alert = false;
        this.viewcps = false;
    }
    //Auto Generated//

    public int getVLA() {
        return vlA;
    }

    public void setVLA(int vlA) {
        this.vlA = vlA;
    }

    public int getVLB() {
        return vlB;
    }

    public void setVLB(int vlB) {
        this.vlB = vlB;
    }

    public int getCpsL() {
        return cpsL;
    }

    public void setCpsL(int cpsL) {
        this.cpsL = cpsL;
    }

    public int getCpsR() {
        return cpsR;
    }

    public void setCpsR(int cpsR) {
        this.cpsR = cpsR;
    }

    public int getMaxcpsL() {
        return maxcpsL;
    }

    public void setMaxcpsL(int maxcpsL) {
        this.maxcpsL = maxcpsL;
    }

    public int getMaxcpsR() {
        return maxcpsR;
    }

    public void setMaxcpsR(int maxcpsR) {
        this.maxcpsR = maxcpsR;
    }

    public double getMaxreach() {
        return maxreach;
    }

    public void setMaxreach(double maxreach) {
        this.maxreach = maxreach;
    }

    public double getLastreach() {
        return lastreach;
    }

    public void setLastreach(double lastreach) {
        this.lastreach = lastreach;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setIsalert(boolean isalert) {
        this.alert = isalert;
    }

    public boolean isViewcps() {
        return viewcps;
    }

    public void setViewcps(boolean viewcps) {
        this.viewcps = viewcps;
    }
}