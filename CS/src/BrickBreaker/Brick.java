/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BrickBreaker;

import java.awt.Rectangle;
import javax.swing.ImageIcon;
  public class Brick extends Rectangle{
   public boolean remove = false;
   public ImageIcon brick = new ImageIcon("brick.jpg");
   public double strength = 1;
   public int x1;
   public int y1;
   public int x2;
   public int y2;
   public Brick(int x1, int y1, int x2, int y2){
       setBounds(x1,y1,brick.getIconWidth(),brick.getIconHeight());
       this.x1 = x1;
       this.y1 = y1;
       this.x2 = x2;
       this.y2 = y2;
   }
   Brick(){
       
   }
   }
class StrongBrick extends Brick{
    StrongBrick(int x1, int y1, int x2, int y2){
       brick = new ImageIcon("StrongBrick.JPG");
       strength=2;
       setBounds(x1,y1,brick.getIconWidth(),brick.getIconHeight());
       this.x1 = x1;
       this.y1 = y1;
       this.x2 = x2;
       this.y2 = y2;
    }
}

