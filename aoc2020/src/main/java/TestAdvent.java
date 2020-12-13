import day12.Day12;
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

        List<String> test = Arrays.asList("F10",
                "N3",
                "F7",
                "R90",
                "F11");
        int result = Day12.part1(input);
        out.println(result);
    }
}
