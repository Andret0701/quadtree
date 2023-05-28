package quadtree;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Quad quad = new Quad(0, 0, 100, 100);
        Point point = new Point(10, 10);
        Point point2 = new Point(10, 10.1);
        quad.insert(point);
        quad.insert(point2);
        System.out.println(quad.getDepth());
        System.out.println(quad);

    }
}
