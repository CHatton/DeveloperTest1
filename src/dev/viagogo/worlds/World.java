package dev.viagogo.worlds;

import dev.viagogo.events.Event;
import dev.viagogo.worlds.distancemetrics.DistanceMetric;
import dev.viagogo.worlds.distancemetrics.Manhattan;

import java.util.*;
import java.util.stream.Collectors;

public class World {

    private DistanceMetric distanceMetric;
    private final Map<Point, Event> events;

    public World() {
        this(new Manhattan());
    }

    public World(DistanceMetric distanceMetric) {
        this.distanceMetric = distanceMetric;
        events = new HashMap<>();
    }

    public int distanceToEvent(Point start, Event event) {
        Point eventPoint = getEventPoint(event);
        return distanceMetric.distanceBetween(start, eventPoint);
    }

    private Point getEventPoint(Event event) {
        if (!events.containsValue(event)) {
            throw new IllegalArgumentException("Event [%s] is not in the world." + event);
        }
        for (Map.Entry<Point, Event> entry : events.entrySet()) {
            if (entry.getValue().equals(event)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void setDistanceMetric(DistanceMetric distanceMetric) {
        this.distanceMetric = distanceMetric;
    }

    public void addEventAt(Event event, Point point) {
        events.put(point, event);
    }

    public List<Event> getClosestEvents(Point point, int n) {
        // assuming you want to return all up to a number if the world doesn't contain enough points.
        int numToReturn = n < events.size() ? n : events.size();
        // sort the points based on distance from the point provided, not each other.
        return events.keySet().stream().sorted((p1, p2) -> {
            int dist1 = distanceMetric.distanceBetween(p1, point);
            int dist2 = distanceMetric.distanceBetween(p2, point);
            return Integer.compare(dist1, dist2);
        }).limit(numToReturn) // we only want n results (e.g. 5 closest)
                .map(events::get) // get the corresponding event happening at that point
                .collect(Collectors.toList()); // give them back as a list.
    }

    public List<Event> getClosestEvents(Point point) {
        return getClosestEvents(point, 5);
    }
}
