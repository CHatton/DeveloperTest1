package dev.viagogo.worlds.heuristics;

import dev.viagogo.worlds.Point;

public interface DistanceMetric {
    int distanceBetween(Point a, Point b);
}
