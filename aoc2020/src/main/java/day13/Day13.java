package day13;

import java.util.ArrayList;
import java.util.List;

public class Day13 {

    public static long part1(List<String> input) {
        long earliestTimestamp = Long.parseLong(input.get(0));
        List<Long> buses = new ArrayList<>();
        for (String departure : input.get(1).split(",")) {
            if (departure.equals("x"))
                continue;
            buses.add(Long.parseLong(departure));
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


}
