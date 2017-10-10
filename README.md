# Viagogo Developer Test

# Scenario

- Your program should randomly generate seed data. 
- Your program should operate in a world that ranges from -10 to +10 (Y axis), and -10 to +10 (X axis).
- Your program should assume that each co-ordinate can hold a maximum of one event.
- Each event has a unique numeric identifier (e.g. 1, 2, 3).
- Each event has zero or more tickets. Each ticket has a non-zero price, expressed in US Dollars.
- The distance between two points should be computed as the Manhattan distance. 

- You are required to write a program which accepts a user location as a pair of co- ordinates, and returns a list of the five closest events, along with the cheapest ticket price for each event. 

- Please detail any assumptions you have made. 

# Assumptions
Several assumptions were made while coding up this solution.

1. One of the specifications for a ticket was that it had a non-0 value, I operated under the assumption that tickets cannot have a negative value either. i.e. ticket.price > 0

2. When requesting the closest 5 positions, I assumed that if there were less than 5 positions, it should return all of the positions.

3. When multiple events are the same distance apart, eg. event1 and event2 both have a distance of 5, there is no preference in which appears first. This could change and cannot be relied upon. The order only garuntees that each event is of equal or increasing distance going from the start of the retrieved list.

4. I've assumed that the world will never be modified once it is created, so I chose to make it along with a lot of the other classes immutible. In a real world situation you would likely need to add/remove events and maintaining immutibility might not be as feasible.

# Question 1

How might you change your program if you needed to support multiple events at the same location?

If the program needed to be modified to support multiple events at the same point/location. The map storing them in the World class could simply be updated to be of type <Point, List\<Event\>>
Similar to how each Event can have zero or more tickets in the form of a list, a point could have zero or more events.

When looking for the N closest events, you could implement this by finding the closest point, then taking events from that point, if more were required, you could move onto the next closest point and get the events from that one. You could continue this process until you reach N events.

# Question 2

How would you change your program if you were working with a much larger world size? 

I think the current sparse matrix implementation would scale up quite well, as opposed to a box-matrix collection. However if the world size got a lot larger, you could segment up the world into multiple smaller worlds, you could then look for events in these smaller/closer to the customer worlds first. These could be prioritised based on any number of categories and determined on a per user basis.

# How to run the code

I've included a runnable jar in this respository.

Simply download the jar file at build/viagogo-dev-test.jar

Navigate to the directory the file was downloaded to, and run the command

```bash
java -jar viagogo-dev-test.jar
```

You'll need to have Java 1.8+ installed.
