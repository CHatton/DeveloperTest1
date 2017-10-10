package viagogo.worlds.distancemetrics;

import viagogo.worlds.Point;

public interface DistanceMetric {
    int distanceBetween(Point a, Point b);
}
