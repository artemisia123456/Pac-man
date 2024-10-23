/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */
public class HighScore {

    ArrayList<String> Score = new ArrayList<String>();

    HighScore() {
        nacitaj();

    }

    public void uloz() {
        try {
            FileWriter skore = new FileWriter("src\\pics\\skore.txt", false);
            if (!Score.isEmpty()) {
                skore.append(Score.get(0)+ "\n");
            }
            if (Score.size() > 1) {
                skore.append(Score.get(1)+ "\n");
            }
            if (Score.size() > 2) {
                skore.append(Score.get(2));
            }

            skore.close();
        } catch (IOException ex) {
            Logger.getLogger(HighScore.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void nacitaj() {
        try {
            File subor = new File("src\\pics\\skore.txt");
            Scanner vstup = new Scanner(subor);
            if (vstup.hasNextLine()) {
                Score.add(vstup.nextLine());
            }
            if (vstup.hasNextLine()) {
                Score.add(vstup.nextLine());
            }
            if (vstup.hasNextLine()) {
                Score.add(vstup.nextLine());
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(HighScore.class.getName()).log(Level.SEVERE, null, ex);
        }
        vypisZoznam();

    }

    public void ulozDoZoznamu(String menoSkore) {
        //posledné skore pred smrťou uložiť do array 
        //ak je skore vačšie ako 0/1/2 tak nahradi 0/1/2
        //ak nemam 0 a 1 a 2 tak sa uloži ako 0
        
        if (!Score.isEmpty()) {
            if (posliSkore(menoSkore) > posliSkore(Score.get(0))) {
                Score.add(0, menoSkore);
            } else if (posliSkore(menoSkore) > posliSkore(Score.get(1))) {
                Score.add(1, menoSkore);
            } else if (posliSkore(menoSkore) > posliSkore(Score.get(2))) {
                Score.add(2, menoSkore);
            }
        } else {
            Score.add(0, menoSkore);
        }
        vypisZoznam();
    }
//Score - meno:skore

    //     Monika            Monika:20
    public String posliMeno(String x) {
        return x.split(":")[0];
    }

    public int posliSkore(String x) {
        return Integer.parseInt(x.split(":")[1]);
    }
    public void vypisZoznam (){
       // System.out.println("vypis");
        for (String string : Score) {
            //System.out.println(","+string);
        }
        
    }

    public ArrayList<String> getScore() {
        return Score;
    }

    public void setScore(ArrayList<String> Score) {
        this.Score = Score;
    }
    
}
