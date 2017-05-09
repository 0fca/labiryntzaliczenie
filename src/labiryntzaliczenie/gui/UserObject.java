/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labiryntzaliczenie.gui;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Obsidiam
 */
public class UserObject extends JLabel{
    ImageIcon p = new ImageIcon(),pu = new ImageIcon(),pd = new ImageIcon(),pl = new ImageIcon(),pr = new ImageIcon();
    private int X,Y = 0;
    
    {
        try {
            Image i = ImageIO.read(getClass().getResourceAsStream("PacMan1.gif"));
            p.setImage(i);
            this.setIcon(p);
            this.setHorizontalAlignment(CENTER);
      
            this.setSize(48, 48);
            Image iu = ImageIO.read(getClass().getResourceAsStream("PacMan2up.gif"));
            pu.setImage(iu);
            
            Image id = ImageIO.read(getClass().getResourceAsStream("PacMan2down.gif"));
            pd.setImage(id);
            
            Image il = ImageIO.read(getClass().getResourceAsStream("PacMan2left.gif"));
            pl.setImage(il);
            
            Image ir = ImageIO.read(getClass().getResourceAsStream("PacMan2right.gif"));
            pr.setImage(ir);
        } catch (IOException ex) {
            Logger.getLogger(UserObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getX(){
        return X;
    }
    
    public int getY(){
        return Y;
    }
    
    public void setX(int x){
        this.X = x;
    }
    
    public void setY(int y){
        this.Y = y;
    }
    
    public void setGraphic(String direction){
        switch(direction){
            case "up":
                this.setIcon(pu);
                break;
            case "left":
                this.setIcon(pl);
                break;
            case "right":
                this.setIcon(pr);
                break;
            case "down":
                this.setIcon(pd);
                break;    
        }
    }
}
