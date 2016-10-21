package kayttoliittyma;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import logiikka.Peli;

/**
 * Käyttöliittymä ohjelmalle.
 */
public class GUI extends JPanel implements ActionListener {

    protected JButton luonti, kylla, generointi, tilastot;
    protected JDialog tilastoja;
    protected JTextArea teksti;
    private final static String N = "\n";
    private Peli peli;

    public GUI(Peli p) {
        super(new GridBagLayout());
        this.setPreferredSize(new Dimension(800, 600));

        luonti = new JButton("Luo joukkue");
        luonti.addActionListener(this);

        kylla = new JButton("Kyllä");
        kylla.addActionListener(this);

        generointi = new JButton("Matsin generointi");
        generointi.addActionListener(this);

        tilastot = new JButton("Lopeta peli");
        tilastot.addActionListener(this);
        
        teksti = new JTextArea(5, 20);
        teksti.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(teksti);
        teksti.setText("Tervetuloa valitsemaan satunnaisesti generoitua "
                + "joukkuetta ja katsomaan miten se pärjää vihollistiimiä vastaan!"
                + " \nLuo joukkue painamalla luonti-näppäintä!");

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);

        add(luonti);

        this.peli = p;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == luonti) {
            luonti();
        }

        if (evt.getSource() == kylla) {
            kylla();
        }

        if (evt.getSource() == generointi) {
            matsinGenerointi();
        }
        
        if (evt.getSource() == tilastot) {
            tilastot();
        }

    }

    /**
     * Joukkueen luonti.
     */
    public void luonti() {
        teksti.setText("");

        peli.tiimienLuonti();
        teksti.append("Tässä satunnaisesti luotu joukkueesi. Voit halutessasi luoda uuden.\n\nJoukkueen nimi: " + peli.omaJoukkue.getNimi() + "\n\nJoukkueen kokonaisvoima: "
                + peli.omaJoukkue.getJoukkueenVoima() + "\n\n" + peli.omaJoukkue.printPelaajat() + "Oletko tyytyväinen joukkueeseesi?");
        teksti.setCaretPosition(0);

        luonti.setText("Ei, luo uusi");

        remove(generointi);
        remove(luonti);
        add(luonti);
        add(kylla);
        revalidate();
        repaint();
    }

    /**
     * Peliin eteneminen.
     */
    public void kylla() {
        teksti.setText("");

        teksti.append("Olet valinnut joukkueesi! Joukkueesi kokonaisvoima on " + peli.omaJoukkue.getJoukkueenVoima() + " verrattuna vastustajan "
                + peli.vihollisJoukkue.getJoukkueenVoima() + "!\n\nTässä vielä joukkueesi:\n\n" + peli.omaJoukkue.printPelaajat() + "Vastustajan joukkue:\n\n" + peli.vihollisJoukkue.printPelaajat()
                + "Paina matsin generointi-nappia ja peli voi alkaa!\n\n");

        teksti.setCaretPosition(0);

        remove(kylla);
        remove(luonti);
        add(generointi);
        revalidate();
        repaint();
    }

    /**
     * Matsi.txt kirjoitus + luku.
     */
    public void matsinGenerointi() {
        teksti.setText("");
        peli.matsinGenerointi();
        try {
            teksti.append(peli.omaJoukkue.getNimi() + "-" + peli.vihollisJoukkue.getNimi() 
                    + "\n" + peli.matsinLuku() + "\nVoit joko generoida "
                    + "uuden matsin tai lopettaa tällä joukkueella pelaamisen.\n");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        peli.tilastojenTallennus(peli.omaJoukkue);
        peli.tilastojenNollaus();

        teksti.setCaretPosition(0);
        
        remove(luonti);
        remove(generointi);
        add(tilastot);
        add(generointi);
    
        revalidate();
        repaint();
    }

    /**
     * Uuden joukkueen luonti, vanhan joukkueen tilastot.
     */
    public void tilastot() {
        teksti.setText("");
        
        teksti.append("Tässä joukkueesi kokonaistilastoja generoimiesi matsien "
                + "jälkeen:\n\n" + peli.omaJoukkue.kokonaistilastot()
                + "\n\nNyt voit luoda uuden satunnaisen joukkueen painamalla "
                + "Luo uusi joukkue-painiketta!\nJos muutit mielesi ja haluat pelata"
                + " vielä samalla joukkueella, paina Matsin gene"
                + "rointi-painiketta ja aloita matsien generointi uudestaan!");
        
        teksti.setCaretPosition(0);

        remove(generointi);
        remove(tilastot);
        add(luonti);
        add(generointi);
        luonti.setText("Luo uusi joukkue");
        revalidate();
        repaint();
    }

    public static void framenLuonti(GUI gui) {
        JFrame kehys = new JFrame("Jalkapallosimulaattori");
        kehys.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        kehys.add(gui);

        kehys.pack();
        kehys.setVisible(true);
    }

}
