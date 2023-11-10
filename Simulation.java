import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

public class Simulation {

  // variables
  private variables v;
  private Elevator elevator;

  // constructor
  public Simulation(variables v, Elevator elevator) {
    this.v = v;
    this.elevator = elevator;
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
        // generate a passenger for each floor
        // for each floor
        for (int j = 0; j < v.getFloors(); j++) {
          // generate a passenger
          Passenger p = new Passenger(); // creating a Passenger instance
          // check if passenger was generated
          if (p.startFloor == 0 || p.destinationFloor == 0) {
            // if no passenger was generated, do nothing
          } else {
            // add the passenger to the floor's queue
            addPassenger(elevator.getFloor(j), elevator); // Correctly adding to the list
          }
        }
      } else {
        System.out.println("Error: invalid number generated");
      }
    }
  }

  // heap to store floors going down

  PriorityQueue<Integer> down = new PriorityQueue<Integer>(
    Collections.reverseOrder()
  );
  // heap to store floors going up
  PriorityQueue<Integer> up = new PriorityQueue<Integer>();

  // function to Elevator unload & load: An elevator may stop at a floor and unload all passengers in the elevator
  // bound for that floor. Additionally, during the same “tick”, any passengers on the floor waiting for
  // an elevator going in the desired direction (up or down). You may assume that passengers never
  // enter an elevator going in the wrong direction.

  public void load(int currentFloor) {
    // generate random floor to go to
    Random rand = new Random();
    int floor = rand.nextInt(v.getFloors()) + 1;
    // make sure floor is not the same as current floor
    while (floor == currentFloor) {
      floor = rand.nextInt(v.getFloors()) + 1;
    }
    // check direction
    int direction = getDirection(currentFloor, floor);
    // get number of floors traveled
    int floorstraveled = Math.abs(floor - currentFloor);
    if (direction == 1) {
      // check if there are passengers on the floor going up
      for (int i = 0; i < floorstraveled; i++) {
        currentFloor = floor + i;
        // if there are passengers on a floor that is being passed, add them to the elevator
        for (i = 0; i < passengers.size(); i++) {
          // if the passenger is going in the same direction as the elevator
          if (passengers.get(i).getEndFloor() > currentFloor) {
            // add the passenger to the elevator
            passengers.add(passengers.get(i));
            // remove the passenger from the floor
            passengers.remove(passengers.get(i));
          }
        }
        // if there are passengers in the elevator that are going to the current floor, remove them
        for (i = 0; i < passengers.size(); i++) {
          // if the passenger is going to the current floor
          if (passengers.get(i).getEndFloor() == currentFloor) {
            // remove the passenger from the elevator
            passengers.remove(passengers.get(i));
          }
        }
      }
    } else if (direction == -1) {
      // check if there are passengers on the floor going down
      for (int i = 0; i < floorstraveled; i++) {
        currentFloor = floor - i;
        // check if there are passengers on a floor that is being passed
        // loop through passengers on the floor, aka in the floor list
        for (i = 0; i < passengers.size(); i++) {
          // if the passenger is going in the same direction as the elevator
          if (passengers.get(i).getEndFloor() < currentFloor) {
            // add the passenger to the elevator
            passengers.add(passengers.get(i));
            // remove the passenger from the floor
            passengers.remove(passengers.get(i));
          }
        }
        // if there are passengers in the elevator that are going to the current floor, remove them
        for (i = 0; i < passengers.size(); i++) {
          // if the passenger is going to the current floor
          if (passengers.get(i).getEndFloor() == currentFloor) {
            // remove the passenger from the elevator
            passengers.remove(passengers.get(i));
          }
        }
      }
    }
  }
}
