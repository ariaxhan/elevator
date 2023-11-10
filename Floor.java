import java.util.LinkedList;
import java.util.Queue;

public class Floor {

  public int floor = 0;
  public Queue<Passenger> upPassengers;
  public Queue<Passenger> downPassengers;
  public variables v;
  public Elevator elevator;

  // constructor
  public Floor(int floor, variables v) {
    // set number of floor
    this.floor = floor;
    // set variables
    this.v = v;
    // create queues of passengers
    this.upPassengers = new LinkedList<Passenger>();
    this.downPassengers = new LinkedList<Passenger>();
  }

  public void checkFloor(int currentFloor, Elevator elevator) {
    for (int i = 0; i < passengerList.size(); i++) {
      // if the passenger is going in the same direction as the elevator
      Passenger passenger = passengerList.get(i);
      int destination = passenger.getdestinationFloor();

      if (destination > currentFloor) {
        // add the passenger to the elevator
        this.upPassengers.add(passenger);
      }
      // if the passenger is going to the current floor
      else if (destination == currentFloor) {
        // remove the passenger from the elevator
        this.downPassengers.remove(passenger);
      }
    }
  }

  // method to add passenger to floor
  public void addPassenger(Passenger p, Elevator e) {
    passengerList.add(p);
  }

  // method to remove passenger from floor
  public void removePassenger(Passenger p, Elevator e) {
    passengerList.remove(p);
  }

  // method to get list of passengers on floor
  public Queue<Passenger> getPassengers() {
    return passengerList;
  }
}
