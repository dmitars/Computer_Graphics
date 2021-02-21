package model;

public class Resolution {
    int horDpi = 0;
    int vertDpi = 0;

    public Resolution(){}

    public Resolution(int horDpi, int vertDpi) {
        this.horDpi = horDpi;
        this.vertDpi = vertDpi;
    }

    public boolean isPositive() {
        return horDpi > 0 && vertDpi > 0;
    }

    public int getHorDpi() {
        return horDpi;
    }

    public void setHorDpi(int horDpi) {
        this.horDpi = horDpi;
    }

    public int getVertDpi() {
        return vertDpi;
    }

    public void setVertDpi(int vertDpi) {
        this.vertDpi = vertDpi;
    }
}
