package kayttoliittyma;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import logiikka.Peli;

/**
 * Käyttöliittymä ohjelmalle.
 */
public class GUI extends JPanel implements ActionListener {

    protected JButton aloitus;
    protected JTextArea eventit;
    private final static String N = "\n";
    private Peli peli;

    public GUI(Peli p) {
        super(new GridBagLayout());
        this.setPreferredSize(new Dimension(800, 600));

        aloitus = new JButton("Matsin generointi");
        aloitus.addActionListener(this);

        eventit = new JTextArea(5, 20);
        eventit.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(eventit);

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        add(aloitus, c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);

        this.peli = p;
    }

    GUI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void actionPerformed(ActionEvent evt) {
        Object src = evt.getSource();

        peli.tiimienLuonti("A", "B");
        peli.matsinGenerointi(peli.x, peli.y);
        eventit.append(peli.matsinLuku() + "\n");

        eventit.setCaretPosition(eventit.getDocument().getLength());
    }

    public static void framenLuonti(GUI gui) {
        JFrame kehys = new JFrame("Jalkapallosimulaattori");
        kehys.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        kehys.add(gui);

        kehys.pack();
        kehys.setVisible(true);
    }

}
