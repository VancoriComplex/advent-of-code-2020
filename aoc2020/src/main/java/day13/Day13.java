package day13;

import java.util.*;

public class Day13 {

    public static long part1(List<String> input) {
        long earliestTimestamp = Long.parseLong(input.get(0));
        List<Long> buses = new ArrayList<>();
        for (String bus : input.get(1).split(",")) {
            if (bus.equals("x"))
                continue;
            buses.add(Long.parseLong(bus));
        }

        long earliestBus = 0;
        long earliestDepartureOffset = Long.MAX_VALUE;

        for (Long busId : buses) {
            long mod = earliestTimestamp%busId;
            if (busId - mod < earliestDepartureOffset) {
                earliestDepartureOffset = busId - mod;
                earliestBus = busId;
            }
        }
        return earliestBus*earliestDepartureOffset;
    }

    public static long part2(List<String> input) {
        List<long[]> buses = getBuses(input);
        long lcm = buses.get(0)[1];
        long t = 0;
        for (int i = 1; i < buses.size(); i++) {
            while (true) {
                if ((t + buses.get(i)[0])%buses.get(i)[1] == 0) {
                    lcm *= buses.get(i)[1];
                    break;
                }
                t += lcm;
            }
        }
        return t;
    }

    private static List<long[]> getBuses(List<String> input) {
        String[] schedule = input.get(1).split(",");
        List<long[]> buses = new ArrayList<>();
        for (int i = 0; i < schedule.length; i++) {
            if (schedule[i].equals("x"))
                continue;
            buses.add(new long[] {(long) i, Long.parseLong(schedule[i])});
        }
        return buses;
    }
}
