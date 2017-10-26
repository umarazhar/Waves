import java.awt.*;
import java.awt.geom.Point2D;

public class WaveGrid {

    private int width, height;
    private Point2D[][] originalGrid;
    private Point2D[][] activeGrid;

    public WaveGrid(int width, int height) {
        this.width = width;
        this.height = height;

        this.originalGrid = new Point2D[this.width][this.height];

        for (int i = 0; i < originalGrid.length; i++) {
            for (int j = 0; j < originalGrid[i].length; j++) {
                this.originalGrid[i][j] = new Point2D.Double(i, j);
                this.activeGrid[i][j] = new Point2D.Double(i, j);
            }
        }
    }

    public void triggerWave(double x, double y) {
        ActiveWave newWave = new ActiveWave(x, y, 50);
    }
}
