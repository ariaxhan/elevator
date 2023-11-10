import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Elevator {

  public int currentFloor = 0;
  public int direction = 0; // 1 for up, -1 for down, 0 for not moving
  public int capacity = 0;
  public List<Floor> floors;
  public variables v;
  // heap to store passengers going down
  PriorityQueue<Passenger> down;
  // heap to store passengers going up
  PriorityQueue<Passenger> up;

  // constructor
  public Elevator(variables v) {
    this.v = v;
    this.capacity = v.getElevatorCapacity();
    if (v.getStructures().equals("linked")) {
      this.floors = new LinkedList<>();
    } else if (v.getStructures().equals("array")) {
      this.floors = new ArrayList<>(v.floors);
    }

    // Initialize each floor of the building
    for (int j = 0; j < v.floors; j++) {
      Floor floor = new Floor(j, v);
      floors.add(floor); // Create a new list for each floor
    }
  }

  // get floor based on index
  public Floor getFloor(int index) {
    return floors.get(index);
  }

  // heaps for destination floors of passengers

  // function to get direction
  public int getDirection(int currentFloor, int destinationFloor) {
    if (destinationFloor > currentFloor) {
      return 1;
    } else if (destinationFloor < currentFloor) {
      return -1;
    } else {
      return 0;
    }
  }
}
