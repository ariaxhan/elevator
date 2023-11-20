import java.io.FileReader;
import java.util.Properties;

public class ElevatorMain {

  public static void main(String[] args) {
    // create variables object
    variables v = new variables();
    // read in properties file
    String filename = args[0];
    readFile(filename, v);
    // create building object
    Building b = new Building(v);
    // run simulation
    Simulation s = new Simulation(v, b);
    s.runSimulation(v);
    // print times
    s.time.print();
  }

  // function to read in properties file
  public static variables readFile(String fileName, variables v) {
    try {
      // create reader object
      FileReader reader = new FileReader("test.properties");
      // create properties object
      Properties p = new Properties();
      // add a wrapper around reader object
      p.load(reader);
      // access data and fill in variables object
      v.setStructures(p.getProperty("structures"));
      v.setFloors(Integer.parseInt(p.getProperty("floors")));
      v.setPassengers(Double.parseDouble(p.getProperty("passengers")));
      v.setElevators(Integer.parseInt(p.getProperty("elevators")));
      v.setElevatorCapacity(
        Integer.parseInt(p.getProperty("elevatorCapacity"))
      );
      v.setDuration(Integer.parseInt(p.getProperty("duration")));

      // close reader
      reader.close();
    } catch (Exception e) {
      // if the file is not found, fill in with default values
      v.setDefault();
    }
    return v;
  }
}
