import java.util.List;
import java.util.Random;

public class Passenger {

  private int startFloor;
  private int destinationFloor;

  public Passenger(variables v) {
    generate(v.getFloors(), v.getPassengers());
  }

  // function to generate a passenger
  public void generate(int floors, double passengersProbability) {
    // create random object
    Random rand = new Random();
    // loop through all floors
    for (int currentFloor = 1; currentFloor <= floors; currentFloor++) {
      // randomly generate a number between 0 and 1
      double num = rand.nextDouble();
      if (num < passengersProbability) {
        // generate int as the floor
        int endFloor = rand.nextInt(floors) + 1;
        // make sure start and end floors are not the same
        while (endFloor == currentFloor) {
          endFloor = rand.nextInt(floors) + 1;
        }
        // set start and end floor and return
        this.startFloor = currentFloor;
        this.destinationFloor = endFloor;
      }
    }
  }

  // function to add passenger to a floor
  public void addPassenger(Passenger p, List<Passenger> floor) {
    floor.add(p);
  }

  // function to remove passenger from a floor
  public void removePassenger(Passenger p, List<Passenger> floor) {
    floor.remove(p);
  }

  // getters
  public int getStartFloor() {
    return startFloor;
  }

  public int getdestinationFloor() {
    return destinationFloor;
  }
}
