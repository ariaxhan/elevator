import java.util.Random;

public class Passenger {

  private int startFloor;
  private int destinationFloor;

  public Passenger(variables v) {
    generate(v);
  }

  public Passenger(int startFloor, int destinationFloor) {
    this.startFloor = startFloor;
    this.destinationFloor = destinationFloor;
  }

  public Passenger() {
    this.startFloor = 0;
    this.destinationFloor = 0;
  }

  // function to generate a passenger
  public void generate(variables v) {
    // create random object
    Random rand = new Random();
    // randomly generate a number between 0 and 1
    if (rand.nextDouble(0, 1) < v.passengers) {
      // generate int as the floor
      this.startFloor = rand.nextInt(v.floors);
      // make sure start and end floors are not the same
      while (this.destinationFloor == this.startFloor) {
        this.destinationFloor = rand.nextInt(v.floors) + 1;
      }
    } else {
      // if no passenger was generated, set start and end floor to 0
      this.startFloor = 0;
      this.destinationFloor = 0;
    }
  }

  // getters
  public int getStartFloor() {
    return startFloor;
  }

  public int getdestinationFloor() {
    return destinationFloor;
  }

}
