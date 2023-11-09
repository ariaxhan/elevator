import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Floor<Passenger> {

  public int floor = 0;
  public List<Passenger> passengers; // each floor is assigned a list of passengers
  public variables v;

  // constructor
  public Floor(int floor, variables v) {
    this.floor = floor;
    this.v = v;
    if (v.getStructures().equals("linked")) {
      this.passengers = new LinkedList<Passenger>();
    } else if (v.getStructures().equals("array")) {
      this.passengers = new ArrayList<Passenger>();
    }
  }

	public void checkFloor(int currentFloor) {
		for (int i = 0; i < passengers.size(); i++) {
			// if the passenger is going in the same direction as the elevator
      Passenger passenger = passengers.get(i);
      int destination = getdestinationFloor();
			if (destination > currentFloor) {
				// add the passenger to the elevator
						passengers.add(passengers.get(i));
						// remove the passenger from the floor
						passengers.remove(passengers.get(i));
					}
				}
				// if there are passengers in the elevator that are going to the current floor, remove them
				for (int i = 0; i < passengers.size(); i++) {
					// if the passenger is going to the current floor
					if (passengers.get(i).getEndFloor() == currentFloor) {
						// remove the passenger from the elevator
						passengers.remove(passengers.get(i));
					}
				}
			}
		private int getdestinationFloor() {
    return 0;
  }
    }

  // method to add passenger to floor
  public void addPassenger(Passenger p) {
    passengers.add(p);
  }

  // method to remove passenger from floor
  public void removePassenger(Passenger p) {
    passengers.remove(p);
  }

  // method to set list of passengers on floor
  public void setPassengers(List<Passenger> passengers) {
    this.passengers = passengers;
  }

  // method to get list of passengers on floor
  public List<Passenger> getPassengers() {
    return passengers;
  }
}
