package viagogo.worlds.builders;

import viagogo.events.Event;
import viagogo.events.EventFactory;
import viagogo.events.Ticket;
import viagogo.worlds.Point;
import viagogo.worlds.World;
import viagogo.worlds.distancemetrics.Manhattan;

import java.util.*;

public class RandomWorldBuilder implements WorldBuilder {

    private final static int UPPER_LIMIT = 10;

    private final int numEvents;
    private final Random rnd;
    private final int maxTickets;
    private final Stack<Point> freePoints;
    private final EventFactory factory;

    public RandomWorldBuilder(final int numEvents, final int maxTickets) {
        this.numEvents = numEvents;
        this.maxTickets = maxTickets;
        rnd = new Random();
        freePoints = new Stack<>();
        this.factory = new EventFactory();
        generateFreePoints();
    }

    private void generateFreePoints() {
        Point point;
        do {
            // give range between -10 and 10
            int xPos = rnd.nextInt(UPPER_LIMIT * 2) - UPPER_LIMIT;
            int yPos = rnd.nextInt(UPPER_LIMIT * 2) - UPPER_LIMIT;
            point = new Point(xPos, yPos);

            if (!freePoints.contains(point)) {
                freePoints.push(point);
            }

        } while (freePoints.size() < numEvents);
        Collections.shuffle(freePoints);
    }

    @Override
    public World build() {
        Map<Point, Event> events = new HashMap<>();
        for (int i = 0; i < numEvents; i++) {
            Event event = factory.newEvent("Event " + (i + 1), generateTickets());
            events.put(getFreePoint(), event);
        }
        return new World(events, new Manhattan());
    }

    private List<Ticket> generateTickets() {
        List<Ticket> tickets = new ArrayList<>();
        int numberOfTickets = rnd.nextInt(maxTickets);
        for (int i = 0; i < numberOfTickets; i++) {
            int price = (int) Math.floor(rnd.nextInt(200000));
            tickets.add(new Ticket(price));
        }
        return tickets;
    }


    private Point getFreePoint() {
        // gives us faster access to free points, but slower at generation time.
        return freePoints.pop();
    }
}
