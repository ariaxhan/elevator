import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Simulation {

  // variables
  private variables v;
  public Building building;
  public Time time;
  public List<Elevator> elevators;
  public List<Floor> floors;

  // constructor
  public Simulation(variables v, Building building) {
    this.v = v;
    this.building = building;
    this.time = new Time(v);
    this.elevators = building.elevators;
    this.floors = building.floors;
  }

  // function to run the simulation based on ticks
  public void runSimulation(variables v) {
    // set up simulation to run once per tick
    for (long i = 0; i < v.getDuration(); i++) {
      // run simulation
      simulation(v, i);
    }
  }

  public void simulation(variables v, long tick) {
    // during each "tick" in the duration variable
    // operations on elevators
    System.out.println("Simulation started");
    // operations on floors
    // loop through list of floors in the building
    for (int y = 0; y < this.floors.size(); y++) {
      // generate a passenger
      Passenger p = new Passenger(v); // creating a Passenger instance
      // check if passenger was generated
      if (p.startFloor == 0 || p.destinationFloor == 0) {
        // if no passenger was generated, go to next iteration in the loop
        continue;
      } else {
        // initialize passenger time
        p.startTime = tick;
        // add the passenger to the floor's queue based on direction
        if (p.startFloor < p.destinationFloor) {
          this.building.getFloor(p.startFloor).goingup.add(p);
          // passenger added to floor queue for going up
          System.out.println("Passenger added to floor: " + p.startFloor);
        } else if (p.startFloor > p.destinationFloor) {
          this.building.getFloor(p.startFloor).goingdown.add(p);
          // passenger added to floor queue for going down
          System.out.println("Passenger added to floor: " + p.startFloor);
        }
      }
    }

    // loop through list of elevators in the building
    for (int j = 0; j < this.elevators.size(); j++) {
      Elevator currentElevator = this.elevators.get(j);
      // run load/unload and travel functions for each elevator,
      if (j % 2 == 0) {
        // travel
        travel(currentElevator, currentElevator.getCurrentFloor());
      } else {
        // load
        load(currentElevator, currentElevator.getCurrentFloor(), tick);
      }
    }
  }

  // function to Elevator unload & load: An elevator may stop at a floor and unload all passengers in the elevator
  // bound for that floor. Additionally, during the same “tick”, any passengers on the floor waiting for
  // an elevator going in the desired direction (up or down). You may assume that passengers never
  // enter an elevator going in the wrong direction.

  public void load(Elevator e, int currentFloor, long tick) {
    System.out.println("Load started");
    // add any passengers going up or down to the elevator based on direction of elevator
    // get direction of elevator
    int direction = e.getDirection();
    // get the floor object for the current floor
    Floor floor = this.building.getFloor(currentFloor);
    // get passenger list for that floor
    Queue<Passenger> pOnFloor = floor.getPassengerList(direction);
    if (pOnFloor == null) {
      return;
    }
    // loop through passengers on the floor
    while (pOnFloor.peek() != null) {
      // get next passenger from queue
      Passenger p = floor.getNextPassenger(direction);
      // check if elevator is full
      if (e.capacity == v.getElevatorCapacity()) {
        // if elevator is full, break out of loop
        return;
      } else {
        // if elevator is not full, remove passenger from floor
        floor.removePassenger(p, direction);

        // add passenger to elevator
        e.addPassenger(p);
      }
      // print passenger
      System.out.println(
        "Passenger added to elevator: on floor: " + currentFloor
      );
      // get all passengers in the elevator
      System.out.println(
        "Unload started with " + e.getPassengerList().size() + " passengers"
      );

      // loop through passengers in the elevator
      for (int r = 0; r < e.getPassengerList().size(); r++) {
        // if the passenger's destinationFloor is the current floor
        // remove the passenger from the elevator
        Passenger currentPassenger = e.getPassengerList().get(r);
        if (currentPassenger.destinationFloor == currentFloor) {
          e.removePassenger(currentPassenger);
          // print passenger
          System.out.println(
            "Passenger removed from elevator: on floor: " + currentFloor
          );
          // set end time and record passenger voyage time
          long endTime = tick;
          time.recordJourneyTime(currentPassenger.getStartTime(), endTime);
        }
      }

      System.out.println(
        "Unload ended with " + e.getPassengerList().size() + " passengers"
      );
    }
  }

  // move elevator
  // function to Elevator travel: An elevator may travel between no more than 5 floors (eg. from the 8th floor to
  // load unload must happen at current floor
  public void travel(Elevator e, int currentFloor) {
    // check direction
    if (e.getDirection() == 0) {
      // if direction is 0, set direction to 1
      e.setUp();
    }
    if (e.getCurrentFloor() < v.getFloors()) {
      // if current floor is 0, set current floor to 1
      e.setCurrentFloor(1);
      e.setUp();
      // if elevator is at max floor, set direction to -1
    } else if (e.getCurrentFloor() == v.getFloors()) {
      e.setDown();
    }
    // generate random floor to go to
    Random rand = new Random();
    if (e.getDirection() == 1) {
      // going up
      int minFloor = currentFloor + 1;
      int maxFloor = v.getFloors();
      if (minFloor + 5 < maxFloor) {
        maxFloor = minFloor + 5;
      }
      int floor = rand.nextInt((maxFloor - minFloor) + 1) + minFloor;
      // print movement
      System.out.println(
        "Elevator moved: from floor: " + currentFloor + " to floor: " + floor
      );
      // update current floor
      e.setCurrentFloor(floor);
      // update direction
      e.setDirection(currentFloor, floor);
    } else if (e.getDirection() == -1) {
      // going down
      int maxFloor = currentFloor - 1;
      int minFloor = 1;
      if (maxFloor - 5 > minFloor) {
        minFloor = maxFloor - 5;
      }
      int floor = rand.nextInt((maxFloor - minFloor) + 1) + minFloor;
      // print movement
      System.out.println(
        "Elevator moved: from floor: " + currentFloor + " to floor: " + floor
      );
      // update current floor
      e.setCurrentFloor(floor);
      // update direction
      e.setDirection(currentFloor, floor);
    }
  }
}
