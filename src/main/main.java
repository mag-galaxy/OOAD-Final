package main;

import javax.swing.SwingUtilities;
import gui.MainFrame;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
