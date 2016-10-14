package testinauttajat;

/**
 * Luokka auttaa randomeiden metodien testaamisessa luokassa Peli. Luokka on
 * kopio alkuperäisestä luokasta mutta randomit metodit on korvattu itse
 * määritellyillä ja tiedostojen testaus on poistettu.
 *
 */
public class PeliTesteri {

    public JoukkueTesteri x, y;
    public String s = "";

    //testaukseen:
    PelaajaTesteri pt = new PelaajaTesteri("Esimerkkipelaaja", 3);

    //tilastoja
    int omatMaalit = 0, vihollisenMaalit = 0;
    int omatLaukaukset = 0, vihollisenLaukaukset = 0;
    int omatSyotot = 0, vihollisenSyotot = 0;
    int omatTaklaukset = 0, vihollisenTaklaukset = 0;
    int omatKeltaiset = 0, vihollisenKeltaiset = 0;
    int omatPunaiset = 0, vihollisenPunaiset = 0;

    public PeliTesteri() {
    }

    public PeliTesteri(JoukkueTesteri x, JoukkueTesteri y) {
        this.x = x;
        this.y = y;
    }

    /*
    Nämä testattu erikseen.
    
    public void matsinGenerointi(Joukkue x, Joukkue y) {
        
    }
    public String matsinLuku() throws FileNotFoundException {
        
    }
     */
    public void tilastojenNollaus() {
        omatMaalit = 0;
        vihollisenMaalit = 0;
        omatLaukaukset = 0;
        vihollisenLaukaukset = 0;
        omatTaklaukset = 0;
        vihollisenTaklaukset = 0;
        omatKeltaiset = 0;
        vihollisenKeltaiset = 0;
        omatPunaiset = 0;
        vihollisenPunaiset = 0;
        omatSyotot = 0;
        vihollisenSyotot = 0;
    }

    public String tilastot() {
        String s = "";
        s = "Joukkueesi maalit: " + omatMaalit + "\n"
                + "Vastustajan maalit: " + vihollisenMaalit + "\n\n"
                + "Joukkueesi laukaukset: " + omatLaukaukset + "\n"
                + "Vastustajan laukaukset: " + vihollisenLaukaukset + "\n\n"
                + "Joukkueesi taklaukset: " + omatTaklaukset + "\n"
                + "Vastustajan taklaukset: " + vihollisenTaklaukset + "\n\n"
                + "Joukkueesi keltaiset: " + omatKeltaiset + "\n"
                + "Vastustajan keltaiset: " + vihollisenKeltaiset + "\n\n"
                + "Joukkueesi punaiset: " + omatPunaiset + "\n"
                + "Vastustajan punaiset: " + vihollisenPunaiset + "\n\n"
                + "Joukkueesi syötöt: " + omatSyotot + "\n"
                + "Vastustajan syötöt: " + vihollisenSyotot + "\n\n";

        return s;
    }

    public void tiimienLuonti(String omaNimi, String vihollisNimi) {
        JoukkueTesteri x = new JoukkueTesteri(omaNimi);
        JoukkueTesteri y = new JoukkueTesteri(vihollisNimi);

        x.luoJoukkue();
        y.luoJoukkue();

        this.x = x;
        this.y = y;
    }

    public void eventGenerointi(JoukkueTesteri x, JoukkueTesteri y, double event) throws Exception {
        syotto();

        if (event == 0.96) { //maali
            s = maali(true);
        }

        if (event == 0.94) { //laukaus tolppaan
            s = tolppa(0.49);
        }

        if (event == 0.14) { //laukaus
            s = laukaus(0.49);
        }

        //muutettu testauksen takia
        if (event == 0.20) { //taklaus
            s = taklaus(0.03, 0.49, 0.91);
        }

    }

    public String maali(boolean maali) throws Exception {
        if (maali) {
            omatLaukaukset++;
            omatMaalit++;
            return "\n" + pt + ", teki maalin joukkueelle " + x.getNimi() + "!";
        } else {
            vihollisenLaukaukset++;
            vihollisenMaalit++;
            return "\n" + pt + ", teki maalin joukkueelle " + y.getNimi() + "!";
        }
    }

    public String tolppa(double tolppaMahdollisuus) {
        if (tolppaMahdollisuus < 0.50) {
            omatLaukaukset++;
            return "\n" + pt + ", " + "pelaaja joukkueelle " + x.getNimi() + ", " + "laukoi!" + " Se osui tolppaan!";
        } else { //y laukoo tolppaan
            vihollisenLaukaukset++;
            return "\n" + pt + ", " + "pelaaja joukkueelle " + y.getNimi() + ", " + "laukoi!" + " Se osui tolppaan!";
        }
    }

    public void syotto() {
        omatSyotot = omatSyotot + 6;
        vihollisenSyotot = vihollisenSyotot + 6;
    }

    public String laukaus(double laukausMahdollisuus) {
        if (laukausMahdollisuus < 0.50) {
            omatLaukaukset++;
            return "\n" + pt + ", " + "pelaaja joukkueelle " + x.getNimi() + ", " + "laukoi!" + " Ohi meni!";
        } else {
            vihollisenLaukaukset++;
            return "\n" + pt + ", " + "pelaaja joukkueelle " + y.getNimi() + ", " + "laukoi!" + " Ohi meni!";
        }
    }

    public String taklaus(double r, double taklausMahdollisuus, double keltPun) {
        String s = "";
        PelaajaTesteri taklaaja = null;

        if (r < 0.04) {
            if (taklausMahdollisuus < 0.50) {
                omatTaklaukset++;
                omatKeltaiset++;
                taklaaja = pt;
                s = s + "\n" + taklaaja + ", taklasi pahasti ja sai" + keltainenVaiPunainen(taklaaja, keltPun) + " kortin!";
                if (taklaaja.getKortit() >= 2) {
                    omatPunaiset++;
                    s = s + " Hän on ulkona pelistä!";
                    taklaaja.pelaajaUlosKentalta();
                    x.joukkueenVoimaLasku();
                }
            }
            if (taklausMahdollisuus > 0.50) {
                vihollisenTaklaukset++;
                vihollisenKeltaiset++;
                taklaaja = pt;
                s = s + "\n" + taklaaja + ", taklasi pahasti ja sai" + keltainenVaiPunainen(taklaaja, keltPun) + " kortin!";
                if (taklaaja.getKortit() >= 2) {
                    vihollisenPunaiset++;
                    s = s + " Hän on ulkona pelistä!";
                    taklaaja.pelaajaUlosKentalta();
                    y.joukkueenVoimaLasku();
                }
            }
        }

        return s;
    }

    public String keltainenVaiPunainen(PelaajaTesteri p, double d) {
        String s = "";

        if (d == 0.91) {
            s = " keltaisen";
            p.setKortit(1);
        } else {
            s = " punaisen";
            p.setKortit(2);
        }

        return s;
    }

}
