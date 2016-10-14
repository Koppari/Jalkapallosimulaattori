package logiikka;

import java.io.*;

/**
 * Luokka huolehtii pelitiedoston tallentamisesta ja lukemisesta
 *
 */
public class TiedostonHoitaja {

    private Peli p;

    public TiedostonHoitaja(Peli peli) {
        this.p = peli;
    }

    /**
     * Generoi matsin eventit 90 minuuttiin, kirjoittaa sen tiedostoon. Lopuksi
     * printtaa tilastoja.
     *
     */
    public void tiedostonGenerointi() {
        try {
            PrintWriter tiedostokirjoitin = new PrintWriter("matsi.txt");
            for (int i = 0; i <= 90; i++) {
                p.s = p.omatMaalit + "-" + p.vihollisenMaalit + ", " + i + " min: ";
                p.eventGenerointi(p.x, p.y);
                tiedostokirjoitin.println(p.s);
            }

            tiedostokirjoitin.println("Peli päättyi " + p.omatMaalit + "-" + p.vihollisenMaalit + "!");
            tiedostokirjoitin.println("\nTilastoja:\n");
            tiedostokirjoitin.println(p.tilastot());
            tiedostokirjoitin.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Lukee matsin tapahtumat tiedostosta ja kirjoittaa ne stringiin.
     *
     *
     * @return Matsin tapahtumat.
     */
    public String matsinLuku() throws FileNotFoundException {
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

}
