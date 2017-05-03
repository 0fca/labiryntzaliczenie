/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labiryntzaliczenie.gui;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Obsidiam
 */
public class UserObject extends JPanel{
    private String NAME = "UserObject";
    
    {
        this.setBackground(Color.BLUE);
    }
    
    @Override
    public void setName(String name){
        this.NAME = name;
    }
    
    
}
