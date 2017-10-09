package dev.viagogo.events;

import java.util.*;
import java.util.stream.Collectors;

public class Event {

    private static int numEvents = 0; // small enough number of events that we can just keep track of them with an int.

    private final String name;
    private final List<Ticket> tickets; // every event can have 0 or more tickets.
    private final int id; // every event will have a unique id


    public Event(String name) { // default we have no tickets.
        this(name, new ArrayList<>());
    }

    public Event(String name, List<Ticket> tickets) {
        this.name = name;
        this.tickets = new ArrayList<>(tickets);
        this.id = numEvents++;
    }

    // use an optional here because an event may have no tickets.
    public Optional<Ticket> getCheapestTicket() {
        if (tickets.isEmpty()) {
            return Optional.empty();
        }

        Ticket cheapest = tickets.stream()
                .sorted(Comparator.comparingInt(Ticket::getPrice))
                .collect(Collectors.toList())
                .get(0);

        return Optional.of(cheapest);
    }

    public boolean addTicket(Ticket ticket) {
        return tickets.add(ticket);
    }

    public boolean removeTicket(Ticket ticket) {
        return tickets.remove(ticket);
    }

    public String getName() {
        return name;
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
