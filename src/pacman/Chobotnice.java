/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Artemisia
 */
public class Chobotnice extends PacMann {

    private double casik = 0;
    private double xxx = 0;
    private double yyy = 0;
    private boolean nahodnyBol;
    private double casNahodneho = 0;
    private ArrayList <Bod> body = new ArrayList <Bod>();
    private PacMann p;
    private BufferedImage a;
    private BufferedImage b;
    

    Chobotnice(int x, int y, ArrayList<Ciara> ciary, PacMann p, BufferedImage a, BufferedImage b) {
        this.b = b;
        this.a = a;
        this.x = x;
        this.y = y;
        this.ciary = ciary;
        smer = "hore";
        spravBody();
        this.p = p;

    }
    
    public void spravBody (){
        Bod a = new Bod (1, 0, 0, 0, 9, 9);
        body.add(a);
    }

    public BufferedImage getA() {
        return a;
    }
    
    

    @Override
    public void vykresliHad(Graphics2D grfka) {
        if (smer == "dolava"){
            grfka.drawImage(b, (int)this.getR().getX(), (int)this.getR().getY(), null);
        } else {
            grfka.drawImage(a, (int)this.getR().getX(), (int)this.getR().getY(), null);
        }
        
       

    }
    
    public void nakresliDead (Graphics2D grfka, BufferedImage mrtva){
         grfka.drawImage(mrtva, (int)this.getR().getX(), (int)this.getR().getY(), null);
    }

    public void ai() {
        if (xxx == this.x && yyy == this.y) {
            Random r = new Random();
            int i = r.nextInt(0, 4);

            if (i == 0) {
                    smer = "dolava";
                }
                if (i == 1) {
                    smer = "doprava";
                }
                if (i == 2) {
                    smer = "hore";
                }
                if (i == 3) {
                    smer = "dole";
                }
        }
        
        xxx = this.x;
        yyy = this.y;
        
        for (Bod bod : body) {
            if (bod.getX() == this.x && bod.getY() == this.y){
                smer = bod.vratNahodnySmer();
            }
            
        }
        if (this.vidisCiNie()){
            if (p.x>this.x){
                smer = "doprava";
            }
            if (p.y>this.y){
                smer = "dole";
            }
            if (p.x<this.x){
                smer = "dolava";
            }
            if (p.y<this.y){
                smer = "hore";
            }
        }

        

    }

    @Override
    public void update() {
        if (System.currentTimeMillis() - cas > rychlost) {
            cas = System.currentTimeMillis();
            double xx = this.x;
            double yy = this.y;

            pohniSa();
            //cesta.add(smer);
            // cesta.removeFirst();
            kontrola(xx, yy, true);
            ai();
        }

    }

    public String najlepsiSmer() {
        int pocitadloHore = 0;
        int pocitadloDole = 0;
        int pocitadloDoprava = 0;
        int pocitadloDolava = 0;

        Rectangle2D r = this.getR();

        while (true) {
            r.setRect(r.getX() + 30, r.getY(), 30, 30);

            if (this.skontrolujCh(r) == false) {
                pocitadloDoprava++;
            } else {
                break;
            }

        }
        r = this.getR();
        while (true) {
            r.setRect(r.getX() - 30, r.getY(), 30, 30);

            if (this.skontrolujCh(r) == false) {
                pocitadloDolava++;
            } else {
                break;
            }

        }
        r = this.getR();
        while (true) {
            r.setRect(r.getX(), r.getY() + 30, 30, 30);

            if (this.skontrolujCh(r) == false) {
                pocitadloDole++;
            } else {
                break;
            }

        }
        r = this.getR();
        while (true) {
            r.setRect(r.getX(), r.getY() - 30, 30, 30);

            if (this.skontrolujCh(r) == false) {
                pocitadloHore++;
            } else {
                break;
            }

        }

        if (pocitadloHore - 1 > pocitadloDole && pocitadloHore - 1 > pocitadloDolava && pocitadloHore - 1 > pocitadloDoprava) {
            return "hore";
        } else if (pocitadloDole - 1 > pocitadloHore && pocitadloDole - 1 > pocitadloDolava && pocitadloDole - 1 > pocitadloDoprava) {
            return "dore";
        } else if (pocitadloDoprava - 1 > pocitadloHore && pocitadloDoprava - 1 > pocitadloDolava && pocitadloDoprava - 1 > pocitadloDole) {
            return "doprava";
        } else if (pocitadloDolava - 1 > pocitadloHore && pocitadloDolava - 1 > pocitadloDoprava && pocitadloDolava - 1 > pocitadloDole) {
            return "dolava";
        } else {
            return "hore";
        }

    }

    public boolean skontrolujCh(Rectangle2D r) {
        //System.out.println(ciary.size());
        for (Ciara ciara : ciary) {

            if (ciara.getR().intersects(r)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean vidisCiNie (){
        Rectangle r = new Rectangle();
        
        r.setFrameFromDiagonal(this.x+1, this.y+1, p.x, p.y);
        
        return !this.skontrolujCh(r);
    }
    
    public void chobotnicaReset (){
        this.setX(9*30);
        this.setY(9*30);
    }

}
