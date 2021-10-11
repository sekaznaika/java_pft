public class Distance {
    public static void main(String[] args) {
        Point p1 = new Point(5.0, 7.0);
        Point p2 = new Point(8.0, 9.0);

        System.out.println(distance(p1, p2));

        System.out.println(p1.distance(p1,p2));

    }

    public static double distance(Point p1, Point p2) {

        return Math.sqrt(((p2.p1 - p1.p1) * (p2.p1 - p1.p1)) + ((p2.p2 - p1.p2) * (p2.p2 - p1.p2)));
    }


}