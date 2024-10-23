/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacman;

import java.util.Random;

/**
 *
 * @author Artemisia
 */
public class Bod {

    private double hore;
    private double dole;
    private double pravo;
    private double lavo;
    private int x;
    private int y;
    private int a;

    Bod(double hore, double dole, double pravo, double lavo, int x, int y) {
        this.hore = hore;
        this.dole = dole;
        this.pravo = pravo;
        this.lavo = lavo;
        this.x = x * 30;
        this.y = y * 30;

    }

    public double getHore() {
        return hore;
    }

    public void setHore(double hore) {
        this.hore = hore;
    }

    public double getDole() {
        return dole;
    }

    public void setDole(double dole) {
        this.dole = dole;
    }

    public double getPravo() {
        return pravo;
    }

    public void setPravo(double pravo) {
        this.pravo = pravo;
    }

    public double getLavo() {
        return lavo;
    }

    public void setLavo(double lavo) {
        this.lavo = lavo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String vratNahodnySmer() {
        Random r = new Random();
        int i = r.nextInt(0, 4);

        if (i < hore) {
            return "hore";
        } else if (i < hore + dole) {
            return "dole";
        } else if (i < hore + dole + pravo) {
            return "doprava";
        } else if (i < hore + dole + pravo + lavo) {
            return "dolava";
        }
        return "hore";
    }

}
