package day20;

import java.util.List;
import java.util.Set;

public class Day20 {

    public static long part1(List<String> input) {
        FragmentParser fragmentParser = new FragmentParser();
        Set<Fragment> fragments = fragmentParser.parseFragments(input);
        FragmentsAnalyzer fragmentsAnalyzer = new FragmentsAnalyzer(fragments);
        long result = 1;
        for (Fragment fragment : fragments) {
            fragmentsAnalyzer.analyze(fragment);
            if (fragmentsAnalyzer.getMatchingBordersCount(fragment) == 2)
                result *= fragment.getId();
        }
        return result;
    }

    public static int part2(List<String> input) {
        FragmentParser fragmentParser = new FragmentParser();
        Set<Fragment> fragments = fragmentParser.parseFragments(input);
        FragmentsAnalyzer fragmentsAnalyzer = new FragmentsAnalyzer(fragments);
        for (Fragment fragment : fragments) {
            fragmentsAnalyzer.analyze(fragment);
        }
        PictureBuilder pictureBuilder = new PictureBuilder(fragmentsAnalyzer);
        String[][] picture = pictureBuilder.buildPicture();
        PictureAnalyzer pictureAnalyzer = new PictureAnalyzer(picture);
        return pictureAnalyzer.getHabitatsWaterRoughness(getSeaMonster());
    }

    private static String[][] getSeaMonster() {
        String seaMonsta =
                "                  # \n" +
                "#    ##    ##    ###\n" +
                " #  #  #  #  #  #   ";
        String[] monstaLines = seaMonsta.split("\\n");
        String[][] seaMonster = new String[monstaLines.length][monstaLines[0].length()];
        for (int line = 0; line < monstaLines.length; line++) {
            String[] split = monstaLines[line].split("");
            for (int i = 0; i < split.length; i++) {
                seaMonster[line][i] = split[i];
            }
        }
        return seaMonster;
    }
}
