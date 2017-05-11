/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labiryntzaliczenie.gui;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Obsidiam
 */
public class Obstacle extends JLabel{
    {
        InputStream in = getClass().getResourceAsStream("ghost.png");
        try {
            Image i = ImageIO.read(in);
            ImageIcon im = new ImageIcon(i);
            this.setIcon(im);
            this.setSize(i.getWidth(this), i.getHeight(this));
        } catch (IOException ex) {
            Logger.getLogger(Obstacle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
