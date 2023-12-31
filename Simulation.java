import java.util.List;
import java.util.Queue;

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
    this.time = new Time();
    this.elevators = building.getElevators();
    this.floors = building.getFloors();
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
    boolean toggle = true;
    // during each "tick" in the duration variable
    // generate a passenger for each floor
    for (int r = 0; r < floors.size(); r++) {
      Passenger p = new Passenger(v); // creating a Passenger instance
      // check if passenger was generated
      if (p.startFloor == 0 || p.destinationFloor == 0) {
        // if no passenger was generated, skip
      } else {
        // initialize passenger time
        p.startTime = tick;
        // add the passenger to the floor's queue based on direction
        if (p.startFloor < p.destinationFloor) {
          this.building.getFloor(p.startFloor).goingUp.add(p);
        } else if (p.startFloor > p.destinationFloor) {
          this.building.getFloor(p.startFloor).goingDown.add(p);
        }
      }
    }
    // loop through list of elevators in the building
    for (int j = 0; j < this.elevators.size(); j++) {
      Elevator currentElevator = this.elevators.get(j);
      if (toggle) {
        // run load/unload and travel functions for each elevator,
        travel(currentElevator, currentElevator.getCurrentFloor());
      }
      // load and unload passengers
      load(currentElevator, currentElevator.getCurrentFloor(), tick);
      unload(currentElevator, currentElevator.getCurrentFloor(), tick);

    }
    toggle = !toggle;
  }

  // function to Elevator unload & load: An elevator may stop at a floor and unload all passengers in the elevator
  // bound for that floor. Additionally, during the same “tick”, any passengers on the floor waiting for
  // an elevator going in the desired direction (up or down). You may assume that passengers never
  // enter an elevator going in the wrong direction.

  public void load(Elevator e, int currentFloor, long tick) {
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
    while (pOnFloor.peek() != null && e.capacity < v.getElevatorCapacity()) {
      // get next passenger from queue
      Passenger p = floor.getNextPassenger(direction);
      // add passenger to elevator
      e.addPassenger(p);
    }
  }

  public void unload(Elevator e, int currentFloor, long tick) {
    // loop through passengers in the elevator
    while (e.getPassengerList().peek() != null && e.getPassengerList().peek().getdestinationFloor() == currentFloor) {
      // if the passenger's destinationFloor is the current floor
      // remove the passenger from the elevator
      Passenger currentPassenger = e.getPassengerList().poll();
      // set end time and record passenger voyage time
      long endTime = tick;
      time.recordJourneyTime(currentPassenger.getStartTime(), endTime);
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
    int nextFloor = 0;
    if (e.getPassengerList().peek() != null) {
      // get next floor to go to
      nextFloor = e.getPassengerList().peek().getdestinationFloor();
  } else {
    return;
    }

    // make sure it is within range of 5 and not greater or less than total number of floors
    if (Math.abs(currentFloor - nextFloor) > 5) {
      if (e.getDirection() == 1) {
        nextFloor = currentFloor + 5;
        if (nextFloor > v.getFloors()) {
          nextFloor = v.getFloors();
        } else if (e.getDirection() == -1) {
          nextFloor = currentFloor - 5;
          if (nextFloor < 1) {
            nextFloor = 1;
          }
        }
      }

      e.setCurrentFloor(nextFloor);
      // update direction
      e.setDirection(currentFloor, nextFloor);
      // update current floor
      e.setCurrentFloor(nextFloor);
    }
  }
}
