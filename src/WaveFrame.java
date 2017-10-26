import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;

public class WaveFrame extends Canvas implements MouseListener {

    private final double PEG_SIZE = 4;

    private BufferStrategy strategy;
    private JFrame container;

    private WaveGrid grid;

    public WaveFrame(WaveGrid grid) {
        this.grid = grid;

        container = new JFrame("Wave");

        JPanel panel = (JPanel)container.getContentPane();
        panel.setPreferredSize(new Dimension(grid.getWidth(), grid.getHeight()));
        panel.setLayout(null);

        setBounds(0, 0, grid.getWidth(), grid.getHeight());

        panel.add(this);

        setIgnoreRepaint(true);

        container.pack();
        container.setResizable(false);
        container.setVisible(true);

        createBufferStrategy(2);
        strategy = getBufferStrategy();

        this.addMouseListener(this);
    }

    public void updateLogic() {
        this.grid.updatePoints();
    }

    public void updateGraphics() {
        Graphics2D g2 = (Graphics2D)strategy.getDrawGraphics();

        g2.setColor(Color.black);
        g2.fillRect(0, 0, this.grid.getWidth(), this.grid.getHeight());

        Point2D[][] activeGrid = this.grid.getActiveGrid();
        Point2D[][] originalGrid = this.grid.getOriginalGrid();

        Ellipse2D ellipse;
        for (int i = 0; i < activeGrid.length; i++) {
            for (int j = 0; j < activeGrid[i].length; j++) {
//                g2.setColor(Color.blue);
                Point2D point = activeGrid[i][j];

                if (point.getX() != originalGrid[i][j].getX() ||  point.getY() != originalGrid[i][j].getY()) g2.setColor(Color.red);
                else g2.setColor(Color.blue);
                ellipse = new Ellipse2D.Double(point.getX() - PEG_SIZE / 2.0, point.getY() - PEG_SIZE / 2, PEG_SIZE, PEG_SIZE);
                g2.fill(ellipse);
            }
        }

        g2.dispose();
        strategy.show();
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked");
    }

    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse pressed");
        this.grid.triggerWave(e.getX(), e.getY());
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
