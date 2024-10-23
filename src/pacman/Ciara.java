/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacman;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Artemisia
 */
public class Ciara {
    private int startX = 0;
    private int startY = 0;
    private int finishX = 0;
    private int finishY = 0;
    
    
    Ciara (int startX, int startY, int finishX, int finishY ){
        this.startX = startX;
        this.startY = startY;
        this.finishX = finishX;
        this.finishY = finishY;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getFinishX() {
        return finishX;
    }

    public void setFinishX(int finishX) {
        this.finishX = finishX;
    }

    public int getFinishY() {
        return finishY;
    }

    public void setFinishY(int finishY) {
        this.finishY = finishY;
    }
    
    public void vykreslenie (Graphics2D grfka){
        grfka.setStroke(new BasicStroke(10));
        grfka.drawLine(startX*30+15, startY*30+15, finishX*30+15, finishY*30+15);
        grfka.setStroke(new BasicStroke(1));
    }
    
    public Rectangle getR (){
        return new Rectangle (startX*30, startY*30, (finishX - startX +1)*30, 30*(finishY - startY+1));
    }
    
    
}
