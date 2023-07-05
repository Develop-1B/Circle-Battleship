import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

public class MenuUtama extends JFrame {
    private JPanel panel;
    private MenungguPemain menungguPemain;

    public MenuUtama() {
        setTitle("Circle Battleship");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Membuat panel utama dengan layout null
        panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(1203, 500));

        // Memuat gambar latar belakang
        try {
            File imageFile = new File("lib\\img\\1-12 1.png");
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(imageFile));
            JLabel backgroundLabel = new JLabel(imageIcon);
            backgroundLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());

            // Menambahkan gambar latar belakang ke panel
            panel.add(backgroundLabel);

            // Membuat tombol dari gambar
            JButton buttonServer = createImageButton("lib\\img\\tombol server.png");
            JButton buttonClient = createImageButton("lib\\img\\tombol client.png");

            // Mengatur posisi dan ukuran tombol
            buttonServer.setBounds(259, 332, buttonServer.getPreferredSize().width, buttonServer.getPreferredSize().height);
            buttonClient.setBounds(631, 332, buttonClient.getPreferredSize().width, buttonClient.getPreferredSize().height);

            // Menambahkan tombol ke panel
            panel.add(buttonServer);
            panel.add(buttonClient);

            // Mengatur urutan tampilan tombol di depan gambar
            panel.setComponentZOrder(buttonServer, 0);
            panel.setComponentZOrder(buttonClient, 0);

            // Menambahkan ActionListener pada tombol server
            buttonServer.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    SwingUtilities.invokeLater(MenungguPemain::new);
                    dispose();
                }
            });

            // Menambahkan ActionListener pada tombol client
            buttonClient.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Membuat instance baru dari kelas IsiIP
                    SwingUtilities.invokeLater(IsiIP::new);
                    dispose();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        // Inisialisasi instance MenungguPemain
        menungguPemain = new MenungguPemain();
        menungguPemain.setVisible(false);
    }

    private JButton createImageButton(String imagePath) {
        JButton button = new JButton();
        try {
            File imageFile = new File(imagePath);
            Image image = ImageIO.read(imageFile);
            ImageIcon imageIcon = new ImageIcon(image);
            button.setIcon(imageIcon);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setOpaque(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuUtama::new);
    }
}