package com.coopnc.effectivejava3rd.item10.exam02;

public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Point))
            return false;

        Point p = (Point) o;
        return this.x == p.x && this.y == p.y;
    }


    // 리스코프 치환원칙 위배
//    private static final Set<Point> unitCircle = Set.of(new Point(0, -1),
//            new Point(0, 1),
//            new Point(-1, 0),
//            new Point(1, 0)
//    );
//
//    public static boolean onUnitCircle(Point p) {
//        return unitCircle.contains(p);
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if(o == null || o.getClass() != this.getClass()) {
//            return false;
//        }
//
//        Point p = (Point) o;
//        return this.x == p.x && this.y == p.y;
//    }
}

class PointTest {

    public static void main( String[] args ) {

        // 리스코프 치환원칙 위배
//        ColorPoint cp = new ColorPoint(1, 0, Color.BLUE);
//        System.out.println(Point.onUnitCircle(cp)); //false
    }
}
