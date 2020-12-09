import day9.Day9;
import util.HttpsReader;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

public class TestAdvent {
    public static void main(String[] args) {
        List<Long> input = null;
        try {
            input = HttpsReader.getContentAsLong(new URL("someUrl"), "session=Key");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert input != null;
        List<Long> test = Arrays.asList(35L,
                20L,
                15L,
                25L,
                47L,
                40L,
                62L,
                55L,
                65L,
                95L,
                102L,
                117L,
                150L,
                182L,
                127L,
                219L,
                299L,
                277L,
                309L,
                576L);
        long result = Day9.Part2.getConsecutiveMinMaxSum(input, Day9.Part1.getFirstInvalidNumber(input, 25));
        out.println(result);
    }
}
