import java.io.FileReader;
import java.util.Properties;
import java.util.Random;

public class elevator {

    // function to read in properties file
    public static variables readFile(String fileName, variables v) {
        try {
            // create reader object
            FileReader reader = new FileReader("test.properties");
            // create properties objectd
            Properties p = new Properties();
            // add a wrapper around reader object
            p.load(reader);
            // access data and fill in variables object
            v.setStructures(p.getProperty("structures"));
            v.setFloors(Integer.parseInt(p.getProperty("floors")));
            v.setPassengers(Double.parseDouble(p.getProperty("passengers")));
            v.setElevators(Integer.parseInt(p.getProperty("elevators")));
            v.setElevatorCapacity(
                    Integer.parseInt(p.getProperty("elevatorCapacity")));
            v.setDuration(Integer.parseInt(p.getProperty("duration")));

            // close reader
            reader.close();
        } catch (Exception e) {
            // if the file is not found, fill in with default values
            v.structures = "linked";
            v.floors = 32;
            v.passengers = 0.03;
            v.elevators = 1;
            v.elevatorCapacity = 10;
            v.duration = 500;
        }
        // test print
        v.print();

        return v;
    }


    public static void runSimulation(variables v) {
        // function to run simulation
        // during each "tick" in the duration variable, one of the following may occur
        for (int i = 0; i < v.getDuration(); i++) {
            // randomly generate a number between 1 and 3 to determine which of the following
            Random rand = new Random();
            int n = rand.nextInt(3) + 1;

            // Elevator unload & load: An elevator may stop at a floor and unload all passengers in the elevator
            // bound for that floor. Additionally, during the same “tick”, any passengers on the floor waiting for
            // an elevator going in the desired direction (up or down). You may assume that passengers never
            // enter an elevator going in the wrong direction.

            // Elevator travel: An elevator may travel between no more than 5 floors (eg. from the 8th floor to
            // the 13th floor).

            // New passengers: Given “passengers” — the probabilities in the property file — a new passenger
            // may appear on a floor and request transportation to another floor
        }
    }


    public static void main(String[] args) {
        // create variables object
        variables v = new variables();
        // read in properties file
        readFile("test.properties", v);
    }

}
