package quadtree;

public class Rectangle {
    public double x;
    public double y;
    public double width;
    public double height;

    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean contains(Point point) {
        return point.x >= this.x &&
                point.x <= this.x + this.width &&
                point.y >= this.y &&
                point.y <= this.y + this.height;
    }

    public boolean intersects(Rectangle range) {
        Point topLeft = new Point(this.x, this.y);
        Point topRight = new Point(this.x + this.width, this.y);
        Point bottomLeft = new Point(this.x, this.y + this.height);
        Point bottomRight = new Point(this.x + this.width, this.y + this.height);

        return range.contains(topLeft) ||
                range.contains(topRight) ||
                range.contains(bottomLeft) ||
                range.contains(bottomRight);
    }

}
