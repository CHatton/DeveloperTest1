package viagogo.worlds;

import viagogo.events.Event;
import viagogo.events.Ticket;
import viagogo.worlds.distancemetrics.Manhattan;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomWorldBuilder implements WorldBuilder {

    private final static int UPPER_LIMIT = 10;

    private final int numEvents;
    private final World world;
    private final Random rnd;
    private final Set<Point> usedPoints;
    private final int maxTickets;

    public RandomWorldBuilder(int numEvents, int maxTickets) {
        this.numEvents = numEvents;
        this.maxTickets = maxTickets;
        world = new World(new Manhattan());
        rnd = new Random();
        usedPoints = new HashSet<>();
    }

    @Override
    public World build() {
        for (int i = 0; i < numEvents; i++) {
            String eventName = "Event " + (i + 1);
            Event event = new Event(eventName);
            addTicketsToEvent(event);
            Point point = generateUnusedPoint();
            world.addEventAt(event, point);
        }
        return world;
    }

    private void addTicketsToEvent(Event event) {
        int numberOfTickets = rnd.nextInt(maxTickets);
        for (int i = 0; i < numberOfTickets; i++) {
            int price = (int) Math.floor(rnd.nextInt(200000));
            event.addTicket(new Ticket(price));
        }
    }

    private Point generateUnusedPoint() {
        Point point;
        do {
            int xPos = rnd.nextInt(UPPER_LIMIT + 1);
            int yPos = rnd.nextInt(UPPER_LIMIT + 1);
            // gives an approx 50/50 chance of changing the sign. leaving the range at -10 -> +10
            if (rnd.nextBoolean()) {
                xPos *= -1;
            }
            if (rnd.nextBoolean()) {
                yPos *= -1;
            }
            point = new Point(xPos, yPos);

        } while (usedPoints.contains(point));
        usedPoints.add(point); // so we don't re-use the same point. 1 Event per point.
        return point;
    }
}
