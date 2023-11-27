import java.util.Comparator;
import java.util.PriorityQueue;

public class Elevator {

  public static final int UP = 1;
  public static final int DOWN = -1;
  public static final int STATIONARY = 0;
  public static int nextId = 1;

  public int currentFloor;
  public int capacity;
  public variables v;
  public PriorityQueue<Passenger> upPassengers;
  public PriorityQueue<Passenger> downPassengers;
  public int direction;
  public int id;

  // Constructor
  public Elevator(variables v) {
    this.upPassengers = new PriorityQueue<>();
    this.downPassengers = new PriorityQueue<>(Comparator.reverseOrder());
    this.v = v;
    this.capacity = 0;
    this.currentFloor = 1;
    this.direction = 0; 
    this.id = nextId;
    nextId++;
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
        if (direction == UP) {
      upPassengers.add(p);
    } else if (direction == DOWN) {
      downPassengers.add(p);
    }
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

  public PriorityQueue<Passenger> getPassengerList() {
    if (direction == UP) {
      return upPassengers;
    } else {
      return downPassengers;
    } 
  }

  public void removePassenger() {
    if (direction == UP) {
      upPassengers.poll();
    } else if (direction == DOWN) {
      downPassengers.poll();
    } else {
      System.err.println("Error, elevator not moving");
    }
    capacity--;
  }
}
