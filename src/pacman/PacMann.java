/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacman;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author MSI
 */
public class PacMann {

    protected double x = 9 * 30;
    protected double y = 13 * 30;
    protected double cas = 0;
    protected String smer = "";
    protected BufferedImage PM1 = null;
    protected BufferedImage PM2 = null;
    protected int Score = 0;

    protected HighScore HS = new HighScore();
    protected String Meno = "";
    protected ArrayList<Ciara> ciary;
    protected String terazSmer = "";
    protected String s = "";
    protected Platno p;
    protected int rychlost = 7;
    protected double casSpapania = 0;
    private boolean agresivita;
    private int zivoty = 3;

    PacMann(ArrayList<Ciara> ciary, Platno thisP) {
        p = thisP;
        this.ciary = ciary;
        try {
            PM1 = ImageIO.read(new File("src\\pics\\PM1.png"));
            PM2 = ImageIO.read(new File("src\\pics\\PM2.png"));
        } catch (IOException ex) {
            Logger.getLogger(PacMann.class.getName()).log(Level.SEVERE, null, ex);
        }
        Meno = (String) JOptionPane.showInputDialog(
                null,
                "MENO:\n",
                "Zadaj meno hráča",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
        if (Meno == null || Meno.isEmpty()) {
            System.exit(0);
        }
    }

    PacMann() {

    }

    public String getTerazSmer() {
        return terazSmer;
    }

    public void setTerazSmer(String terazSmer) {
        this.terazSmer = terazSmer;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;

    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;

    }

    public void update() {

        smer = terazSmer;
        //System.out.println("daco");
        //int rychlost = 7;
        /* if (this.getScore() >= 10) {
            rychlost -= 70;
        }
        if (this.getScore() >= 20) {
            rychlost -= 30;
        }
        if (this.getScore() >= 30) {
            rychlost -= 15;
        }
        if (this.getScore() >= 40) {
            rychlost -= 17;
        }
        if (this.getScore() >= 50) {
            rychlost -= 15;
        }
        if (this.getScore() >= 60) {
            rychlost -= 10;
        }*/

        if (System.currentTimeMillis() - cas > rychlost) {
            cas = System.currentTimeMillis();
            double xx = this.x;
            double yy = this.y;

            pohniSa();
            //cesta.add(smer);
            // cesta.removeFirst();

            kontrola(xx, yy, true);

        }

        if (rychlost < 7) {
            if (System.currentTimeMillis() >= casSpapania + 10000) {
                rychlost = 7;
                agresivita = false;
            }

        }
        this.spapaj();
        this.spapajVG();
        this.agresor();
    }

    public void kontrola(double xx, double yy, boolean prvaKontrola) {

        if (skontroluj() == false) {
            // System.out.println(x + " "+y );
            s = this.getSmer();

            return;
        } else {

            setX(xx);
            setY(yy);
            //System.out.println("somarina");
            if (!prvaKontrola) {
                return;
            }

            this.setSmer(s);
            pohniSa();
            kontrola(xx, yy, false);
        }
    }

    public void dakeVykreslenie(Graphics2D d) {

        d.drawString("teraz smer: " + terazSmer, 200, 800);
        d.drawString("smer: " + smer, 200, 830);
        d.drawString("s: " + s, 200, 860);

    }

    public void pohniSa() {
        if ("doprava".equals(smer)) {
            x++;

            if (x >= 18 * 30) {
                x = 0;
            }
        }
        if ("dolava".equals(smer)) {
            x--;

            if (x <= -1) {
                x = 18 * 30;
            }
        }
        if ("dole".equals(smer)) {
            y++;

            if (y >= 19 * 30) {
                y = 0;
            }
        }
        if ("hore".equals(smer)) {
            y--;

            if (y <= -1) {
                y = 19 * 30;
            }
        }

    }

    public String getSmer() {
        return smer;
    }

    public void setSmer(String smer) {
        this.smer = smer;
    }

    public void vykresliHad(Graphics2D grfka) {
        // grfka.drawImage(this.getMlok(), this.getX(), this.getY(), null);
        //grfka.setColor(Color.red);
        //zmenFarbu(grfka);
        //grfka.fillRect((int)(this.getX() * 30)+2, (int)(this.getY() * 30)+2, 25, 25);
        //grfka.setColor(Color.BLUE);
        //grfka.fill (this.getR());

        this.dakeVykreslenie(grfka);
        pocitadlo++;
        if (pocitadlo == 200) {
            pocitadlo = 0;
        }
        double rotationRequired = 0;
        
        if (smer == "hore") {
            rotationRequired = Math.toRadians(90);
        } else if (smer == "doprava") {
            rotationRequired = Math.toRadians(180);
        } else if (smer == "dole") {
            rotationRequired = Math.toRadians(270);
        }
        double locationX = PM1.getWidth() / 2;
        double locationY = PM1.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        if (pocitadlo < 100) {
           grfka.drawImage(op.filter(PM1, null), (int) this.getX(), (int) this.getY(), null);
           
        } else {
           grfka.drawImage(PM2, (int) (this.getX()), (int) (this.getY()), null);
        }
        

        
        
    }

    private int pocitadlo = 0;

    public void spapaj() {

        for (int i = 0; i <= p.getGulicky().size() - 1; i++) {
            if (p.getGulicky().get(i).vratStvorec().intersects(this.getR())) {
                p.getGulicky().remove(i);
                i--;
                Score += 1;
            }
        }
    }

    public void spapajVG() {

        for (int i = 0; i <= p.getGule().size() - 1; i++) {
            if (p.getGule().get(i).vratStvorec().intersects(this.getR())) {
                p.getGule().remove(i);
                i--;
                Score += 1;
                rychlost = 4;
                casSpapania = System.currentTimeMillis();
                agresivita = true;
            }
        }
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

    private double a = 0;
    private double b = 0;

    public boolean skontroluj() {
        //System.out.println(ciary.size());
        for (Ciara ciara : ciary) {

            /*if(this.x >= ciara.getStartX() && this.x <= ciara.getFinishX() && 
                    this.y == ciara.getStartY()){
                return true;
                
            }
            if(this.y >= ciara.getStartY() && this.y <= ciara.getFinishY() && 
                    this.x == ciara.getStartX()){
                return true;
                
             */
            if (ciara.getR().intersects(this.getR())) {
                return true;
            }
        }
        return false;
    }

    public Rectangle2D getR() {
        return new Rectangle2D.Double(x, y, 30, 30);
    }

    public void reset() {
        //HS.ulozDoZoznamu(this.getMeno()+ ":" + this.getScore());
        // HS.uloz();
        this.setScore(0);
        this.setX(9 * 30);
        this.setY(13 * 30);
        this.setSmer("");
        p.getGule().clear();
        p.getGulicky().clear();
        p.maleGule();
        p.velkeGule();
        zivoty = 3;

        //cesta.clear();
    }

    public String getMeno() {
        return Meno;
    }

    public void setMeno(String Meno) {
        this.Meno = Meno;
    }

    public boolean isAgresivita() {
        return agresivita;
    }

    public void setAgresivita(boolean agresivita) {
        this.agresivita = agresivita;
    }

    public void agresor() {
        if (this.agresivita) {
            for (Chobotnice ch : p.getChobotnice()) {
                if (ch.getR().intersects(this.getR())) {
                    ch.chobotnicaReset();
                }
            }
        } else {
            for (Chobotnice ch : p.getChobotnice()) {
                if (ch.getR().intersects(this.getR())) {
                    this.setX(9 * 30);
                    this.setY(13 * 30);
                    if (zivoty > 0) {
                        zivoty--;
                    }

                    for (int i = 0; i <= p.getChobotnice().size() - 1; i++) {
                        p.getChobotnice().get(i).chobotnicaReset();
                    }
                }
            }
        }
    }

    public int getZivoty() {
        return zivoty;
    }

    public void setZivoty(int zivoty) {
        this.zivoty = zivoty;
    }

}
