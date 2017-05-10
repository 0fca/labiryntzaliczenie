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
    ImageIcon p = new ImageIcon();
    private int X,Y = 0;
    
    {
        try {
            Image i = ImageIO.read(getClass().getResourceAsStream("amcia.png"));
            p.setImage(i);
            this.setIcon(p);
            this.setHorizontalAlignment(CENTER);
      
        } catch (IOException ex) {
            Logger.getLogger(UserObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
