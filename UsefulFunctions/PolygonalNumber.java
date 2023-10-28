package UsefulFunctions;

public class PolygonalNumber {
    public long polygonalNumber(int sides, int n) {
        return ((long) (sides - 2)*n*n - (long) (sides - 4)*n)/2;
    }
}
