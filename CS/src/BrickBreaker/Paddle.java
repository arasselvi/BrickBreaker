/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BrickBreaker;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
public class Paddle extends Rectangle{
    public int x1;
    public int y1;
    public int x2;
    public int y2;
    public boolean sticky = false;
    //500, 600,100, 10
    public Paddle(int x1,int y1,int x2,int y2){
         setBounds(x1, y1, x2, y2);
         this.x1 = x1;
         this.x2 = x2;
         this.y1 = y1;
         this.y2 = y2;
    }
    public void drawOn(Graphics g){
      g.setColor(Color.black);
      g.fillRect(x1-1, y1-1, x2+2, y2+2);
      g.setColor(Color.blue);
      g.fillRect(x1, y1, x2, y2);
    }
}
        
   
