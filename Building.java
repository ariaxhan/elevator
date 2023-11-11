import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Building {

  // building has floors and elevators
  // each floor has two queues of passengers (up and down)
  // each elevator has a list of passengers
  // the passengers for both are self-contained in the Floor and Elevator classes
  // all of the elevators and floors are in the same building
  // the building class generates the floors and the elevators
  // the simulation adds passengers to the floors, moves the elevators,
  // and adds or removes to the elevators and floors as appropriate

  public variables v;
  public List<Floor> floors;
  public List<Elevator> elevators;

  // constructor for building
  public Building(variables v) {
    // set variables
    this.v = v;
    // set type of lists for floors and elevators
    if (v.getStructures().equals("linked")) {
      this.floors = new LinkedList<>();
      this.elevators = new LinkedList<>();
    } else if (v.getStructures().equals("array")) {
      this.floors = new ArrayList<>();
      this.elevators = new ArrayList<>();
    }
    // generate floors
    this.generateFloors();
    // generate elevators
    this.generateELevators();

    // print successful generation
    System.out.println(
      "Building generated with " +
      floors.size() +
      " floors and " +
      elevators.size() +
      " elevators"
    );
  }

  public void generateFloors() {
    // Initialize each floor of the building
    for (int j = 1; j <= v.floors; j++) {
      Floor floor = new Floor(j, v);
      this.floors.add(floor); // Add new floors for each floor in the building
    }
  }

  public void generateELevators() {
    // Initialize each elevator of the building
    for (int j = 1; j <= v.elevators; j++) {
      Elevator elevator = new Elevator(v);
      elevator.id = j;
      this.elevators.add(elevator); // Add new elevators for each elevator in the building
    }
  }

  // get floor and elevator at index
  public Floor getFloor(int floor) {
    return this.floors.get(floor);
  }

  public Elevator getElevators(int elevator) {
    return this.elevators.get(elevator);
  }
}
