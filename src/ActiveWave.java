import java.awt.geom.Point2D;

public class ActiveWave {

    private double x, y; // point of origin
    private double r; // radius
    private double period;

    public ActiveWave(double x, double y, double period) {
        this.x = x;
        this.y = y;
        this.r = 1;
        this.period = period;
    }

    public Point2D calculateOffset(double i, double j) {
        double d = distance(x, y, i, j);
        double offset = d - r;

        if (Math.abs(offset) > period / 2) return new Point2D.Double(i, j);

        double angle = Math.atan2((j - y), (i - x));
        double rx = x + r * Math.cos(angle);
        double ry = y + r * Math.sin(angle);

        double ratio = offset / (period / 2.0);
        double angleRatio = (Math.PI / 2) * ratio - Math.PI / 2;

        double calculatedOffset = (period / 2) * Math.cos(angleRatio);

        double xoffset = calculatedOffset * Math.cos(angle);
        double yoffset = calculatedOffset * Math.sin(angle);

        return new Point2D.Double(rx + xoffset, ry + yoffset);
    }

    public void updateRadius() {
        this.r += 5;
    }

    private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public double getRadius() {
        return this.r;
    }
}
