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

    protected JButton luonti, kylla, generointi;
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

        teksti = new JTextArea(5, 20);
        teksti.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(teksti);
        teksti.setText("Tervetuloa valitsemaan satunnaisesti generoitua joukkuetta ja katsomaan miten se pärjää vihollistiimiä vastaan!"
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

    }

    public void luonti() {
        teksti.setText("");

        peli.tiimienLuonti("A", "B");
        teksti.append("Tässä satunnaisesti luotu joukkueesi. Voit halutessasi luoda uuden.\n\n" + "Joukkueen kokonaisvoima: "
                + peli.x.getJoukkueenVoima() + "\n\n" + peli.x.printPelaajat() + "Oletko tyytyväinen joukkueeseesi?");
        teksti.setCaretPosition(0);

        luonti.setText("Ei, luo uusi");

        remove(luonti);
        add(luonti);
        add(kylla);
        revalidate();
        repaint();
    }

    public void kylla() {
        teksti.setText("");

        teksti.append("Olet valinnut joukkueesi! Joukkueesi kokonaisvoima on " + peli.x.getJoukkueenVoima() + " verrattuna vastustajan "
                + peli.y.getJoukkueenVoima() + "!\n\nTässä vielä joukkueesi:\n\n" + peli.x.printPelaajat() + "Vastustajan joukkue:\n\n" + peli.y.printPelaajat()
                + "Paina matsin generointi-nappia ja peli voi alkaa!\n\n");

        teksti.setCaretPosition(0);

        remove(kylla);
        remove(luonti);
        add(generointi);
        revalidate();
        repaint();
    }

    public void matsinGenerointi() {
        teksti.setText("");
        peli.matsinGenerointi(peli.x, peli.y);
        try {
            teksti.append(peli.x.getNimi() + "-" + peli.y.getNimi() + "\n" + peli.matsinLuku() + "\n");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        peli.tilastojenNollaus();
        
        teksti.setCaretPosition(0);

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
