package logiikka;

import java.io.*;

/**
 * Luokka huolehtii pelitiedoston tallentamisesta ja lukemisesta
 *
 */
public class TiedostonHoitaja {

    private Peli peli;

    public TiedostonHoitaja(Peli peli) {
        this.peli = peli;
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
                peli.s = peli.omatMaalit + "-" + peli.vihollisenMaalit + ", " + i + " min: ";
                peli.eventGenerointi(peli.omaJoukkue, peli.vihollisJoukkue);
                tiedostokirjoitin.println(peli.s);
            }
            
            tiedostokirjoitin.println("Peli päättyi " + peli.omatMaalit + "-" + peli.vihollisenMaalit + "!");
            tiedostokirjoitin.println("\nTilastoja:\n");
            tiedostokirjoitin.println(peli.tilastot());
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
     * @throws java.io.FileNotFoundException
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
