package xyz.n7mn.dev.reachchecker;



public class PlayerData {


    private int vlA;
    private double maxreach;
    private double lastreach;
    private boolean alert;

    public PlayerData(){
        this.vlA = 0;
        this.maxreach = 0.0;
        this.lastreach = 0.0;
        this.alert = false;
    }
    //Auto Generated//

    public int getVLA() {
        return vlA;
    }

    public void setVLA(int vlA) {
        this.vlA = vlA;
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
}