package kayttoliittyma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {

    private static final int LEVEYS = 800;
    private static final int KORKEUS = 600;

    public void luoGUI() {
        this.setLayout(new BorderLayout());
        this.setTitle("Jalkapallosimulaattori");
        this.setSize(LEVEYS, KORKEUS);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        //vasen panel, tähän eventit
        JPanel vasenPanel = new JPanel(new BorderLayout());
        vasenPanel.setSize(400, 572);
        vasenPanel.setBackground(new Color(100, 100, 100));
        vasenPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        JButton aloitus = new JButton("Aloita");
        vasenPanel.add(aloitus, BorderLayout.SOUTH);
        
        JLabel eventit = new JLabel();
        eventit.setText("Paska");
        vasenPanel.add(eventit, BorderLayout.NORTH);
        
        this.add(vasenPanel);

        //oikea panel, tähän näkyviin kenttä
        JPanel oikeaPanel = new JPanel(new BorderLayout());
        oikeaPanel.setSize(400, 600);
        oikeaPanel.setBackground(new Color(103, 184, 104));
        oikeaPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        this.add(oikeaPanel);

    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.luoGUI();
    }

}
