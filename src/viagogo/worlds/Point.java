package viagogo.worlds;

public class Point  {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    @Override
    public int hashCode() {
        return x | y & x ^ y;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Point)) {
            return false;
        }
        Point o = (Point) other;
        return x == o.x && y == o.y;
    }
}
