import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class main {

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
            v.setDefault();
        }
        // test print
        v.print();
        return v;
    }

  public static void runSimulation(variables v) {
      Random rand = new Random();
      int n;
    // function to run simulation
    // during each "tick" in the duration variable, one of the following may occur
    for (int i = 0; i < v.getDuration(); i++) {
      // randomly generate a number between 1 and 3 to determine which of the following
      n = rand.nextInt(3) + 1;
      // Elevator unload & load: An elevator may stop at a floor and unload all passengers in the elevator
      // bound for that floor. Additionally, during the same “tick”, any passengers on the floor waiting for
      // an elevator going in the desired direction (up or down). You may assume that passengers never
      // enter an elevator going in the wrong direction.
      // Elevator travel: An elevator may travel between no more than 5 floors (eg. from the 8th floor to
      // the 13th floor).
      //every multiple passengers
      // use queue, up and down

      // New passengers: Given “passengers” — the probabilities in the property file — a new passenger
      // may appear on a floor and request transportation to another floor
    }
  }

    // linkedlist, arraylist
    // linked queue

    // use an array to store a heap
    // down max heap
    // up min heap

    // list interface to implement linkedlist and arraylist
    // queue interface to implement linkedqueue and arrayqueue

    private class Elevator {
        private int currentFloor = 0;
        private int direction = 1; // 1 for up, -1 for down
        private int capacity = 0;
        private List<Passenger> passengers;
        private String structureType;

        // constructor
        public Elevator(int capacity, String structureType) {
            this.capacity = capacity;
            this.structureType = structureType;
            if (structureType.equals("linked")) {
                passengers = new LinkedList<Passenger>();
            } else if (structureType.equals("array")) {
                passengers = new ArrayList<Passenger>();
            }
        }
    }

    private class Passenger {
        int startFloor;
        int endFloor;
        int startTime;
        int direction;

        // constructor
        public Passenger(int startFloor, int endFloor, int startTime, int direction) {
            this.startFloor = startFloor;
            this.endFloor = endFloor;
            this.startTime = startTime;
            this.direction = direction;
        }

       // function to add passenger to a floor
        public void addPassenger(Passenger p, List<Passenger> floor) {
            floor.add(p);
        }

    }

    private class generatePassenger {
        private int floors;
        private double passengersProbability;
        private int direction; // 1 for up, -1 for down
        // constructor

        public generatePassenger(int floors, double passengersProbability) {
            this.floors = floors;
            this.passengersProbability = passengersProbability;
        }

        // function to generate a passenger
        public Passenger generate() {
            Random rand = new Random();
            int startFloor = rand.nextInt(floors) + 1;
            int endFloor = rand.nextInt(floors) + 1;
            // make sure startFloor and endFloor are not the same
            while (startFloor == endFloor) {
                endFloor = rand.nextInt(floors) + 1;
            }
            // get direction
            direction = getDirection(startFloor, endFloor);
            return new Passenger(startFloor, endFloor, 0, direction);
        }

        // determining direction
        public int getDirection(int startFloor, int endFloor) {
            if (startFloor < endFloor) {
                return 1;
            } else {
                return -1;
            }
        }
    }

  public static void main(String[] args) {
    // create variables object
    variables v = new variables();
    // read in properties file
    readFile("test.properties", v);
  }
}

