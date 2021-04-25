package xyz.n7mn.dev.reachchecker;

import java.util.UUID;


public class PlayerData {

    //public static HashMap<UUID, PlayerData> playerData = new HashMap<>();

    private final UUID uuid;
    private int vlA;
    private int vlB;
    private int cps;
    private int maxcps;
    private double maxreach;
    private double lastreach;
    private boolean alert;
    private boolean viewcps;

    public PlayerData(UUID uuid){
        this.uuid = uuid;
        this.vlA = 0;
        this.vlB = 0;
        this.cps = 0;
        this.maxcps = 0;
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

    public int getCps() {
        return cps;
    }

    public void setCps(int cps) {
        this.cps = cps;
    }

    public int getMaxcps() {
        return maxcps;
    }

    public void setMaxcps(int maxcps) {
        this.maxcps = maxcps;
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