import java.util.LinkedList;
import java.util.Queue;

public class Floor {

  public int floorNumber;
  public static final int UP = 1;
  public static final int DOWN = -1;
  public Queue<Passenger> goingUp;
  public Queue<Passenger> goingDown;
  public variables v;

  // Constructor
  public Floor(int floorNumber, variables v) {
    this.floorNumber = floorNumber;
    this.v = v;
    this.goingUp = new LinkedList<>();
    this.goingDown = new LinkedList<>();
  }

  // Add a passenger to the appropriate queue based on direction
  public void addPassenger(Passenger p, int direction) {
    if (direction == UP) {
      goingUp.add(p);
    } else if (direction == DOWN) {
      goingDown.add(p);
    } else {
      // Handle invalid direction
      throw new IllegalArgumentException("Invalid direction: " + direction);
    }
  }

  // Remove a passenger from the appropriate queue
  public void removePassenger(Passenger p, int direction) {
    if (direction == UP) {
      goingUp.remove();
    } else if (direction == DOWN) {
      goingDown.remove();
    } else {
      // Handle invalid direction
      throw new IllegalArgumentException("Invalid direction: " + direction);
    }
  }

  // Get the queue of passengers for a given direction
  public Queue<Passenger> getPassengerList(int direction) {
    if (direction == UP) {
      return goingUp;
    } else if (direction == DOWN) {
      return goingDown;
    } else {
      // Handle invalid direction
      throw new IllegalArgumentException("Invalid direction: " + direction);
    }
  }

  // Get the next passenger from the queue without removing them
  public Passenger getNextPassenger(int direction) {
    if (direction == UP) {
      return goingUp.peek();
    } else if (direction == DOWN) {
      return goingDown.peek();
    } else {
      // Handle invalid direction
      throw new IllegalArgumentException("Invalid direction: " + direction);
    }
  }
}
