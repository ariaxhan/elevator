import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Elevator<Floor> {

  public int currentFloor = 0;
  public int direction = 1; // 1 for up, -1 for down
  public int capacity = 0;
  public List<Floor> floors;
  public variables v;

  // constructor
  public Elevator(variables v) {
    this.capacity = v.getElevatorCapacity();
    this.v = v;

    // heap to store floors going down
    PriorityQueue<Floor> down = new PriorityQueue<Floor>(
      Collections.reverseOrder()
    );

    // heap to store floors going up
    PriorityQueue<Floor> up = new PriorityQueue<Floor>();
  }
}
