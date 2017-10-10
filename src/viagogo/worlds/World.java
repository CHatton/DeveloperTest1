package viagogo.worlds;

import viagogo.events.Event;
import viagogo.worlds.distancemetrics.DistanceMetric;

import java.util.*;
import java.util.stream.Collectors;

public class World {

    private final DistanceMetric distanceMetric;
    private final Map<Point, Event> events;

    public World(final Map<Point, Event> events, final DistanceMetric distanceMetric) {
        this.events = Collections.unmodifiableMap(events);
        this.distanceMetric = distanceMetric;
    }

    public int distanceToEvent(final Point start, final Event event) {
        Optional<Point> eventPoint = getEventPoint(event);
        // negative number to indicate point doesn't exist.
        return eventPoint.isPresent() ? distanceMetric.distanceBetween(start, eventPoint.get()) : -1;
    }

    private Optional<Point> getEventPoint(final Event event) {
        for (Map.Entry<Point, Event> entry : events.entrySet()) {
            if (entry.getValue().equals(event)) {
                return Optional.of(entry.getKey());
            }
        }
        return Optional.empty();
    }


    public List<Event> getClosestEvents(final Point point, final int n) {
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

    public List<Event> getClosestEvents(final Point point) {
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
