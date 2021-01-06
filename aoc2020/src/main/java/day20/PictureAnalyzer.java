package day20;

import java.util.HashSet;
import java.util.Set;

class PictureAnalyzer {

    private final String[][] picture;
    private final Set<Set<int[]>> seaMonsters = new HashSet<>();

    PictureAnalyzer(String[][] picture) {
        this.picture = picture;
    }

    public int getHabitatsWaterRoughness(String[][] seaMonster) {
        Fragment pic = new Fragment();
        pic.setImage(this.picture);
        int seaMonsterCount = countSeaMonsters(pic, seaMonster);

        boolean flipped = false;
        int flipCount = 0;

        while (seaMonsterCount == 0) {
            if (!flipped) {
                pic.flipHorizontal();
                flipped = true;
            } else {
                pic.flipVertical();
                flipped = false;
            }
            flipCount++;
            if (flipCount == 3)
                pic.rotate();
            seaMonsterCount = countSeaMonsters(pic, seaMonster);
        }
        return getWaterRoughness(pic.getImage());
    }

    private int countSeaMonsters(Fragment pic, String[][] seaMonster) {
        String[][] picture = pic.getImage();
        int seaMonsterCount = 0;
        Set<int[]> seaMonsterCoordinates = getSeaMonsterCoordinates(seaMonster);
        int seaMonsterLength = seaMonster[0].length;
        int seaMonsterHeight = seaMonster.length;

        for (int y = 0; y < picture.length - seaMonsterHeight; y++) {
            for (int x = 0; x < picture.length - seaMonsterLength; x++) {
                Set<int[]> potentialSeaMonster = new HashSet<>();
                for (int[] coordinate : seaMonsterCoordinates) {
                    if (picture[y + coordinate[0]][x + coordinate[1]].equals("#"))
                        potentialSeaMonster.add(new int[]{y + coordinate[0], x + coordinate[1]});
                    else
                        break;
                }
                if (potentialSeaMonster.size() == seaMonsterCoordinates.size()) {
                    seaMonsters.add(potentialSeaMonster);
                    seaMonsterCount++;
                }
            }
        }
        return seaMonsterCount;
    }

    private static Set<int[]> getSeaMonsterCoordinates(String[][] seaMonster) {
        Set<int[]> result = new HashSet<>();
        for (int y = 0; y < seaMonster.length; y++) {
            for (int x = 0; x < seaMonster[y].length; x++) {
                if (seaMonster[y][x].equals("#"))
                    result.add(new int[] {y, x});
            }
        }
        return result;
    }

    private int getWaterRoughness(String[][] picture) {
        int roughnessCount = 0;
        for (int y = 0; y < picture.length; y++) {
            for (int x = 0; x < picture.length; x++) {
                if (picture[y][x].equals("#")) {
                    int[] coordinates = new int[] {y,x};
                    if (seaMonsters.stream()
                            .noneMatch(
                            seaMonster -> seaMonster.stream()
                                    .anyMatch(
                                    monsterCoordinate -> (monsterCoordinate[0] == coordinates[0]
                                            && monsterCoordinate[1] == coordinates[1]))))
                    {
                        roughnessCount++;
                    }
                }
            }
        }
        return roughnessCount;
    }
}
