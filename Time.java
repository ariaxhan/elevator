// Time.java
import java.util.ArrayList;
import java.util.List;

public class Time {

  private List<Long> journeyTimes; // List to store journey times for all passengers
  private long totalJourneyTime; // Total time for all journeys to calculate average
  private long longestJourneyTime; // Longest journey time
  private long shortestJourneyTime; // Shortest journey time
  private long tickCount; // Track the number of ticks
  public variables v;

  public Time(variables v) {
    this.journeyTimes = new ArrayList<>();
    this.totalJourneyTime = 0;
    this.longestJourneyTime = Long.MIN_VALUE;
    this.shortestJourneyTime = Long.MAX_VALUE;
    this.tickCount = 0;
    this.v = v;
  }

  // Method to be called for every tick that happens in the simulation
  public void onTick() {
    // Increment the tick count
    tickCount++;
	// record journey times of all passengers

  }

  // Records the journey time of a passenger
	public void recordJourneyTime(long startTime, long endTime) {
		long journeyTime = endTime - startTime;
		journeyTimes.add(journeyTime);
		totalJourneyTime += journeyTime;

		if (journeyTime > longestJourneyTime) {
			longestJourneyTime = journeyTime;
		}
		if (journeyTime < shortestJourneyTime) {
			shortestJourneyTime = journeyTime;
		}
	}


  // Calculates and returns the average journey time
  public double getAverageJourneyTime() {
    return journeyTimes.isEmpty()
      ? 0
      : (double) totalJourneyTime / journeyTimes.size();
  }

  // Returns the longest journey time
  public long getLongestJourneyTime() {
    return longestJourneyTime == Long.MIN_VALUE ? 0 : longestJourneyTime;
  }

  // Returns the shortest journey time
  public long getShortestJourneyTime() {
    return shortestJourneyTime == Long.MAX_VALUE ? 0 : shortestJourneyTime;
  }

  // Getter for tickCount
	public long getTickCount() {
		return tickCount;
	}
  
	public long getTime() {
		// get current time in milliseconds
		long time = System.currentTimeMillis();
		return time;
	}
  
  // function to print times
  public void print() {
    // print average journey time
    System.out.println(
      "Average journey time: " + this.getAverageJourneyTime() + " seconds"
    );
    // print longest journey time
    System.out.println(
      "Longest journey time: " + this.getLongestJourneyTime() + " seconds"
    );
    // print shortest journey time
    System.out.println(
      "Shortest journey time: " + this.getShortestJourneyTime() + " seconds"
    );
  }
}
