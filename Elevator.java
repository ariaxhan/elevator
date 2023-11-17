import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Elevator {

  public static final int UP = 1;
  public static final int DOWN = -1;
  public static final int STATIONARY = 0;
  public static int nextId = 1;

  public int currentFloor;
  public int capacity;
  public variables v;
  public List<Passenger> passengers;
  public int direction;
  public int id;

  // Constructor
  public Elevator(variables v) {
    this.v = v;
    this.capacity = 0;
    this.currentFloor = 1;
    this.direction = UP; // Initial direction can be set to UP or STATIONARY
    this.id = nextId;
    nextId++;

    if (v.getStructures().equals("linked")) {
      this.passengers = new LinkedList<>();
    } else {
      this.passengers = new ArrayList<>();
    }
  }

  public int getCurrentFloor() {
    return currentFloor;
  }

  public void setCurrentFloor(int floor) {
    this.currentFloor = floor;
  }

  public int getDirection() {
    return direction;
  }

  public int getId() {
    return id;
  }

  public int addPassenger(Passenger p) {
    if (capacity == v.getElevatorCapacity()) {
      return -1;
    }
    passengers.add(p);
    capacity++;
    return 0;
  }

  public void setDirection(int currentFloor, int destinationFloor) {
    if (destinationFloor > currentFloor) {
      direction = UP;
    } else if (destinationFloor < currentFloor) {
      direction = DOWN;
    } else {
      direction = STATIONARY;
    }
  }

  public void setUp() {
    direction = UP;
  }

  public void setDown() {
    direction = DOWN;
  }

  public List<Passenger> getPassengerList() {
    return passengers;
  }

  public void removePassenger(Passenger p) {
    passengers.remove(p);
    capacity--;
  }
}
