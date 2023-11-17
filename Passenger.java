import java.util.Random;

public class Passenger {

  public static int nextId = 1;
  public final int UP = 1;
  public final int DOWN = -1;

  public int startFloor;
  public int destinationFloor;
  public int pDirection;
  public long startTime;
  public int id;

  public Passenger(variables v) {
    this.generate(v);
    this.id = nextId;
    nextId++;
    if (this.startFloor != 0 && this.destinationFloor != 0) {
      System.out.println(
        "Passenger " +
        this.id +
        " generated on floor " +
        this.startFloor +
        " going " +
        this.getPDirection() +
        " to floor " +
        this.destinationFloor
      );
    }
  }

  // function to generate a passenger
  public void generate(variables v) {
    // create random object
    Random rand = new Random();
    // randomly generate a number between 0 and 100
    // run probability
    double probability = rand.nextDouble(0, 100) + 1;
    // if the random number is less than the probability of a passenger being generated
    // generate a passenger
    double currProbability = v.passengers * 100;
    if (probability <= currProbability) {
      // generate int as the start floor
      int startFloor = rand.nextInt(1, v.floors);
      // generate int as the destination floor
      int destinationFloor = rand.nextInt(1, v.floors);
      // make sure start and end floors are not the same
      while (startFloor == destinationFloor) {
        destinationFloor = rand.nextInt(1, v.floors);
      }
      // set start floor
      this.startFloor = startFloor;
      // set destination floor
      this.destinationFloor = destinationFloor;
      // set direction
      setPDirection(startFloor, destinationFloor);
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

  public String getPDirection() {
    if (this.pDirection == 1) {
      return "up";
    } else if (this.pDirection == -1) {
      return "down";
    } else {
      return "stationary";
    }
  }

  public int getId() {
    return this.id;
  }

  // get starttime
  public long getStartTime() {
    return this.startTime;
  }

  // setters
  public void setPDirection(int startFloor, int destinationFloor) {
    if (destinationFloor > startFloor) {
      this.pDirection = 1; // going up
    } else if (destinationFloor < startFloor) {
      this.pDirection = -1; // going down
    } else {
      this.pDirection = 0; // not moving
    }
  }
}
