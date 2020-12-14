package day13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        // this works well only with small inputs ;_;
        Map<Integer, Long> buses = new HashMap<>();
        String[] schedule = input.get(1).split(",");

        long earliestPossible = 0;
        long latestBus = 0;
        for (int i = 0; i < schedule.length; i++) {
            if (schedule[i].equals("x"))
                continue;
            buses.put(i, Long.parseLong(schedule[i]));
            latestBus = Math.max(buses.get(i), latestBus);
            earliestPossible = Math.max(buses.get(i) - i, earliestPossible);
        }

        while (true) {
            int busMatchCounter = 0;
            for (Map.Entry<Integer, Long> busId : buses.entrySet()) {
                if ((earliestPossible+busId.getKey())%busId.getValue() != 0)
                    break;
                busMatchCounter++;
            }
            if (busMatchCounter == buses.size())
                return earliestPossible;
            earliestPossible += latestBus;
        }
    }
}
