package viagogo.events;

import java.util.*;
import java.util.stream.Collectors;

public class Event {

    private final String name;
    private final List<Ticket> tickets; // every event can have 0 or more tickets.
    private final long id; // every event will have a unique id


    public Event(String name, long id, List<Ticket> tickets) {
        this.name = name;
        this.tickets = new ArrayList<>(tickets);
        this.id = id;
    }

    // use an optional here because an event may have no tickets.
    public Optional<Ticket> getCheapestTicket() {
        if (tickets.isEmpty()) {
            return Optional.empty();
        }

        Ticket cheapest = tickets.stream()
                .sorted(Comparator.comparingLong(Ticket::getPrice))
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

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("{name=%s, id=%s, tickets=%s}", name, id, tickets);
    }

    @Override
    public int hashCode() {
        return (int) id;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Event)) {
            return false;
        }
        return id == ((Event) other).id;
    }


}
