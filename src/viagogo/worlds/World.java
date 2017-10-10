package viagogo.worlds;

import viagogo.events.Event;
import viagogo.worlds.distancemetrics.DistanceMetric;

import java.util.*;
import java.util.stream.Collectors;

public class World {

    private DistanceMetric distanceMetric;
    private final Map<Point, Event> events;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Num events: ")
                .append(events.size())
                .append("\n");

        sb.append("===== Events =====\n");
        events.keySet().forEach((point) ->
                sb.append(events.get(point))
                        .append(" happening at ")
                        .append(point)
                        .append("\n"));

        sb.append("=================\n");

        return sb.toString();
    }
}
