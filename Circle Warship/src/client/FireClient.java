package client;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FireClient implements KeyListener {
    private EnemyMapClient enemyMap;
    private MapClient mapPanel;

    public FireClient(MapClient mapPanel, EnemyMapClient enemyMap) {
        this.mapPanel = mapPanel;
        this.enemyMap = enemyMap;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            int bulletRow = enemyMap.getRow();
            int bulletCol = enemyMap.getCol();

            int numObjects = mapPanel.getNumObjects();
            if (numObjects >= 5) {
                enemyMap.shootCell(bulletRow, bulletCol);
            }
            mapPanel.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
