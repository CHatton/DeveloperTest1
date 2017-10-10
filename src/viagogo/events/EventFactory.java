package viagogo.events;

import java.util.ArrayList;
import java.util.List;

public class EventFactory {

    private long numEvents = 0;

    public Event newEvent(final String name) {
        return newEvent(name, new ArrayList<>());
    }

    public Event newEvent(final String name, final List<Ticket> ticketList) {
        return new Event(name, numEvents++, new ArrayList<>(ticketList));
    }
}
