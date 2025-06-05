package main;

import vista.LoginVista;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginVista().setVisible(true);
        });
    }
}
