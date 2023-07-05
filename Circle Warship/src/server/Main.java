package server;
import javax.swing.*;
import javax.swing.plaf.IconUIResource;

import java.awt.Color;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    public static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            Icon customWarningIcon = new ImageIcon("lib/img/pngegg.png");
            UIManager.put("OptionPane.warningIcon", new IconUIResource(customWarningIcon));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Circle Battleship");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.decode("#AFF3FF"));
        frame.setLayout(null);

        Map mapPanel = new Map();
        EnemyMap enemyPanel = new EnemyMap();
        mapPanel.setBounds(41, 26, 480, 320);
        enemyPanel.setBounds(641, 26, 480, 320);
        frame.add(mapPanel);
        frame.add(enemyPanel);

        Kontrol kontrol = new Kontrol(mapPanel, enemyPanel);
        frame.addKeyListener(kontrol);

        Fire fire = new Fire(mapPanel, enemyPanel);
        frame.addKeyListener(fire);

        frame.setFocusable(true);
        frame.requestFocus();

        frame.setSize(1200, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }
}
