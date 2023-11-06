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
      // create properties object
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
      if (n == 1) {
        // Elevator unload & load: An elevator may stop at a floor and unload all passengers in the elevator
        // bound for that floor. Additionally, during the same “tick”, any passengers on the floor waiting for
        // an elevator going in the desired direction (up or down). You may assume that passengers never
        // enter an elevator going in the wrong direction.
      } else if (n == 2) {
        // Elevator travel: An elevator may travel between no more than 5 floors (eg. from the 8th floor to
        // the 13th floor).
        //every multiple passengers
        // use queue, up and down
      } else if (n == 3) {
        // New passengers: Given “passengers” — the probabilities in the property file — a new passenger
        // may appear on a floor and request transportation to another floor
      } else {
        System.out.println("Error: invalid number generated");
      }
    }
  }

  // linkedlist, arraylist
  // linked queue

  // use an array to store a heap
  // down max heap
  // up min heap

  // list interface to implement linkedlist and arraylist
  // queue interface to implement linkedqueue and arrayqueue

  // notes:
  // 1. use a heap to store the floors of each elevator
  // 2. use a queue within each floor to store passengers
  // 3. generate elevator and run simulation in main

  private class Floor {
    public int floor = 0;
    public List<Passenger> passengers;
    public variables v;

    // constructor
    public Floor(int floor, variables v) {
      this.floor = floor;
      this.v = v;
      if (v.getStructures().equals("linked")) {
        passengers = new LinkedList<Passenger>();
      } else if (v.getStructures().equals("array")) {
        passengers = new ArrayList<Passenger>();
      }
    }

    // function to add passenger to elevator
    public void addPassenger(Passenger p) {
      passengers.add(p);
    }

    // function to remove passenger from elevator
    public void removePassenger(Passenger p) {
      passengers.remove(p);
    }

    public void getPassengers() {
      
    }
  }

  private class Elevator {

    public int currentFloor = 0;
    public int direction = 1; // 1 for up, -1 for down
    public int capacity = 0;
    public List<Floor> floors;
    private String structureType;
    public variables v;

    // constructor
    public Elevator(int capacity, String structureType, variables v) {
      this.capacity = capacity;
      this.structureType = structureType;
      this.v = v;
      // create list of floors based on type
      if (structureType.equals("linked")) {
        this.floors = new LinkedList<Floor>();
      } else if (structureType.equals("array")) {
        this.floors = new ArrayList<Floor>();
      }
      // add the floors 
      for (int i = 0; i < v.getFloors(); i++) {
        floors.add(new Floor(i, v));
      }
    }

    // get passengers on a given floor
    public void getPassengers(int floor) {
      floors.get(floor);
    }

    // function to Elevator unload & load: An elevator may stop at a floor and unload all passengers in the elevator
    // bound for that floor. Additionally, during the same “tick”, any passengers on the floor waiting for
    // an elevator going in the desired direction (up or down). You may assume that passengers never
    // enter an elevator going in the wrong direction.
    public void load() {
      // generate random floor to go to
      Random rand = new Random();
      int floor = rand.nextInt(v.getFloors()) + 1;
      // make sure floor is not the same as current floor
      while (floor == currentFloor) {
        floor = rand.nextInt(v.getFloors()) + 1;
      }
      // check direction
      this.direction = getDirection(currentFloor, floor);
      int floorstraveled = Math.abs(floor - currentFloor);
      if (direction == 1) {
        // check if there are passengers on the floor going up
        for (int i = 0; i < floorstraveled; i++) {
          currentFloor = floor + i;
          floors.getPassengers(currentFloor);
        }
      } else if (direction == -1) {
        // check if there are passengers on the floor going down
      }
    }
  

  // Elevator travel: An elevator may travel between no more than 5 floors (eg. from the 8th floor to
  // the 13th floor).
  public void travel() {
    // check direction
    if (direction == 1) {
      // check if there are passengers on the floor going up
    } else if (direction == -1) {
      // check if there are passengers on the floor going down
    }
  }
}

  private class Passenger {

    int startFloor;
    int endFloor;
    int direction;

    public Passenger() {
      Passenger p = generatePassenger(v.getFloors(), v.getPassengers());
      this.startFloor = 0;
      this.endFloor = 0;
    }

    // function to add passenger to a floor
    public void addPassenger(Passenger p, List<Passenger> floor) {
      floor.add(p);
    }

    // function to remove passenger from a floor
    public void removePassenger(Passenger p, List<Passenger> floor) {
      floor.remove(p);
    }

    // getters
    public int getStartFloor() {
      return startFloor;
    }

    public int getEndFloor() {
      return endFloor;
    }
  }

  // determining direction
  public int getDirection(int startFloor, int endFloor) {
    // going up
    if (startFloor < endFloor) {
      return 1;
      // going down
    } else if (startFloor > endFloor) {
      return -1;
    } else {
      return 0;
    }
  }

  private class generatePassenger {

    private int floors;
    private double passengersProbability;
    public Passenger p = new Passenger();

    // constructor
    public generatePassenger(int floors, double passengersProbability) {
      // set floors and probability of passenger generation
      this.floors = floors;
      this.passengersProbability = passengersProbability;
      // generate new passenger
      p = generate(floors, passengersProbability);
      // check if passenger was generated
      if (p != null) {
        // add the passenger to the start floor
        p.addPassenger(p, p.getStartFloor());
      }
    }

    // function to generate a passenger
    public Passenger generate(int floors, double passengersProbability) {
      // create random object
      Random rand = new Random();
      // loop through all floors
      for (int currentFloor = 1; currentFloor <= floors; currentFloor++) {
        // randomly generate a number between 0 and 1
        double num = rand.nextDouble();
        if (num < passengersProbability) {
          // generate int as the floor
          int endFloor = rand.nextInt(floors) + 1;
          // make sure start and end floors are not the same
          while (endFloor == currentFloor) {
            endFloor = rand.nextInt(floors) + 1;
          }
          // set start and end floor and return
          p.startFloor = currentFloor;
          p.endFloor = endFloor;
          return p;
        }
      }
      // if no passenger was generated, return null
      return null;
    }
  }

  public static void main(String[] args) {
    // create variables object
    variables v = new variables();
    // read in properties file
    readFile("test.properties", v);
  }
}
