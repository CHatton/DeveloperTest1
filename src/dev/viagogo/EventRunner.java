package dev.viagogo;

import dev.viagogo.events.Event;
import dev.viagogo.events.Ticket;
import dev.viagogo.worlds.Point;
import dev.viagogo.worlds.RandomWorldBuilder;
import dev.viagogo.worlds.World;
import dev.viagogo.worlds.WorldBuilder;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class EventRunner {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        WorldBuilder builder = new RandomWorldBuilder(10, 4);
        World world = builder.build();

        while (true) {
            System.out.println("Please input Coordinates (in the form of \"x,y\")");
            System.out.print(">");

            String line = input.nextLine();
            String[] coords = line.split(",");
            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);
            Point point = new Point(x, y);
            List<Event> closeEvents = world.getClosestEvents(point);
            System.out.println("Closest events to (" + x + "," + y + "):");
            closeEvents.forEach(event -> {

                StringBuilder sb = new StringBuilder();
                int id = event.getId();
                sb.append(String.format("Event %03d - ", id));
                Optional<Ticket> cheapestTicket = event.getCheapestTicket();
                if (cheapestTicket.isPresent()) {
                    sb.append(String.format("$%.2f - ", cheapestTicket.get().getPrice() / 100.0));
                } else {
                    sb.append("no tickets available - ");
                }
                int distance = world.distanceToEvent(point, event);
                sb.append("distance: ").append(distance);
                System.out.println(sb.toString());

            });

        }
    }
}
