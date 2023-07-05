package client;
import javax.swing.*;
import javax.swing.plaf.IconUIResource;

import java.awt.Color;

public class MainClient {
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

        MapClient mapPanel = new MapClient();
        EnemyMapClient enemyPanel = new EnemyMapClient();
        mapPanel.setBounds(41, 26, 480, 320);
        enemyPanel.setBounds(641, 26, 480, 320);
        frame.add(mapPanel);
        frame.add(enemyPanel);

        KontrolClient kontrol = new KontrolClient(mapPanel, enemyPanel);
        frame.addKeyListener(kontrol);

        FireClient fire = new FireClient(mapPanel, enemyPanel);
        frame.addKeyListener(fire);

        frame.setFocusable(true);
        frame.requestFocus();

        frame.setSize(1200, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }
}
