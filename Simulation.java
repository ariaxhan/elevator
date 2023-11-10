import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Simulation {

  // variables
  private variables v;
  public Building building;

  // constructor
  public Simulation(variables v, Building building) {
    this.v = v;
    this.building = new Building(v);
  }

  public void runSimulation(variables v) {
    // during each "tick" in the duration variable
    // operations on elevators
    for (int i = 0; i < v.getDuration(); i++) {
      // loop through list of elevators in the building
      for (int j = 0; j < v.getElevators(); j++) {
        Elevator currentElevator = building.getElevators(j);
        // run load/unload and travel functions for each elevator,
        // alternating between the two functions every tick
        if (i % 2 == 0) {
          // load
          load(currentElevator, building, currentElevator.getCurrentFloor());
          // unload
          unload(currentElevator, building, currentElevator.getCurrentFloor());
        } else {
          // travel
          travel(currentElevator, currentElevator.getCurrentFloor());
        }
      }
      // operations on floors
      // loop through list of floors in the building
      for (int y = 0; y < v.getFloors(); y++) {
        // generate a passenger
        Passenger p = new Passenger(v); // creating a Passenger instance
        // check if passenger was generated
        if (p.startFloor == 0 || p.destinationFloor == 0) {
          // if no passenger was generated, do nothing
        } else {
          // add the passenger to the floor's queue based on direction
          if (p.startFloor < p.destinationFloor) {
            building.getFloor(p.startFloor).goingup.add(p);
          } else if (p.startFloor > p.destinationFloor) {
            building.getFloor(p.startFloor).goingdown.add(p);
          }
        }
      }
    }
  }

  // function to Elevator unload & load: An elevator may stop at a floor and unload all passengers in the elevator
  // bound for that floor. Additionally, during the same “tick”, any passengers on the floor waiting for
  // an elevator going in the desired direction (up or down). You may assume that passengers never
  // enter an elevator going in the wrong direction.

  public void load(Elevator e, Building b, int currentFloor) throws Exception {
    // add any passengers going up or down to the elevator based on direction of elevator
    // get direction of elevator
    int direction = e.getDirection();
    // get the floor object for the current floor
    Floor floor = b.getFloor(currentFloor);
    // get passenger list for that floor
    Queue<Passenger> pOnFloor = floor.getPassengerList(direction);
    // loop through passengers on the floor
    for (int i = 0; i < pOnFloor.size(); i++) {
      // get next passenger from queue
      Passenger p = floor.getNextPassenger(direction);
      // check if elevator is full
      if (e.capacity == v.getElevatorCapacity()) {
        // if elevator is full, break out of loop
        break;
      } else {
        // if elevator is not full, remove passenger from floor
        floor.removePassenger(p, direction);
      }
      // add passenger to elevator
      e.addPassenger(p);
    }
  }

  // function to unload elevator based on the current floor
  public void unload(Elevator e, Building b, int currentFloor) {
    // get all passengers in the elevator
    List<Passenger> pInElevator = e.getPassengerList();
    // loop through passengers in the elevator
    for (int r = 0; r < pInElevator.size(); r++) {
      // if the passenger's destinationFloor is the current floor
      // remove the passenger from the elevator
      if (pInElevator.get(r).destinationFloor == currentFloor) {
        e.removePassenger(pInElevator.get(r));
      }
    }
  }

  // move elevator
  // function to Elevator travel: An elevator may travel between no more than 5 floors (eg. from the 8th floor to
  // load unload must happen at current floor
  public void travel(Elevator e, int currentFloor) {
    // generate random floor to go to
    Random rand = new Random();
    int floor = rand.nextInt(v.getFloors()) + 1;
    // make sure floor is not the same as current floor
    while (floor == currentFloor) {
      floor = rand.nextInt(v.getFloors()) + 1;
    }
    // check if the elevator traveled more than 5 floors
    int floorstraveled = Math.abs(floor - currentFloor);
    while (floorstraveled > 5) {
      floor = rand.nextInt(v.getFloors()) + 1;
    }
    // update floors traveled
    floorstraveled = Math.abs(floor - currentFloor);
    // update current floor
    e.setCurrentFloor(currentFloor);
    // update direction
    e.setDirection(currentFloor, floor);
  }
}
