import java.util.LinkedList;
import java.util.Queue;

public class Floor {

  public int floor = 0;
  // queue of passengers going up
  public Queue<Passenger> goingup;
  // queue of passengers going down
  public Queue<Passenger> goingdown;
  public variables v;

  // constructor
  public Floor(int floor, variables v) {
    // set variables
    this.v = v;
    // set number of floor
    this.floor = floor;
    // create queues
    this.goingup = new LinkedList<>();
    this.goingdown = new LinkedList<>();
  }

  // method to add passenger to floor given direction
  public void addPassenger(Passenger p, int direction) {
    if (direction == 1) {
      this.goingup.add(p);
    } else if (direction == -1) {
      this.goingdown.add(p);
    }
  }

  // method to remove passenger from floor given direction
  public void removePassenger(Passenger p, int direction) {
    if (direction == 1) {
      this.goingup.remove(p);
    } else if (direction == -1) {
      this.goingdown.remove(p);
    }
  }

  // method to get passenger queue
  public Queue<Passenger> getPassengerList(int direction) {
    if (direction == 1) {
      return this.goingup;
    } else if (direction == -1) {
      return this.goingdown;
    } else {
      return null;
    }
  }

  // method to get next passenger in the queue
  public Passenger getNextPassenger(int direction) {
    if (direction == 1) {
      return this.goingup.peek();
    } else if (direction == -1) {
      return this.goingdown.peek();
    } else {
      return null;
    }
  }
}
