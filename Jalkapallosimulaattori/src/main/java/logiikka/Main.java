package logiikka;

import kayttoliittyma.GUI;

public class Main {

    public static void main(String[] args) {
        Peli p = new Peli();
        GUI gui = new GUI(p);

        javax.swing.SwingUtilities.invokeLater(() -> {
            GUI.framenLuonti(gui);
        });

    }

}
