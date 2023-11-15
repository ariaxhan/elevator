import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Elevator {

  public int currentFloor = 1;
  public int capacity = 0;
  public variables v;
  // list of all passengers in the elevator
  public List<Passenger> passengers;
  int goingUp = 0;

  // constructor
  public Elevator(variables v, int id) {
    // set variables
    this.v = v;
    // set capacity
    this.capacity = v.getElevatorCapacity();
    // set type of list for passengers
    if (v.getStructures().equals("linked")) {
      this.passengers = new LinkedList<>();
    } else if (v.getStructures().equals("array")) {
      this.passengers = new ArrayList<>();
    }
    // set current floor
    this.currentFloor = 1;
    // set direction
    this.goingUp = 1;
  }

  // method to add passenger to elevator
  public void addPassenger(Passenger p) {
    this.passengers.add(p);
  }

  // method to remove a passenger from the elevator
  public void removePassenger(Passenger p) {
    this.passengers.remove(p);
  }

  // method to get passenger list
  public List<Passenger> getPassengerList() {
    return this.passengers;
  }

  // method to get currentFloor of elevator
  public int getCurrentFloor() {
    return this.currentFloor;
  }

  // method to set currentFloor of the elevator
  public void setCurrentFloor(int currentFloor) {
    this.currentFloor = currentFloor;
  }

  // method to get direction
  public int getDirection() {
    return this.goingUp;
  }

  // function to set direction
  public void setDirection(int currentFloor, int destinationFloor) {
    if (destinationFloor > currentFloor) {
      this.goingUp = 1; // going up
    } else if (destinationFloor < currentFloor) {
      this.goingUp = -1; // going down
    } else {
      this.goingUp = 0;
    }
  }

  public void setUp() {
    this.goingUp = 1;
  }

  public void setDown() {
    this.goingUp = -1;
  }
}
