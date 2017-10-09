package dev.viagogo.worlds.distancemetrics;

import dev.viagogo.worlds.Point;

public interface DistanceMetric {
    int distanceBetween(Point a, Point b);
}
