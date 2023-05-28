package quadtree;

import java.util.ArrayList;

public class Quad extends Rectangle {
    private Quad northWest;
    private Quad northEast;
    private Quad southWest;
    private Quad southEast;

    private Point point;

    public Quad(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public boolean isEmpty() {
        return this.point == null;
    }

    public boolean isSubdivided() {
        return this.northWest != null;
    }

    private void subdivide() {
        double x = this.x;
        double y = this.y;
        double width = this.width / 2;
        double height = this.height / 2;

        this.northWest = new Quad(x, y, width, height);
        this.northEast = new Quad(x + width, y, width, height);
        this.southWest = new Quad(x, y + height, width, height);
        this.southEast = new Quad(x + width, y + height, width, height);
    }

    public boolean insert(Point point) {
        if (!this.contains(point))
            return false;

        if (isEmpty()) {
            this.point = point;
            return true;
        }

        if (!isSubdivided())
            this.subdivide();

        if (this.northWest.insert(point))
            return true;
        if (this.northEast.insert(point))
            return true;
        if (this.southWest.insert(point))
            return true;
        if (this.southEast.insert(point))
            return true;

        return false;
    }

    public ArrayList<Point> getPoints(Rectangle range) {
        ArrayList<Point> points = new ArrayList<>();

        if (!this.intersects(range))
            return points;

        if (!isEmpty() && range.contains(this.point))
            points.add(this.point);

        if (!isSubdivided())
            return points;

        points.addAll(this.northWest.getPoints(range));
        points.addAll(this.northEast.getPoints(range));
        points.addAll(this.southWest.getPoints(range));
        points.addAll(this.southEast.getPoints(range));

        return points;
    }

    public int getNumberOfPoints() {
        int numberOfPoints = 0;

        if (!isEmpty())
            numberOfPoints++;

        if (!isSubdivided())
            return numberOfPoints;

        numberOfPoints += this.northWest.getNumberOfPoints();
        numberOfPoints += this.northEast.getNumberOfPoints();
        numberOfPoints += this.southWest.getNumberOfPoints();
        numberOfPoints += this.southEast.getNumberOfPoints();

        return numberOfPoints;
    }

    public int getNumberOfQuads() {
        int numberOfQuads = 1;

        if (!isSubdivided())
            return numberOfQuads;

        numberOfQuads += this.northWest.getNumberOfQuads();
        numberOfQuads += this.northEast.getNumberOfQuads();
        numberOfQuads += this.southWest.getNumberOfQuads();
        numberOfQuads += this.southEast.getNumberOfQuads();

        return numberOfQuads;
    }

    public int getDepth() {
        if (!isSubdivided())
            return 1;

        int depth = 1;

        depth = Math.max(depth, this.northWest.getDepth());
        depth = Math.max(depth, this.northEast.getDepth());
        depth = Math.max(depth, this.southWest.getDepth());
        depth = Math.max(depth, this.southEast.getDepth());

        return depth + 1;
    }

    @Override
    public String toString() {
        String subQuads = isSubdivided()
                ? "NW " + this.northWest + ", NE " + this.northEast + ", SW " + this.southWest + ", SE "
                        + this.southEast
                : "";
        String point = isEmpty() ? "" : "O" + (isSubdivided() ? ", " : "");
        return "[" + point + subQuads + "]";
    }
}
