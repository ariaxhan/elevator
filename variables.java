public class variables {

  // input variables
  String structures;
  int floors;
  double passengers;
  int elevators;
  int elevatorCapacity;
  int duration;

  // constructor
  public variables(){};

  // getters
  public String getStructures() {
    return structures;
  }

  public int getFloors() {
    return floors;
  }

  public double getPassengers() {
    return passengers;
  }

  public int getElevators() {
    return elevators;
  }

  public int getElevatorCapacity() {
    return elevatorCapacity;
  }

  public int getDuration() {
      return duration;
  }
  
  // setters including validation, invalid values will be replaced with default values
  public void setStructures(String structures) {
      if (structures.equals("linked")) {
          this.structures = structures;
      } else if (structures.equals("array")) {
          this.structures = structures;
      } else {
          this.structures = "linked";
      }
  }
    
  public void setFloors(int floors) {
      if (floors >= 2) {
          this.floors = floors;
      } else {
          this.floors = 32;
      }
  }
    
  public void setPassengers(double passengers) {
      if (passengers >= 0 && passengers <= 1.0) {
          this.passengers = passengers;
      } else {
          this.passengers = 0.03;
      }
  }
    
  public void setElevators(int elevators) {
      if (elevators >= 1) {
          this.elevators = elevators;
      } else {
          this.elevators = 1;
      }
  }
    
  public void setElevatorCapacity(int elevatorCapacity) {
      if (elevatorCapacity >= 1) {
          this.elevatorCapacity = elevatorCapacity;
      } else {
          this.elevatorCapacity = 10;
      }
  }
    
  public void setDuration(int duration) {
      if (duration >= 1) {
          this.duration = duration;
      } else {
          this.duration = 500;
      }
  }
    
  // print function to check input
    public void print() {
        System.out.println("structures: " + structures + "\n");
        System.out.println("floors: " + floors + "\n");
        System.out.println("passengers: " + passengers + "\n");
        System.out.println("elevators: " + elevators + "\n");
        System.out.println("elevatorCapacity: " + elevatorCapacity + "\n");
        System.out.println("duration: " + duration + "\n");
    }
}
