import com.here.interview.*;
import com.here.interview.internal.Coordinate;
import com.here.interview.internal.UserException;
import org.junit.Assert;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestSuite {

    @Test
    public void testQuestion1() {

        final Coordinate origin = Coordinate.of(50.162123, 8.534531, "Schwalbach");

        // destinations are sorted on distances to the origin
        final List<Coordinate> destinations = new ArrayList<>(List.of(
                Coordinate.of(50.086508, 8.240075, "Wiesbaden"),
                Coordinate.of(49.487939, 8.460194, "Mannheim"),
                Coordinate.of(50.938070, 6.953619, "Cologne"),
                Coordinate.of(48.131621, 11.581930, "Munich"),
                Coordinate.of(53.545780, 10.002856, "Hamburg")
        ));

        // case 1
        final Coordinate newDest = Coordinate.of(49.452401,11.074622, "Nuremberg");

        Question1.process(origin, destinations, newDest);

        Assert.assertEquals(6, destinations.size());
        Assert.assertEquals("Wiesbaden, Mannheim, Cologne, Nuremberg, Munich, Hamburg", destinations.stream().map(c -> c.name).collect(Collectors.joining(", ")));


        // case 2
        final Coordinate newDest2 = Coordinate.of(48.196012,16.364736, "Vienna");
        Question1.process(origin, destinations, newDest2);

        Assert.assertEquals(7, destinations.size());
        Assert.assertEquals("Wiesbaden, Mannheim, Cologne, Nuremberg, Munich, Hamburg, Vienna", destinations.stream().map(c -> c.name).collect(Collectors.joining(", ")));

    }

    @Test
    public void testQuestion2() {

        //Assert.assertEquals(884591, Question2.process("CarrierCollection_lux_cellCarrier_1440.json"));

    }

    @Test
    public void testQuestion3Ok() throws Exception {
        final String durationStr = "durations:1000,300,3000,600";
        int[] durations = Question3.process(durationStr);
        Assert.assertNotNull(durations);
        Assert.assertEquals("1000,300,3000,600", Arrays.stream(durations).mapToObj(String::valueOf).collect(Collectors.joining(",")));
    }

    @Test
    public void testQuestion3Ko() throws Exception {
        // case 1
        String durationStr = "";
        expectUserException(durationStr);

        // case 2
        durationStr = "durations,1000,300,3000,600";
        expectUserException(durationStr);

        // case 3
        durationStr = "duration:1000,300,3000,600";
        expectUserException(durationStr);

        // case 4
        durationStr = "durations:1000,300,3000";
        expectUserException(durationStr);

        // case 5
        durationStr = "durations:1000,300,,3000";
        expectUserException(durationStr);

    }

    private void expectUserException(String durationStr) {
        try {
            Question3.process(durationStr);
            Assert.fail("Expect validation exception, but got none");
        }catch (UserException e){
            Assert.assertEquals("User error", e.getMessage());
        }catch (Exception e) {
            Assert.fail("Expect userException, but got: " + e);
        }
    }
}
