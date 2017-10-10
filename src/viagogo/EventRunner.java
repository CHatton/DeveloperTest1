package viagogo;


import viagogo.events.Event;
import viagogo.events.Ticket;
import viagogo.worlds.Point;
import viagogo.worlds.RandomWorldBuilder;
import viagogo.worlds.World;
import viagogo.worlds.WorldBuilder;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class EventRunner {

    public static void main(String[] args) {
        WorldBuilder builder = new RandomWorldBuilder(10, 4);
        World world = builder.build();

        while (true) {
            displayOptions();
            processOptions(world);
        }
    }

    private static void displayOptions() {
        System.out.println("Choose option:");
        System.out.println("1.) Find events near you.");
        System.out.println("2.) Display all events and locations.");
        System.out.println("3.) Exit.");
    }

    private static void processOptions(World world) {
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            case 1:
                // invalid values.
                int x = -11;
                int y = -11;
                do {
                    System.out.println("Please input Coordinates (in the form of \"x,y\")");
                    System.out.println("Coordinates must be between -10 and +10 in both x and y.");
                    System.out.print(">");
                    String coordsAsString = sc.nextLine().trim();
                    try {

                        // allow spaces between comma and digits.
                        String[] coordsAsArr = coordsAsString.split("\\s*,\\s*");
                        if (coordsAsArr.length != 2) { // too few or too many comma separated values provided.
                            System.out.println("Please enter 2 values, e.g. \"2,3\"");
                            continue;
                        }
                        x = Integer.parseInt(coordsAsArr[0]);
                        y = Integer.parseInt(coordsAsArr[1]);
                    } catch (NumberFormatException e) { // parsing error
                        System.out.println("Please enter 2 integers. E.g. \"3,-6\"");
                    }
                } while ((x < -10 || x > 10) || (y < -10 || y > 10));
                Point point = new Point(x, y);

                List<Event> closeEvents = world.getClosestEvents(point);
                System.out.println("Closest events to (" + x + "," + y + "):");
                closeEvents.forEach(event -> displayEventInformation(event, point, world));
                break;
            case 2:
                System.out.println(world);
                break;
            case 3:
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Please enter a valid option.");
        }
    }

    private static void displayEventInformation(Event event, Point origin, World world) {
        StringBuilder sb = new StringBuilder();
        int id = event.getId();
        sb.append(String.format("Event %03d - ", id));
        Optional<Ticket> cheapestTicket = event.getCheapestTicket();
        if (cheapestTicket.isPresent()) {
            sb.append(String.format("$%.2f - ", cheapestTicket.get().getPrice() / 100.0));
        } else {
            sb.append("no tickets available - ");
        }
        int distance = world.distanceToEvent(origin, event);
        sb.append("distance: ").append(distance);
        System.out.println(sb.toString());
    }

}
