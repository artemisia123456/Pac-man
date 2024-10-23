/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Artemisia
 */
public class MalaGula extends Jedlo {

    
    MalaGula (int x, int y){
       setX(x);
       setY(y);
        
    }

    @Override
    public void nahodnaPozicia() {
        super.nahodnaPozicia(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void vykresliJedlo(Graphics2D grfka) {
        grfka.setColor(Color.YELLOW);
        grfka.fillOval(x*30+10, y*30+10, 10, 10);
    }
    
    public Rectangle vratStvorec (){
        return new Rectangle (x*30,y*30, 10,10);

    }
    
    
}
