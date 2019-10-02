import com.gojek.parkinglot.ParkingLot;
import com.gojek.parkinglot.abstractions.Executor;
import com.gojek.parkinglot.executor.ExecutorFactory;
import com.gojek.parkinglot.executor.ExecutorType;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Ordered execution of test cases in this class: As ParkingLot class is singleton: can't create a lot object once created
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParkingTest {
    private final PrintStream outStream = System.out;
    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    private static final Executor executor = ExecutorFactory.getCommandExecutor(ExecutorType.INTERACTIVE);

    @Before
    public void streamSet() {
        System.setOut(new PrintStream(out));
        out.reset();
    }


    @After
    public void streamReset() {
        System.setOut(outStream);
    }

    @Test
    public void testfull() {
        String[] inputCommandArray = {
                "create_parking_lot 6\n",
                "park 1234 White\n",
                "park 9999 White\n",
                "park 0001 Black\n",
                "park MP-01-KK-1111 Red\n",
                "park MH-81-NN-2701 Blue\n",
                "park KA-21-HH-7741 Black\n",
                "leave 4\n",
                "status\n",
                "park KA-01-P-333 White\n",
                "park DL-12-AA-9999 White\n",
                "registration_numbers_for_cars_with_colour White\n",
                "slot_numbers_for_cars_with_colour White\n",
                "slot_number_for_registration_number KA-21-HH-7741\n",
                "slot_number_for_registration_number MH-04-AY-1111\n"
        };

        String[] outputArray = {
                "Parking lot created with 6 slots\n",
                "Parked in slot number: 1\n",
                "Parked in slot number: 2\n",
                "Parked in slot number: 3\n",
                "Parked in slot number: 4\n",
                "Parked in slot number: 5\n",
                "Parked in slot number: 6\n",
                "Slot number 4 is free\n",
                "Slot No.    Registration No    Colour\n1           1234      White\n2           9999      White\n3           0001      Black\n5           MH-81-NN-2701      Blue\n6           KA-21-HH-7741      Black\n",
                "Parked in slot number: 4\n",
                "parking lot is full\n",
                "1234, 9999, KA-01-P-333\n",
                "1, 2, 4\n",
                "6\n",
                "Not found\n"
        };

        for (int i = 0; i < inputCommandArray.length; i++) {
            executor.execute(inputCommandArray[i]);
            Assert.assertEquals(outputArray[i], out.toString());
            out.reset();

        }


    }

    @Test
    public void unparkTest() {
        if (ParkingLot.getLot() != null){
            executor.execute("leave 6\n");
            Assert.assertEquals("Slot is already free\n", out.toString());
        }
    }


    @Test
    public void parkTest() {
        if (ParkingLot.getLot() != null) {
            executor.execute("park DL-12-AA-9999 Green");
            Assert.assertEquals("Parked in slot number: 6\n", out.toString());
        }
    }

    @Test
    public void parkingFullTest() {
        if (ParkingLot.getLot() != null) {
            executor.execute("park DL-22-AA-9939 Green");
            Assert.assertEquals("parking lot is full\n", out.toString());
        }
    }

    @Test
    public void notFoundTest() {
        if (ParkingLot.getLot() != null) {
            executor.execute("registration_numbers_for_cars_with_colour magenta");
            Assert.assertEquals("Not found\n", out.toString());
        }
    }

    @Test
    public void invalidCommandTest() {
        if (ParkingLot.getLot() != null) {
            executor.execute("AnyJunkCommand blah blah");
            Assert.assertEquals("Invalid command\n", out.toString());
        }
    }


    @Test
    public void invalidCommand1Test() {
        if (ParkingLot.getLot() != null) {
            executor.execute("^%#%@*@&@*&*@&*@");
            Assert.assertEquals("Invalid command\n", out.toString());
        }
    }


    @Test
    public void unpark1Test() {
        if (ParkingLot.getLot() != null){
            executor.execute("leave 6\n");
            Assert.assertEquals("Slot number 6 is free\n", out.toString());
        }
    }

    @Test
    public void unpark2Test() {
        if (ParkingLot.getLot() != null){
            executor.execute("leave 5\n");
            Assert.assertEquals("Slot number 5 is free\n", out.toString());
        }
    }

    @Test
    public void unpark3Test() {
        if (ParkingLot.getLot() != null){
            executor.execute("leave 4\n");
            Assert.assertEquals("Slot number 4 is free\n", out.toString());
        }
    }

    @Test
    public void unpark4Test() {
        if (ParkingLot.getLot() != null){
            executor.execute("leave 3\n");
            Assert.assertEquals("Slot number 3 is free\n", out.toString());
        }
    }

    @Test
    public void unpark5Test() {
        if (ParkingLot.getLot() != null){
            executor.execute("leave 2\n");
            Assert.assertEquals("Slot number 2 is free\n", out.toString());
        }
    }

    @Test
    public void unpark6Test() {
        if (ParkingLot.getLot() != null){
            executor.execute("leave 1\n");
            Assert.assertEquals("Slot number 1 is free\n", out.toString());
        }
    }

    @Test
    public void unpark7Test() {
        if (ParkingLot.getLot() != null){
            executor.execute("leave 7\n");
            Assert.assertEquals("Invalid Slot Number\n", out.toString());
        }
    }

    @Test
    public void invalidArgumentTest() {
        if (ParkingLot.getLot() != null) {
            executor.execute("park blah blah blah blah");
            Assert.assertEquals("", out.toString());
        }
    }


}