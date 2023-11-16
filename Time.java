// Time.java
import java.util.ArrayList;
import java.util.List;

public class Time {
    private List<Long> journeyTimes; // Store journey times in ticks
    private long totalJourneyTicks; // Total ticks for all journeys to calculate average
    private long longestJourneyTicks; // Longest journey in ticks
    private long shortestJourneyTicks; // Shortest journey in ticks
    private long tickCount; // Current tick count in the simulation

    public Time() {
        this.journeyTimes = new ArrayList<>();
        this.totalJourneyTicks = 0;
        this.longestJourneyTicks = 0;
        this.shortestJourneyTicks = Long.MAX_VALUE;
        this.tickCount = 0;
    }

    // Called for every tick in the simulation
    public void onTick() {
        tickCount++;
    }

    // Record a journey based on start and end ticks
    public void recordJourneyTime(long startTick, long endTick) {
        long journeyTicks = endTick - startTick;
        journeyTimes.add(journeyTicks);
        totalJourneyTicks += journeyTicks;

        if (journeyTicks > longestJourneyTicks) {
            longestJourneyTicks = journeyTicks;
        }
        if (journeyTicks < shortestJourneyTicks) {
            shortestJourneyTicks = journeyTicks;
        }
    }

    // Get average journey time in ticks
    public double getAverageJourneyTime() {
        if (journeyTimes.isEmpty()) {
            return 0.0;
        }
        return (double) totalJourneyTicks / journeyTimes.size();
    }

    // Get the longest journey time in ticks
    public long getLongestJourneyTime() {
        return longestJourneyTicks;
    }

    // Get the shortest journey time in ticks
    public long getShortestJourneyTime() {
        return shortestJourneyTicks == Long.MAX_VALUE ? 0 : shortestJourneyTicks;
    }

    public long getTickCount() {
        return tickCount;
    }

    // Print journey times in ticks
    public void print() {
        System.out.println("Average journey time: " + getAverageJourneyTime() + " ticks");
        System.out.println("Longest journey time: " + getLongestJourneyTime() + " ticks");
        System.out.println("Shortest journey time: " + getShortestJourneyTime() + " ticks");
    }
}
