import javax.swing.*;

import client.MainClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.Socket;

public class IsiIP extends JFrame {
    private JPanel panel;
    private JTextField textField;
    private JLabel labelNama;

    public IsiIP() {
        setTitle("Circle Battleship");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Membuat panel utama dengan layout null
        panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(1203, 500));

        // Menambahkan gambar latar belakang
        try {
            File imageFile = new File("lib\\img\\Ip.png");
            BufferedImage backgroundImage = ImageIO.read(imageFile);
            JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
            backgroundLabel.setBounds(0, 0, backgroundImage.getWidth(), backgroundImage.getHeight());

            // Menambahkan backgroundLabel ke panel
            panel.add(backgroundLabel);

            // Label Nama
            labelNama = new JLabel("IP Server:");
            labelNama.setBounds(438, 284, 203, 48);
            Font font1 = new Font("Poppins", Font.BOLD, 32);
            labelNama.setFont(font1);
            labelNama.setForeground(Color.BLACK);

            // Menambahkan labelNama ke panel dan mengatur lapisan tampilan
            panel.add(labelNama);
            panel.setComponentZOrder(labelNama, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Membuat input field
        textField = new JTextField();
        textField.setBounds(438, 332, 327, 82);
        Font font = new Font("Poppins", Font.BOLD, 32);
        textField.setFont(font);
        textField.setForeground(Color.BLACK);
        textField.setBorder(null);

        // Menambahkan textField ke panel
        panel.add(textField);

        // Membuat tombol "Connect"
        JButton connectButton = new JButton("Connect");
        connectButton.setBounds(438, 440, 150, 50);
        connectButton.setFont(new Font("Poppins", Font.PLAIN, 20));

        // Menambahkan tombol "Connect" ke panel
        
        panel.add(connectButton);
        panel.setComponentZOrder(connectButton, 0);

        // Menambahkan aksi pada tombol "Connect"
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ipAddress = textField.getText();
                if (!ipAddress.isEmpty()) { // Memeriksa apakah textfield tidak kosong
                // Menggunakan alamat IP untuk koneksi ke server
                connectToServer(ipAddress);
                } else {
                    JOptionPane.showMessageDialog(panel, "Ip Address Tidak Boleh Kosong", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void connectToServer(String ipAddress) {
        try {
            Socket socket = new Socket(ipAddress, 1234);
            System.out.println("Connected to server at IP: " + ipAddress);

            // Jika berhasil terhubung, arahkan ke kelas MainClient
            SwingUtilities.invokeLater(MainClient::createAndShowGUI);
            dispose();

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Ip Server Tidak Ditemukan", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(IsiIP::new);
    }
}
