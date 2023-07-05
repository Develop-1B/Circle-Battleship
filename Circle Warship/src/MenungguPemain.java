import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.ServerSocket;
import java.net.Socket;
import server.Main;

public class MenungguPemain extends JFrame {
    private JPanel panel;

    public MenungguPemain() {
        setTitle("Circle Battleship");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Membuat panel utama
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(1203, 500));

        // Memuat gambar latar belakang
        try {
            File imageFile = new File("lib/img/Frame 5.png");
            BufferedImage backgroundImage = ImageIO.read(imageFile);
            JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
            backgroundLabel.setBounds(0, 0, backgroundImage.getWidth(), backgroundImage.getHeight());
            panel.add(backgroundLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Membuat label
        JLabel label1 = createLabel();
        panel.add(label1);
        panel.setComponentZOrder(label1, 0);

        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        // Menjalankan kelas Server dalam thread terpisah
        SwingWorker<Void, Void> serverThread = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                ServerSocket serverSocket = new ServerSocket(1234);
                System.out.println("Server started on port 1234");

                // Menerima koneksi dari client
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Lanjutkan logika server Anda di sini
                SwingUtilities.invokeLater(Main::createAndShowGUI);
                dispose();

                return null;
            }
        };
        serverThread.execute();
    }

    private JLabel createLabel() {
        JLabel label1 = new JLabel("Menunggu Pemain Lain");
        label1.setBounds(408, 351, 387, 48);
        Font font = new Font("Poppins", Font.BOLD, 32);
        label1.setFont(font);
        label1.setForeground(Color.BLACK); // Mengatur warna font menjadi hitam
        return label1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenungguPemain::new);
    }
}
