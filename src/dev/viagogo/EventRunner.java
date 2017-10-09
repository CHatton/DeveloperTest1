package dev.viagogo;

import dev.viagogo.events.Event;
import dev.viagogo.worlds.Point;
import dev.viagogo.worlds.World;
import dev.viagogo.worlds.distancemetrics.Manhattan;

public class EventRunner {
    public static void main(String[] args) {
        Event e1 = new Event("Hello World");
        Event e2 = new Event("Goodbye World");
        Event e3 = new Event("Event 3");
        Event e4 = new Event("Event 4");
        Event e5 = new Event("Event 5");
        Event e6 = new Event("Event 6");

        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 0);
        Point p3 = new Point(-3, 2);
        Point p4 = new Point(7, -9);
        Point p5 = new Point(2, -4);
        Point p6 = new Point(4, -9);

        World w = new World(new Manhattan());
        w.addEventAt(e1, p1);
        w.addEventAt(e2, p2);
        w.addEventAt(e3, p3);
        w.addEventAt(e4, p4);
        w.addEventAt(e5, p5);
        w.addEventAt(e6, p6);


        System.out.println(w.getClosestEvents(new Point(3, -7), 1));
    }
}
