/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BrickBreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class PowerUps extends Rectangle{
    public int instance;
    public boolean dropped = true;
    public int xspeed=10;
    public int yspeed=3;
    public ImageIcon icon;
    public int x1;
    public int y1;
    public int x2;
    public int y2;
    public int xdirection=1;
    public int ydirection=1;
    public PowerUps(int x1,int y1,int x2,int y2){
        setBounds(x1,y1,icon.getIconWidth(),icon.getIconHeight());
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    public PowerUps(){
    }
        public void drawOn(Graphics g){
        g.setColor(Color.RED);
        g.fillOval(x1, y1, 10, 10);
    }
}
class ExtraBall extends PowerUps{        
        ExtraBall(int x1,int y1){
        icon = new ImageIcon("AddBall.png");
        setBounds(x1,y1,icon.getIconWidth(),icon.getIconHeight());
        this.x1 = x1;
        this.y1 = y1;      
        instance = 1;  
}
}
class ExpandPaddle extends PowerUps{
    
    ExpandPaddle(int x1,int y1,int xspeed, int yspeed, int xdirection, int ydirection){
        icon = new ImageIcon("ExpandPaddle.png");
        setBounds(x1,y1,icon.getIconWidth(),icon.getIconHeight());
        this.x1 = x1;
        this.y1 = y1;
        this.xspeed = xspeed;
        this.yspeed = yspeed;
        this.xdirection = xdirection;
        this.ydirection = ydirection;
        instance = 2;  
}
}
class ShrinkPaddle extends PowerUps{
    ShrinkPaddle(int x1, int y1,int xspeed, int yspeed,int xdirection, int ydirection){
        icon = new ImageIcon("ShrinkPaddle.png");
        setBounds(x1,y1,icon.getIconWidth(),icon.getIconHeight());
        this.x1 = x1;
        this.y1 = y1;
        this.xspeed = xspeed;
        this.yspeed = yspeed;
        this.xdirection = xdirection;
        this.ydirection = ydirection;
        instance = 3;  
    }  
}
class SpeedUp extends PowerUps{
        SpeedUp(int x1, int y1,int xspeed, int yspeed,int xdirection, int ydirection){
        icon = new ImageIcon("SpeedUp.png");
        setBounds(x1,y1,icon.getIconWidth(),icon.getIconHeight());
        this.x1 = x1;
        this.y1 = y1;
        this.xspeed = xspeed;
        this.yspeed = yspeed;
        this.xdirection = xdirection;
        this.ydirection = ydirection;
        instance = 4;  
    }  
}
class SlowDown extends PowerUps{
    SlowDown(int x1, int y1,int xspeed, int yspeed,int xdirection, int ydirection){
        icon = new ImageIcon("SlowDown.png");
        setBounds(x1,y1,icon.getIconWidth(),icon.getIconHeight());
        this.x1 = x1;
        this.y1 = y1;
        this.xspeed = xspeed;
        this.yspeed = yspeed;
        this.xdirection = xdirection;
        this.ydirection = ydirection;
        instance = 5;  
    }
}
class FasterSlower extends PowerUps{
    FasterSlower(int x1, int y1, int xspeed, int yspeed, int xdirection, int ydirection){
        icon = new ImageIcon("GettingFasterSlower.png");
        setBounds(x1,y1,icon.getIconWidth(),icon.getIconHeight());
        this.x1 = x1;
        this.y1 = y1;
        this.xspeed = xspeed;
        this.yspeed = yspeed;
        this.xdirection = xdirection;
        this.ydirection = ydirection;
        instance = 6;  
    }
}
class FasterFaster extends PowerUps{
    FasterFaster(int x1, int y1, int xspeed, int yspeed, int xdirection, int ydirection){
        icon = new ImageIcon("GettingFasterFaster.png");
        setBounds(x1,y1,icon.getIconWidth(),icon.getIconHeight());
        this.x1 = x1;
        this.y1 = y1;
        this.xspeed = xspeed;
        this.yspeed = yspeed;
        this.xdirection = xdirection;
        this.ydirection = ydirection;
        instance = 7;  
    }
}
class Drift extends PowerUps{
    Drift(int x1, int y1, int xspeed, int yspeed, int xdirection, int ydirection){
        icon = new ImageIcon("Drift.png");
        setBounds(x1,y1,icon.getIconWidth(),icon.getIconHeight());
        this.x1 = x1;
        this.y1 = y1;
        this.xspeed = xspeed;
        this.yspeed = yspeed;
        this.xdirection = xdirection;
        this.ydirection = ydirection;
        instance = 8;  
    }
}
class MirrorWise extends PowerUps{
    MirrorWise(int x1, int y1, int xspeed, int yspeed, int xdirection, int ydirection){
        icon = new ImageIcon("MirrorWise.png");
        setBounds(x1,y1,icon.getIconWidth(),icon.getIconHeight());
        this.x1 = x1;
        this.y1 = y1;
        this.xspeed = xspeed;
        this.yspeed = yspeed;
        this.xdirection = xdirection;
        this.ydirection = ydirection;
        instance = 9;  
    }
}
class RedPowder extends PowerUps{
    RedPowder(int x1, int y1, int xspeed, int yspeed, int xdirection, int ydirection){
        setBounds(x1, y1, 10,10);
        this.x1 = x1;
        this.y1 = y1;
        this.xspeed = xspeed;
        this.yspeed = yspeed;
        this.xdirection = xdirection;
        this.ydirection = ydirection;
        instance = 10;  
    }
}
class GoodPowups extends PowerUps{
    GoodPowups(int x1, int y1, int xspeed, int yspeed, int xdirection, int ydirection){
        icon = new ImageIcon("GoodPowups.png");
        setBounds(x1,y1,icon.getIconWidth(),icon.getIconHeight());
        this.x1 = x1;
        this.y1 = y1;
        this.xspeed = xspeed;
        this.yspeed = yspeed;
        this.xdirection = xdirection;
        this.ydirection = ydirection;
        instance = 11;  
    }
}
class StickyPaddle extends PowerUps{
    StickyPaddle(int x1, int y1, int xspeed, int yspeed, int xdirection, int ydirection){
        icon = new ImageIcon("StickyPaddle.png");
        setBounds(x1,y1,icon.getIconWidth(),icon.getIconHeight());
        this.x1 = x1;
        this.y1 = y1;
        this.xspeed = xspeed;
        this.yspeed = yspeed;
        this.xdirection = xdirection;
        this.ydirection = ydirection;
        instance = 12;       
}    
}
class GreenPowder extends PowerUps{
    GreenPowder(int x1, int y1, int xspeed, int yspeed, int xdirection, int ydirection){
        setBounds(x1, y1, 10,10);
        this.x1 = x1;
        this.y1 = y1;
        this.xspeed = xspeed;
        this.yspeed = yspeed;
        this.xdirection = xdirection;
        this.ydirection = ydirection;
    }
        public void drawOn(Graphics g){
        g.setColor(Color.green);
        g.fillOval(x1, y1, 10, 10);
    }
}