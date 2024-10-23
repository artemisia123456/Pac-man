/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacman;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author MSI
 */
public class Jedlo {

    protected int x = 0;
    protected int y = 0;
    private BufferedImage mafin = null;
    
    
    private int typJedla = 0;

    Jedlo() {
        /*try {
            mafin = ImageIO.read(new File("C:\\Users\\Artemisia\\Desktop\\mafin.png"));
        } catch (IOException ex) {
            Logger.getLogger(PacMann.class.getName()).log(Level.SEVERE, null, ex);
        }
        nahodnaPozicia*/

        

    }

    public void vykresliJedlo(Graphics2D grfka) {
        
           // grfka.drawImage(this.mafin, this.x * 37, this.y * 37, null);
        

        
        //this.vykresliDruheJedlo(grfka);
    }

    /*public void vykresliDruheJedlo(Graphics2D grfka) {
        grfka.drawImage(this.druheJedlo, this.x * 20, this.y * 20, null);
    }*/

    public void nahodnaPozicia() {
        Random pozicia = new Random();
        
        this.x = pozicia.nextInt(0, 35);
        this.y = pozicia.nextInt(0, 30);
        
        this.typJedla = pozicia.nextInt(0, 2);
    }

    

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public Rectangle vratStvorec (){
        return null;

    }
    
  
    

}
