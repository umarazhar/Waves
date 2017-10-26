import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class WaveGrid {

    private int width, height;
    private Point2D[][] originalGrid;
    private Point2D[][] activeGrid;

    private ArrayList<ActiveWave> activeWaves;

    public WaveGrid(int width, int height) {
        this.width = width;
        this.height = height;

        this.activeWaves = new ArrayList<ActiveWave>();

        int xSpread = 10;
        int ySpread = 10;

        int xPoints = this.width / xSpread;
        int yPoints = this.height / ySpread;

        this.originalGrid = new Point2D[xPoints][yPoints];
        this.activeGrid = new Point2D[xPoints][yPoints];

        for (int i = 0; i < originalGrid.length; i++) {
            for (int j = 0; j < originalGrid[i].length; j++) {
                this.originalGrid[i][j] = new Point2D.Double(i * xSpread, j * ySpread);
                this.activeGrid[i][j] = new Point2D.Double(i * xSpread, j * ySpread);
            }
        }
    }

    public void triggerWave(double x, double y) {
        ActiveWave newWave = new ActiveWave(x, y, 5);

        this.activeWaves.add(newWave);
    }

    public void updatePoints() {
        if (this.activeWaves.size() == 0) return;

        ArrayList<ActiveWave> toRemove = new ArrayList<ActiveWave>();

        Point2D[][] originalCopy = originalGrid.clone();

        for (ActiveWave activeWave : this.activeWaves) {
            for (int i = 0; i < originalCopy.length; i++) {
                for (int j = 0; j < originalCopy[i].length; j++) {
                    activeGrid[i][j] = activeWave.calculateOffset(originalGrid[i][j].getX(), originalGrid[i][j].getY());
                }
            }

            activeWave.updateRadius();

            if (activeWave.getRadius() > this.width) toRemove.add(activeWave);
        }

        this.activeWaves.removeAll(toRemove);
    }

    public Point2D[][] getActiveGrid() {
        return this.activeGrid;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
