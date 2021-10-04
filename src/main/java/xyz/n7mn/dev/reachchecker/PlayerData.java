package xyz.n7mn.dev.reachchecker;


import java.util.ArrayList;
import java.util.List;

public class PlayerData {
    private boolean alert;
    private double buffer;
    private final List<Double> doubles = new ArrayList<>();

    public PlayerData(boolean alert) {
        this.alert = alert;
    }

    public PlayerData() {
        this.alert = false;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }

    public List<Double> getDoubles() {
        return doubles;
    }

    public void addDoubles(double Doubles) {
        this.doubles.add(Doubles);
    }

    public void remove(int index) {
        this.doubles.remove(index);
    }

    public double getBuffer() {
        return buffer;
    }

    public void setBuffer(double buffer) {
        this.buffer = buffer;
    }

    public void increaseBuffer(double buffer) {
        this.buffer += buffer;
    }

    public void decreaseBuffer(double buffer) {
        if(buffer > 0) this.buffer -= buffer;
    }
}