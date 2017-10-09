package test.viagogo.worlds;


import dev.viagogo.dev.viagogo.events.Event;
import dev.viagogo.worlds.Point;
import dev.viagogo.worlds.World;
import dev.viagogo.worlds.distancemetrics.Manhattan;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class WorldTest {

    private World buildWorld() {
        World world = new World(new Manhattan());
        world.addEventAt(new Event("Event1"), new Point(0, 0));
        world.addEventAt(new Event("Event2"), new Point(1, 1));
        world.addEventAt(new Event("Event3"), new Point(4, 9));
        world.addEventAt(new Event("Event4"), new Point(3, 3));
        world.addEventAt(new Event("Event5"), new Point(4, -4));
        world.addEventAt(new Event("Event6"), new Point(5, -5));
        world.addEventAt(new Event("Event7"), new Point(-6, 6));
        world.addEventAt(new Event("Event8"), new Point(-8, -5));
        return world;
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
