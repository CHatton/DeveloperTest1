package viagogo.worlds;


import org.testng.annotations.Test;
import viagogo.events.Event;
import viagogo.events.EventFactory;
import viagogo.worlds.distancemetrics.Manhattan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.AssertJUnit.assertEquals;

public class WorldTest {

    private World buildWorld() {
        EventFactory fact = new EventFactory();
        Map<Point, Event> events = new HashMap<>();
        events.put(new Point(0, 0), fact.newEvent("Event1"));
        events.put(new Point(1, 1), fact.newEvent("Event2"));
        events.put(new Point(4, 9), fact.newEvent("Event3"));
        events.put(new Point(3, 3), fact.newEvent("Event4"));
        events.put(new Point(4, -4), fact.newEvent("Event5"));
        events.put(new Point(5, -5), fact.newEvent("Event6"));
        events.put(new Point(-6, 6), fact.newEvent("Event7"));
        events.put(new Point(-8, -5), fact.newEvent("Event8"));
        return new World(events, new Manhattan());
    }

    @Test
    public void testClosestEventsReturnsTheCorrectNumberOfEvents() {
        World world = buildWorld();
        assertEquals(4, world.getClosestEvents(new Point(0, 0), 4).size());
        assertEquals(5, world.getClosestEvents(new Point(1, 5), 5).size());
        assertEquals(2, world.getClosestEvents(new Point(2, 4), 2).size());
        assertEquals(0, world.getClosestEvents(new Point(3, 3), 0).size());
        assertEquals(8, world.getClosestEvents(new Point(4, 2), 100).size());
        assertEquals(1, world.getClosestEvents(new Point(5, 1), 1).size());
    }

    @Test
    public void testClosestEventsReturnsTheCorrectEvents() {
        World world = buildWorld();
        List<Event> closestEvents = world.getClosestEvents(new Point(0, 0), 5);
        assertEquals("Event1", closestEvents.get(0).getName());
        assertEquals("Event2", closestEvents.get(1).getName());
        assertEquals("Event4", closestEvents.get(2).getName());
        assertEquals("Event5", closestEvents.get(3).getName());
        assertEquals("Event6", closestEvents.get(4).getName());
    }

}
