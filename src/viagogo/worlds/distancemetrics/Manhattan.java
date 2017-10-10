package viagogo.worlds.distancemetrics;

import viagogo.worlds.Point;

public class Manhattan implements DistanceMetric {

    @Override
    public int distanceBetween(Point a, Point b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
