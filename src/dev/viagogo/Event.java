package dev.viagogo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Event {

    private static int numEvents = 0; // small enough number of events that we can just keep track of them with an int.

    private final String name;
    private final List<Ticket> tickets; // every event can have 0 or more tickets.
    private final int id; // every event will have a unique id

    public Event(String name) {
        this.name = name;
        this.tickets = new ArrayList<>(); // start off with no tickets to the event.
        this.id = numEvents++; // guarantee a unique ticket id.
    }

    public List<Ticket> getTickets() {
        return Collections.unmodifiableList(tickets);
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("{name=%s, id=%s, tickets=%s}", name, id, tickets);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Event)) {
            return false;
        }
        return id == ((Event) other).id;
    }


}
