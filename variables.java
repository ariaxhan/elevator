public class variables {

  // input variables
  String structures;
  int floors;
  double passengers;
  int elevators;
  int elevatorCapacity;
  int duration;

  // constructor
  public variables() {}

  public void setDefault() {
    this.structures = "linked";
    this.floors = 32;
    this.passengers = 0.03;
    this.elevators = 1;
    this.elevatorCapacity = 10;
    this.duration = 500;
  }

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
}
