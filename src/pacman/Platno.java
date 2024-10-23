/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author MSI
 */
public class Platno extends JPanel implements KeyListener {

    private PacMann H;
    private BufferedImage jpg = new BufferedImage(716, 952, BufferedImage.TYPE_INT_RGB);
    private Graphics2D grfka;
    private Jedlo M;
    private BufferedImage pozadie;
    private ArrayList<Ciara> ciary = new ArrayList<Ciara>();
    private ArrayList<MalaGula> gulicky = new ArrayList<MalaGula>();
    private ArrayList<VelkaGula> gule = new ArrayList<VelkaGula>();
    private ArrayList<Chobotnice> chobotnice = new ArrayList<Chobotnice>();
    private ImageIcon fw;
    private JLabel label;
    private BufferedImage mrtva;

    Platno() {
        this.setLayout(null);
        este = new JButton();
        this.add(este);
        este.setText("ZNOVA");
        este.setBounds((9 * 30), (13 * 30), 90, 30);
        este.setVisible(false);
        este.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                H.reset();
                este.setVisible(false);
                label.setVisible(false);
            }
        });

        ImageIcon fw = new ImageIcon("src\\pics\\nie.gif");
        label = new JLabel(fw);
        label.setBounds(30, 30, 500, 500); // for example, you can use your own values
        this.add(label);
        label.setVisible(false);

        H = new PacMann(ciary, this);
        Chobotnice a;
        try {
            a = new Chobotnice(8 * 30, 9 * 30, ciary, H, ImageIO.read(new File("src\\pics\\blue.png")), ImageIO.read(new File("src\\pics\\blueLavo.png")));
            Chobotnice b = new Chobotnice(9 * 30, 9 * 30, ciary, H, ImageIO.read(new File("src\\pics\\red.png")), ImageIO.read(new File("src\\pics\\redlavo.png")));
            Chobotnice c = new Chobotnice(10 * 30, 9 * 30, ciary, H, ImageIO.read(new File("src\\pics\\orange.png")), ImageIO.read(new File("src\\pics\\orangeLavo.png")));
            chobotnice.add(a);
            chobotnice.add(b);
            chobotnice.add(c);
            mrtva = ImageIO.read(new File("src\\pics\\dead.png"));
        } catch (IOException ex) {
            Logger.getLogger(Platno.class.getName()).log(Level.SEVERE, null, ex);
        }

        grfka = (Graphics2D) jpg.getGraphics();
        this.grfka.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        this.grfka.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        this.grfka.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        this.addKeyListener(this);
        setFocusable(true);

        M = new Jedlo();

        try {
            pozadie = ImageIO.read(new File("src\\pics\\pozadie.png"));
        } catch (IOException ex) {
            Logger.getLogger(PacMann.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.vytvorCiary();
        this.maleGule();
        this.velkeGule();

    }

    public ArrayList<Chobotnice> getChobotnice() {
        return chobotnice;
    }

    public void setChobotnice(ArrayList<Chobotnice> chobotnice) {
        this.chobotnice = chobotnice;
    }

    public ArrayList<VelkaGula> getGule() {
        return gule;
    }

    public ArrayList<MalaGula> getGulicky() {
        return gulicky;
    }

    @Override
    public void paintComponent(Graphics g) { //vykresli na obrazovku
        // super.paintComponents(g);
        g.drawImage(jpg, 0, 0, this);

    }

    public void run() {
        for (;;) {
            update();
            hlavneVykreslenie();
            super.paintComponents(grfka);
            paintComponent(getGraphics());

        }
    }

    public void update() {
        if (H.getScore() >= 170) {
            return;
        }
        if (H.getZivoty() == 0) {
            return;
        }
        H.update();
        for (Chobotnice chobotnice1 : chobotnice) {
            chobotnice1.update();
        }

    }
    private Font f = new Font("Arial", Font.BOLD, 35);

    private JButton este;

    public void vyhra() {
        Font a = grfka.getFont();
        grfka.setColor(Color.black);
        grfka.fillRect(0, 0, 716, 952);
        grfka.setColor(Color.green);
        grfka.setFont(f);
        grfka.drawString("ABSOLUTNE DABLJU B)", 5 * 30, 9 * 30);
        este.setVisible(true);
        grfka.setFont(a);
        //este.update(grfka);
        label.setVisible(true);

    }

    public void prehra() {
        Font a = grfka.getFont();
        grfka.setColor(Color.black);
        grfka.fillRect(0, 0, 716, 952);
        grfka.setColor(Color.red);
        grfka.setFont(f);
        grfka.drawString("ABSOLUTNA PREHRA :c", 5 * 30, 9 * 30);
        este.setVisible(true);
        grfka.setFont(a);
        //este.update(grfka);
    }

    public void hlavneVykreslenie() {
        if (H.getScore() >= 170) {
            this.vyhra();
            return;
        }
        if (H.getZivoty() < 1) {
            this.prehra();
            return;
        }
        grfka.setColor(Color.black);
        grfka.fillRect(0, 0, 716, 952);
        grfka.setColor(Color.BLUE);
        for (Ciara ciara : ciary) {
            ciara.vykreslenie(grfka);
            //grfka.setColor(Color.red);
            //grfka.draw( ciara.getR());
            //grfka.drawRect((int)ciara.getR().getX(), (int)ciara.getR().getY(), (int)ciara.getR().getWidth(), (int)ciara.getR().getHeight());

        }

        H.vykresliHad(grfka);
        M.vykresliJedlo(grfka);
        //M.vykresliDruheJedlo(grfka);
        grfka.setColor(Color.YELLOW);
        grfka.drawString("SCORE:" + H.getScore(), 600, 15);
        grfka.setColor(Color.RED);
        grfka.drawString("ZIVOTY:" + H.getZivoty(), 600, 45);
        this.vykresliGulicky(grfka);
        this.vykresliGule(grfka);

        for (Chobotnice chobotnice1 : chobotnice) {
            if (H.isAgresivita() == true) {
                chobotnice1.nakresliDead(grfka, mrtva);

            } else {
                chobotnice1.vykresliHad(grfka);
            }
        }

    }
    
    

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT) {
            H.setSmer("dolava");
            H.setTerazSmer("dolava");
        }
        if (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN) {
            H.setSmer("dole");
            H.setTerazSmer("dole");
        }
        if (e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            H.setSmer("doprava");
            H.setTerazSmer("doprava");
        }
        if (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP) {
            H.setSmer("hore");
            H.setTerazSmer("hore");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void vytvorCiary() {

        ciary.add(new Ciara(0, 0, 18, 0));
        ciary.add(new Ciara(0, 18, 18, 18));

        ciary.add(new Ciara(0, 6, 3, 6));
        ciary.add(new Ciara(0, 8, 3, 8));
        ciary.add(new Ciara(0, 10, 3, 10));
        ciary.add(new Ciara(0, 12, 3, 12));
        ciary.add(new Ciara(3, 6, 3, 8));
        ciary.add(new Ciara(3, 10, 3, 12));

        ciary.add(new Ciara(15, 6, 18, 6));
        ciary.add(new Ciara(15, 8, 18, 8));
        ciary.add(new Ciara(15, 10, 18, 10));
        ciary.add(new Ciara(15, 12, 18, 12));
        ciary.add(new Ciara(15, 6, 15, 8));
        ciary.add(new Ciara(15, 10, 15, 12));

        ciary.add(new Ciara(0, 0, 0, 6));
        ciary.add(new Ciara(18, 0, 18, 6));
        ciary.add(new Ciara(0, 12, 0, 18));
        ciary.add(new Ciara(18, 12, 18, 18));

        ciary.add(new Ciara(9, 0, 9, 1));
        ciary.add(new Ciara(2, 2, 3, 2));
        ciary.add(new Ciara(5, 2, 7, 2));
        ciary.add(new Ciara(11, 2, 13, 2));
        ciary.add(new Ciara(15, 2, 16, 2));

        ciary.add(new Ciara(2, 4, 3, 4));
        ciary.add(new Ciara(7, 4, 11, 4));
        ciary.add(new Ciara(15, 4, 16, 4));
        ciary.add(new Ciara(9, 4, 9, 5));

        ciary.add(new Ciara(5, 4, 5, 8));
        ciary.add(new Ciara(5, 6, 6, 6));
        ciary.add(new Ciara(13, 4, 13, 8));
        ciary.add(new Ciara(12, 6, 13, 6));

        ciary.add(new Ciara(2, 16, 7, 16));
        ciary.add(new Ciara(5, 14, 5, 16));
        ciary.add(new Ciara(7, 14, 11, 14));
        ciary.add(new Ciara(9, 14, 9, 16));
        ciary.add(new Ciara(11, 16, 16, 16));
        ciary.add(new Ciara(13, 14, 13, 16));

        ciary.add(new Ciara(2, 14, 3, 14));
        ciary.add(new Ciara(5, 10, 5, 12));
        ciary.add(new Ciara(15, 14, 16, 14));
        ciary.add(new Ciara(13, 10, 13, 12));

        ciary.add(new Ciara(7, 8, 8, 8));
        ciary.add(new Ciara(7, 8, 7, 10));
        ciary.add(new Ciara(7, 10, 11, 10));
        ciary.add(new Ciara(11, 8, 11, 10));
        ciary.add(new Ciara(10, 8, 11, 8));
        ciary.add(new Ciara(7, 12, 11, 12));

        for (Ciara ciara : ciary) {
            //System.out.println(ciara.getR());

        }

    }

    public void maleGule() {

        for (int x = 0; x <= 18; x++) {
            for (int y = 0; y <= 18; y++) {
                MalaGula g = new MalaGula(x, y);

                if (this.prekryvaSa(g) == false) {

                    gulicky.add(g);

                    if ((g.x == 0 && g.y == 7)
                            || (g.x == 0 && g.y == 11)
                            || (g.x == 1 && g.y == 7)
                            || (g.x == 1 && g.y == 11)
                            || (g.x == 2 && g.y == 7)
                            || (g.x == 2 && g.y == 11)
                            || (g.x == 16 && g.y == 7)
                            || (g.x == 16 && g.y == 11)
                            || (g.x == 17 && g.y == 7)
                            || (g.x == 17 && g.y == 11)
                            || (g.x == 18 && g.y == 7)
                            || (g.x == 18 && g.y == 11)
                            || (g.x == 1 && g.y == 1)
                            || (g.x == 17 && g.y == 1)
                            || (g.x == 1 && g.y == 17)
                            || (g.x == 17 && g.y == 17)
                            || (g.x == 9 && g.y == 6)
                            || (g.x == 8 && g.y == 9)
                            || (g.x == 9 && g.y == 9)
                            || (g.x == 10 && g.y == 9)) {
                        gulicky.remove(g);

                    }

                }
            }
        }

    }

    public void velkeGule() {
        VelkaGula a = new VelkaGula(1, 1);
        VelkaGula b = new VelkaGula(17, 1);
        VelkaGula c = new VelkaGula(1, 17);
        VelkaGula d = new VelkaGula(17, 17);
        VelkaGula e = new VelkaGula(9, 6);

        gule.add(a);
        gule.add(b);
        gule.add(c);
        gule.add(d);
        gule.add(e);

    }

    public boolean prekryvaSa(Jedlo j) {
        // neprekryva
        // neprekryva
        // neprekryva
        // prekryva ++
        // prekryva ++

        //false ked sa neprekryva... true ked sa prekryva
        for (Ciara ciara : ciary) {

            if (!ciara.getR().intersects(j.vratStvorec())) {

            } else {
                return true;
            }
        }
        return false;

    }

    public void vykresliGulicky(Graphics g) {
        for (MalaGula malaGula : gulicky) {
            malaGula.vykresliJedlo(grfka);
        }
    }

    public void vykresliGule(Graphics g) {
        for (VelkaGula gula : gule) {
            gula.vykresliJedlo(grfka);
        }
    }

}
