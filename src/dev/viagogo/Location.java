package dev.viagogo;

import java.util.Optional;

public class Location {
    private final int x;
    private final int y;
    private Event event;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
        this.event = null;
    }

    public Location(int x, int y, Event event) {
        this(x, y);
        this.event = event;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addEvent(Event event) {
        if (this.event != null) {
            throw new IllegalArgumentException(String.format(
                    "Cannot add event [%s], there is already an event at this location: [%s]", event, this.event));
        }
        this.event = event;
    }

    // chose optional because not every location is guaranteed to have an event.
    public Optional<Event> getEvent() {
        return event == null ? Optional.empty() : Optional.of(event);
    }
}
