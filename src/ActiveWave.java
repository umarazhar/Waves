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

        if (Math.abs(offset) > period) return new Point2D.Double(i, j);

        double angle = Math.atan((j - y) / (i - x));

        double xoffset = offset * Math.cos(angle);
        double yoffset = offset * Math.sin(angle);

        return new Point2D.Double(this.x + xoffset, this.y + yoffset);
    }

    private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
