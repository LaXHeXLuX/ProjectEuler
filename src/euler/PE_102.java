package euler;

import utils.Parser;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PE_102 {
    private static class Triangle {
        Point a;
        Point b;
        Point c;

        private static class Point {
            int x;
            int y;

            private Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
            private int cross(Point p) {
                return x*p.y - p.x*y;
            }
            @Override
            public String toString() {
                return "[" + x + ", " + y + "]";
            }
        }
        @Override
        public String toString() {
            return "{" + a + ", " + b + ", " + c + "}";
        }
    }

    public static void main(String[] args) {
        System.out.println(PE());
    }

    public static long PE() {
        String file = "src/euler/inputs/PE_102_triangles.txt";
        List<Triangle> triangles = parse(file);
        int count = 0;
        for (Triangle triangle : triangles) {
            if (containsOrigin(triangle))  {
                count++;
            }
        }
        return count;
    }

    private static boolean containsOrigin(Triangle t) {
        boolean crossAB = t.a.cross(t.b) > 0;
        boolean crossBC = t.b.cross(t.c) > 0;
        boolean crossCA = t.c.cross(t.a) > 0;
        return crossAB == crossBC && crossBC == crossCA;
    }

    private static List<Triangle> parse(String file) {
        int[][] ints = Parser.parseManyInts(file, ",");
        List<Triangle> triangles = new ArrayList<>();

        for (int[] row : ints) {
            Triangle t = new Triangle();
            t.a = new Triangle.Point(row[0], row[1]);
            t.b = new Triangle.Point(row[2], row[3]);
            t.c = new Triangle.Point(row[4], row[5]);
            triangles.add(t);
        }

        return triangles;
    }
}
