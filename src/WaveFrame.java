import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class WaveFrame extends JFrame implements MouseListener {

    private WaveGrid grid;

    public WaveFrame(WaveGrid grid) {
        this.grid = grid;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
