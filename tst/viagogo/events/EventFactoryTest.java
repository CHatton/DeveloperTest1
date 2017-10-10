package viagogo.events;

import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;


public class EventFactoryTest {

    @Test
    public void testEventFactoryCreatesUniqueIds() {
        EventFactory fact = new EventFactory();
        Set<Long> ids = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            ids.add(fact.newEvent("Event").getId());
        }
        assertEquals(10000, ids.size());
    }

}
