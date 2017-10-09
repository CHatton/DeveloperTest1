package test.viagogo.events;

import dev.viagogo.events.Event;
import dev.viagogo.events.Ticket;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;

public class EventTest {

    @Test
    public void testCheapestTicketIsCheapest() {
        Event e1 = new Event("Event1", Arrays.asList(
                new Ticket(1000),
                new Ticket(5000),
                new Ticket(2000),
                new Ticket(500),
                new Ticket(4000)
        ));

        Optional<Ticket> cheapest = e1.getCheapestTicket();
        assertTrue(cheapest.isPresent());
        assertEquals(500, cheapest.get().getPrice());

        Event e2 = new Event("Event3");
        e2.addTicket(new Ticket(3000));

        Optional<Ticket> cheapestE2 = e2.getCheapestTicket();
        assertTrue(cheapestE2.isPresent());
        assertEquals(3000, cheapestE2.get().getPrice());
    }

    @Test
    public void testEventWithNoTicketsHasNoCheapest() {
        Event e1 = new Event("Event1");
        Optional<Ticket> cheapest = e1.getCheapestTicket();
        assertFalse(cheapest.isPresent());

    }
}