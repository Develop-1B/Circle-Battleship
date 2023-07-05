package client;
import javax.swing.*;
import java.awt.*;

public class EnemyMapClient extends JPanel {
    private static final int PANEL_WIDTH = 480;
    private static final int PANEL_HEIGHT = 320;
    private static final int CELL_SIZE = 40;

    private int EnemyMap[][] = {
        //     1  2  3  4  5  6  7  8  9  10 11 12
        /*1*/ {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        /*2*/ {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        /*3*/ {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        /*4*/ {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        /*5*/ {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        /*6*/ {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        /*7*/ {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
        /*8*/ {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };

    private int Row = 0;
    private int Col = 0;
    private int numObjects = 0;

    public EnemyMapClient() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setLayout(null);

        // Mencari koordinat awal objek biru (nilai 0) di peta
        for (int Row = 0; Row < EnemyMap.length; Row++) {
            for (int Col = 0; Col < EnemyMap[Row].length; Col++) {
                if (EnemyMap[Row][Col] == 0) {
                    this.Row = Row;
                    this.Col = Col;
                    return; // Keluar dari loop jika sudah ditemukan posisi awal objek biru
                }
            }
        }
    }

    public void moveEnemyUp() {
        if (Row > 0 && EnemyMap[Row - 1][Col] != 7) {
            Row--;
            repaint();
        }
    }

    public void moveEnemyLeft() {
        if (Col > 0 && EnemyMap[Row][Col - 1] != 7) {
            Col--;
            repaint();
        }
    }

    public void moveEnemyDown() {
        if (Row < EnemyMap.length - 1 && EnemyMap[Row + 1][Col] != 7) {
            Row++;
            repaint();
        }
    }

    public void moveEnemyRight() {
        if (Col < EnemyMap[Row].length - 1 && EnemyMap[Row][Col + 1] != 7) {
            Col++;
            repaint();
        }
    }

    public int getNumObjects() {
        return numObjects;
    }

    public int getRow() {
        return Row;
    }

    public int getCol() {
        return Col;
    }

    public void shootCell(int row, int col) {
    if (row >= 0 && row < EnemyMap.length && col >= 0 && col < EnemyMap[0].length) {
        if (EnemyMap[row][col] > 0) {
            EnemyMap[row][col] = 3; // Menandai kapal terkena dengan nilai 3
        } else {
            EnemyMap[row][col] = -1; // Menandai tembakan yang tidak mengenai kapal dengan nilai -1
        }
        repaint();
    }

    boolean allDestroyed = true;

    // Periksa apakah masih ada angka 1 di seluruh cell EnemyMap
    for (int r = 0; r < EnemyMap.length; r++) {
        for (int c = 0; c < EnemyMap[r].length; c++) {
            if (EnemyMap[r][c] == 1) {
                allDestroyed = false; // Ada angka 1 yang belum dihancurkan
                break;
            }
        }
    }

    // Jika tidak ada angka 1, tampilkan pesan
    if (allDestroyed) {
        JOptionPane.showMessageDialog(this, "Kita Menang",
                "LAPOR KOMANDAN", JOptionPane.WARNING_MESSAGE);
    }
}


    @Override
    protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    for (int Row = 0; Row < EnemyMap.length; Row++) {
        for (int Col = 0; Col < EnemyMap[Row].length; Col++) {
            int cellValue = EnemyMap[Row][Col];
            int x = Col * CELL_SIZE;
            int y = Row * CELL_SIZE;

            if (cellValue == 0) {
                g.setColor(Color.WHITE);
                g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
            } else if (cellValue == 1) {
                g.setColor(Color.WHITE);
                g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
            } else if (cellValue == -1) {
                g.setColor(Color.YELLOW);
                g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
            } else if (cellValue == 3) {
                g.setColor(Color.WHITE);
                g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, CELL_SIZE, CELL_SIZE);

                ImageIcon redImageIcon = new ImageIcon("lib/img/Starburst-Explosion-PNG-Clipart-Background.png");
                Image redImage = redImageIcon.getImage();
                g.drawImage(redImage, x, y, CELL_SIZE, CELL_SIZE, null);
            } else if (cellValue == 7) {
                continue; // Skip drawing Columns with value 7
            }
        }
    }
        int EnemyX = Col * CELL_SIZE;
        int EnemyY = Row * CELL_SIZE;
        ImageIcon blueImageIcon = new ImageIcon("lib/img/kisspng-computer-icons-target-corporation-symbol-illustrat-target-icon-5ab070b9baa139.5116457715215126337645.png");
        Image blueImage = blueImageIcon.getImage();
        g.drawImage(blueImage, EnemyX, EnemyY, CELL_SIZE, CELL_SIZE, null);
        // Membuat gambar objek biru transparan
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.0f)); // Opasitas 100%
        g2d.drawImage(blueImage, EnemyX, EnemyY, CELL_SIZE, CELL_SIZE, null);
        g2d.dispose();

        repaint();
        
}

    public void resetEnemyMap() {
    for (int i = 0; i < EnemyMap.length; i++) {
        for (int j = 0; j < EnemyMap[i].length; j++) {
            EnemyMap[i][j] = 0;
        }
    }
    Row = 0;
    Col = 0;
    numObjects = 0;
    repaint();
}


}