/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BrickBreaker;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Ball extends Rectangle {
    public int drift;
    public double driftProbability;
    boolean isStatic = false;
    public boolean removed = false;
    public double equilibriumSpeedIncrementProbability = 0.03;
    public int equilibriumSpeed = 6;
    public double speed = calculateSpeed();
    public int xspeed = 3;
    public int yspeed = 3;
    public boolean xincreasing = true;
    public boolean yincreasing = true;
    public int x1;
    public int y1;
    public int x2 = 10;
    public int y2 = 10;

    public Ball(int x1, int y1, int x2, int y2) {
        setBounds(x1, y1, x2, y2);
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public void drawOn(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(x1, y1, x2, y2);
        g.setColor(Color.blue);
        g.fillOval(x1 + 1, y1 + 1, x2 - 2, y2 - 2);
    }

    public void setPosition(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public double calculateSpeed() {
        return Math.sqrt(Math.pow(xspeed, 2) + Math.pow(yspeed, 2));
    }

    public void reset() {
        equilibriumSpeedIncrementProbability = 0.03;
        equilibriumSpeed = 6;
        speed = calculateSpeed();
        xspeed = 3;
        yspeed = 3;
        drift = 0;
        driftProbability = 0;
    }
}
