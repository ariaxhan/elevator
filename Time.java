import java.time.LocalTime;

public class Time {

  // variables
  public int currentTime = 0;

  // constructor
  public Time() {
    this.currentTime = 0;
  }

  public void getCurrentTime() {
    LocalTime time = LocalTime.now();
    System.out.println("Current time: " + time);
  }
}
