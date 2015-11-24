/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BrickBreaker;
import java.util.Random;
import javax.swing.JFrame;
 //FRAME.setExtendedState(JFrame.MAXIMIZED_BOTH);
public class Test{  
    static int FRAMEWIDTH = 1370;
    static int FRAMEHEIGHT = 730;
    public static void main (String [] args) throws InterruptedException{
        JFrame frame = new JFrame();
        int currentLevel=10;
        Panel panel = new Panel(currentLevel);
        frame.setBounds(0,0,FRAMEWIDTH,FRAMEHEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
        while(true){
            if(panel.levelPassed){
                currentLevel++;
                Random r = new Random ();
                int a = r.nextInt(4);
                if (a ==0){
                panel.infoBox("You are a master!","Congratulations!");
                }
                else if (a ==1){
                    panel.infoBox("Bravo","Helal olsun!");	
                }
                else if ( a==2){
                    panel.infoBox("Prosun sen!","Prooooo");
                }
                else if ( a==3){
                    panel.infoBox("Go on","Very good!");
                }
                frame.remove(panel);
                panel = new Panel(currentLevel);
                frame.add(panel);
                frame.setVisible(true);
            }
            panel.bounce();
            panel.ballSpeed();
            panel.powupMove();
            Thread.sleep(15);
        }
    }
}


