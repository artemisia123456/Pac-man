/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacman;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

/**
 *
 * @author MSI
 */
public class Okienko extends JFrame {
    
    Okienko (int sirka, int vyska){
        Platno S = new Platno();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        S.setBounds(0, 0, sirka, vyska);
        setBounds(100, 100, sirka, vyska);
        setContentPane(S);
        setVisible(true);
        setTitle("PacMan");
        S.run();
        
        
       
    }

    
}
