import day12.Day12;
import day13.Day13;
import util.HttpsReader;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

public class TestAdvent {
    public static void main(String[] args) {
        List<String> input = null;
        try {
            input = HttpsReader.getContent(new URL("someUrl"), "session=Key");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert input != null;
        List<String> testSeat = Arrays.asList("L.LL.LL.LL",
                "LLLLLLL.LL",
                "L.L.L..L..",
                "LLLL.LL.LL",
                "L.LL.LL.LL",
                "L.LLLLL.LL",
                "..L.L.....",
                "LLLLLLLLLL",
                "L.LLLLLL.L",
                "L.LLLLL.LL");

        List<String> test13 = Arrays.asList("939",
                "7,13,x,x,59,x,31,19");
        long result = Day13.part1(input);
        out.println(result);
    }
}
