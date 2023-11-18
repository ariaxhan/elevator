import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Building {

  public variables v;
  public List<Floor> floors;
  public List<Elevator> elevators;

  // Constructor
  public Building(variables v) {
    this.v = v;
    // Initialize floors and elevators based on v.getStructures()
    this.floors =
      (v.getStructures().equals("linked"))
        ? new LinkedList<>()
        : new ArrayList<>();
    this.elevators =
      (v.getStructures().equals("linked"))
        ? new LinkedList<>()
        : new ArrayList<>();

    // Generate floors and elevators
    generateFloors();
    generateElevators();

    System.out.println(
      "Building generated with " +
      floors.size() +
      " floors and " +
      elevators.size() +
      " elevators"
    );
  }

  private void generateFloors() {
    for (int i = 1; i <= v.getFloors(); i++) {
      floors.add(new Floor(i, v));
    }
  }

  private void generateElevators() {
    for (int i = 1; i <= v.getElevators(); i++) {
      elevators.add(new Elevator(v));
    }
  }

  // Get a floor by its number. Floor numbers start from 1.
  public Floor getFloor(int floorNumber) {
    if (floorNumber < 1 || floorNumber > floors.size()) {
      throw new IllegalArgumentException(
        "Floor number " + floorNumber + " is out of range"
      );
    }
    return floors.get(floorNumber - 1); // Adjust for zero-based indexing
  }

  // Get an elevator by its index. Indices start from 1.
  public Elevator getElevator(int elevatorIndex) {
      if (elevatorIndex < 1 || elevatorIndex > elevators.size()) {
          throw new IllegalArgumentException(
                  "Elevator index " + elevatorIndex + " is out of range");
      }
      return elevators.get(elevatorIndex - 1); // Adjust for zero-based indexing
  }
  
  // get list of flooors
  public List<Floor> getFloors() {
      return floors;
  }
    
  // get list of elevators
    public List<Elevator> getElevators() {
        return elevators;
    }
}
