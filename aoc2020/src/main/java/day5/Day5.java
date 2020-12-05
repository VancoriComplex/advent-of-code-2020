package day5;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day5 {

    public static class Part1 {

        public static int getHighestBoardingPassId(List<String> boardingPasses) {
            int maxId = 0;
            for (String boardingPass : boardingPasses) {
                int id = getId(boardingPass);
                maxId = Math.max(id, maxId);
            }
            return maxId;
        }

        private static int getId(String boardingPass) {
            int seatRowMin = 0;
            int seatRowMax = 127;
            int seatColMin = 0;
            int seatColMax = 7;
            int i = 0;
            int myRow = 0;
            int myCol = 0;

            while (i < boardingPass.length()) {
                switch (boardingPass.charAt(i)) {
                    case 'F':
                        seatRowMax -= (seatRowMax - seatRowMin)/2 + 1;
                        if (seatRowMax == seatRowMin)
                            myRow = seatRowMin;
                        i++;
                        break;
                    case 'B':
                        seatRowMin += (seatRowMax - seatRowMin)/2 + 1;
                        if (seatRowMax == seatRowMin)
                            myRow = seatRowMax;
                        i++;
                        break;
                    case 'R':
                        seatColMin += (seatColMax - seatColMin)/2 + 1;
                        if (seatColMax == seatColMin)
                            myCol = seatColMax;
                        i++;
                        break;
                    case 'L':
                        seatColMax -= (seatColMax - seatColMin)/2 + 1;
                        if (seatColMax == seatColMin)
                            myCol = seatColMin;
                        i++;
                        break;
                }
            }
            return myRow*8 + myCol;
        }
    }

    public static class Part2 {

        public static int getMyBoardingPassId(List<String> boardingPasses) {
            int myId = 0;
            Set<Integer> boardingIds = new HashSet<>();
            for (String boardingPass : boardingPasses) {
                boardingIds.add(Part1.getId(boardingPass));
            }
            for (Integer id : boardingIds) {
                if (boardingIds.contains(id + 2)
                        && !boardingIds.contains(id + 1))
                    myId = id + 1;
            }
            return myId;
        }
    }
}
