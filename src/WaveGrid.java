import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class WaveGrid {

    private final int X_SPREAD = 20;
    private final int Y_SPREAD = 20;

    private int width, height;
    private Point2D[][] originalGrid;
    private Point2D[][] activeGrid;

    private ArrayList<ActiveWave> activeWaves;

    private double outOfBoundsSize;

    public WaveGrid(int width, int height) {
        this.width = width;
        this.height = height;

        this.activeWaves = new ArrayList<ActiveWave>();

        this.originalGrid = createGrid(this.width, this.height);
        this.activeGrid = createGrid(this.width, this.height);

        this.outOfBoundsSize = Math.sqrt(Math.pow(this.width, 2) + Math.pow(this.height, 2));
    }

    private Point2D[][] createGrid(int width, int height) {
        int xPoints = width / X_SPREAD;
        int yPoints = height / Y_SPREAD;

        Point2D[][] newGrid = new Point2D[xPoints][yPoints];

        for (int i = 0; i < newGrid.length; i++) {
            for (int j = 0; j < newGrid[i].length; j++) {
                newGrid[i][j] = new Point2D.Double(i * X_SPREAD, j * Y_SPREAD);
            }
        }

        return newGrid;
    }

    public void triggerWave(double x, double y, double power) {
        ActiveWave newWave = new ActiveWave(x, y, 50, power);

        synchronized (this.activeWaves) {
            this.activeWaves.add(newWave);
        }
    }

    public void updatePoints() {
        if (this.activeWaves.size() == 0) return;

        ArrayList<ActiveWave> toRemove = new ArrayList<ActiveWave>();

        Point2D[][] originalCopy = createGrid(this.width, this.height);

        synchronized (this.activeWaves) {
            for (ActiveWave activeWave : this.activeWaves) {
                for (int i = 0; i < originalCopy.length; i++) {
                    for (int j = 0; j < originalCopy[i].length; j++) {
                        originalCopy[i][j] = activeWave.calculateOffset(originalCopy[i][j].getX(), originalCopy[i][j].getY());
                    }
                }

                activeWave.updateWave();

                if (activeWave.getRadius() > this.outOfBoundsSize) toRemove.add(activeWave);
            }
        }

        this.activeGrid = originalCopy;

        this.activeWaves.removeAll(toRemove);
    }

    public Point2D[][] getActiveGrid() {
        return this.activeGrid;
    }

    public Point2D[][] getOriginalGrid() {
        return this.originalGrid;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
