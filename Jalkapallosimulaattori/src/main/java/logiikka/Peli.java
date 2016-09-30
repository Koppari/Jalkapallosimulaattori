package logiikka;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import kayttoliittyma.GUI;

/**
 * Huolehtii ottelun pyörittämisestä ja sen kirjoittamisesta tiedostoon.
 */
public class Peli {

    private static final Random R = new Random();
    private static final RandomEventGeneraattori REG = new RandomEventGeneraattori();
    public Joukkue x, y;

    public Peli() {
    }

    public Peli(Joukkue x, Joukkue y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Generoi matsin eventit 90 minuuttiin, kirjoittaa sen tiedostoon. Lopuksi
     * printtaa tilastoja.
     *
     * @param x Pelaajan joukkue
     * @param y Vastustajan joukkue
     *
     */
    public void matsinGenerointi(Joukkue x, Joukkue y) {
        double event = 0, laukausMahdollisuus = 0;
        //näiden hoitamiseen parempi ratkaisu
        int omatMaalit = 0, vihollisenMaalit = 0;
        int omatLaukaukset = 0, vihollisenLaukaukset = 0;

        //tiedoston kirjoittamiseen
        String s = "";

        try {
            PrintWriter tiedostokirjoitin = new PrintWriter("matsi.txt");
            for (int i = 0; i <= 90; i++) {
                event = R.nextDouble();
                s = omatMaalit + "-" + vihollisenMaalit + ", " + i + " min: ";

                if (event > 0.95) { //maali
                    boolean maali = REG.maali(x, y);
                    if (maali) {
                        omatLaukaukset++;
                        omatMaalit++;
                        s = s + "\n" + REG.maalinTekija(x) + ", teki maalin joukkueelle " + x.getNimi() + "!";
                    } else {
                        vihollisenLaukaukset++;
                        vihollisenMaalit++;
                        s = s + "\n" + REG.maalinTekija(x) + ", teki maalin joukkueelle " + y.getNimi() + "!";
                    }
                }

                if (event < 0.95 && event > 0.94) { //x laukoo tolppaan
                    omatLaukaukset++;
                    s = s + "\n" + REG.laukaus(x) + " Se osui tolppaan!";
                }

                if (event < 0.94 && event > 0.93) { //y laukoo tolppaan
                    vihollisenLaukaukset++;
                    s = s + "\n" + REG.laukaus(y) + " Se osui tolppaan!";
                }

                //laukaukset - jotkin laukauksista menevät ohi, jotkin tolppaan ja joistain maali
                //ensi viikoksi systeemi, jossa pelaajilla on laukauksia ja näistä laukauksista jotkut ohi, jotkut tolppaan ja jotkut maaliin
                if (event < 0.15) { //laukaus
                    laukausMahdollisuus = R.nextDouble();
                    if (laukausMahdollisuus < REG.joukkueenParemmuus(x, y)) {
                        s = s + "\n" + REG.laukaus(x) + " Ohi meni!";
                        omatLaukaukset++;
                    }
                    if (laukausMahdollisuus > REG.joukkueenParemmuus(x, y)) {
                        s = s + "\n" + REG.laukaus(y) + " Ohi meni!";
                        vihollisenLaukaukset++;
                    }
                }

                tiedostokirjoitin.println(s);

            }

            tiedostokirjoitin.println("Peli päättyi " + omatMaalit + "-" + vihollisenMaalit + "!");
            tiedostokirjoitin.println("\nTilastoja:");
            tiedostokirjoitin.println("Joukkueen " + x.getNimi() + " laukaukset: " + omatLaukaukset);
            tiedostokirjoitin.println("Joukkueen " + y.getNimi() + " laukaukset: " + vihollisenLaukaukset);

            tiedostokirjoitin.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Lukee matsin tapahtumat tiedostosta.
     * 
     *
     * @return Matsin tapahtumat.
     */
    public String matsinLuku() {
        String s = "";
        try (BufferedReader br = new BufferedReader(new FileReader("matsi.txt"))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                s = s + line + "\n";
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return s;
    }

    /**
     * Luo satunnaiset joukkueet.
     * 
     *
     * @param omaNimi Oman joukkuuen nimi.
     * @param vihollisNimi Vastustajan joukkueen nimi.
     * 
     */
    public void tiimienLuonti(String omaNimi, String vihollisNimi) {
        Joukkue x = new Joukkue(omaNimi);
        Joukkue y = new Joukkue(vihollisNimi);

        x.luoJoukkue();
        y.luoJoukkue();

        this.x = x;
        this.y = y;
    }

    public static void main(String[] args) {
        Peli p = new Peli();
        GUI gui = new GUI(p);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUI.framenLuonti(gui);
            }
        });

    }

}
