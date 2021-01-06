package day20;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class PictureBuilder {

    private final Set<Fragment> seen = new HashSet<>();
    private final FragmentsAnalyzer fragmentsAnalyzer;

    PictureBuilder(FragmentsAnalyzer fragmentsAnalyzer) {
        this.fragmentsAnalyzer = fragmentsAnalyzer;
    }

    String[][] buildPicture() {
        Fragment[][] fragments = arrangeFragments();
        return getPicture(fragments);
    }

    private Fragment[][] arrangeFragments() {
        int size = (int) Math.sqrt(fragmentsAnalyzer.getMatchingFragmentsMap().size());
        Fragment nextFragment = fragmentsAnalyzer.getCornerFragments().keySet().iterator().next();
        String[] relativeTopBorder = new String[] {};
        String[] relativeLeftBorder = new String[] {};
        Fragment[][] picture = new Fragment[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (row > 0)
                    relativeTopBorder = picture[row - 1][col].getBottomBorder();
                if (col > 0) {
                    relativeLeftBorder = picture[row][col - 1].getRightBorder();
                }
                picture[row][col] = nextFragment;
                seen.add(nextFragment);
                Iterator<Fragment> fragmentIterator = fragmentsAnalyzer.getMatchingFragmentsMap().get(picture[row][col]).iterator();
                if (row == 0 && col == 0) {
                    adjustPrimaryFragmentOrientation(picture[row][col]);
                    while (seen.contains(nextFragment))
                        do
                            nextFragment = fragmentIterator.next();
                        while (!fragmentsAnalyzer.borderHasAnyMatch(picture[row][col].getRightBorder(), nextFragment));
                } else if (row == 0) {
                    adjustFirstRowFragmentOrientation(picture[row][col], relativeLeftBorder);
                    if (col == size - 1) {
                        fragmentIterator = fragmentsAnalyzer.getMatchingFragmentsMap().get(picture[row][0]).iterator();
                        while (seen.contains(nextFragment))
                            do
                                nextFragment = fragmentIterator.next();
                            while (!fragmentsAnalyzer.borderHasAnyMatch(picture[row][0].getBottomBorder(), nextFragment));
                    } else {
                        while (seen.contains(nextFragment))
                            do
                                nextFragment = fragmentIterator.next();
                            while (!fragmentsAnalyzer.borderHasAnyMatch(picture[row][col].getRightBorder(), nextFragment));
                    }
                } else if (col == 0) {
                    adjustFirstColFragmentOrientation(picture[row][col], relativeTopBorder);
                    while (seen.contains(nextFragment))
                        do
                            nextFragment = fragmentIterator.next();
                        while (!fragmentsAnalyzer.borderHasAnyMatch(picture[row][col].getRightBorder(), nextFragment));
                } else {
                    adjustInnerFragmentOrientation(picture[row][col], relativeLeftBorder, relativeTopBorder);
                    if (col != size - 1) {
                        while (seen.contains(nextFragment))
                            do
                                nextFragment = fragmentIterator.next();
                            while (!fragmentsAnalyzer.borderHasAnyMatch(picture[row][col].getRightBorder(), nextFragment));

                    }
                    if (col == size - 1 && row < size - 1) {
                        fragmentIterator = fragmentsAnalyzer.getMatchingFragmentsMap().get(picture[row][0]).iterator();
                        while (seen.contains(nextFragment))
                            do
                                nextFragment = fragmentIterator.next();
                            while (!fragmentsAnalyzer.borderHasAnyMatch(picture[row][0].getBottomBorder(), nextFragment));
                    }
                }
            }
        }
        return picture;
    }

    private void adjustPrimaryFragmentOrientation(Fragment fragment) {
        Set<Fragment> neighbors = fragmentsAnalyzer.getCornerFragments().get(fragment);
        while (borderHasAnyMatch(fragment.getTopBorder(), neighbors))
            fragment.flipVertical();
        while (borderHasAnyMatch(fragment.getLeftBorder(), neighbors))
            fragment.flipHorizontal();
    }

    private void adjustFirstRowFragmentOrientation(Fragment fragment, String[] relativeLeftBorder) {
        boolean flipped = false;
        int flipCount = 0;
        while (!fragmentsAnalyzer.bordersMatch(relativeLeftBorder, fragment.getLeftBorder())) {
            if (!flipped) {
                fragment.flipVertical();
                flipped = true;
            } else {
                fragment.flipHorizontal();
                flipped = false;
            }
            flipCount++;
            if (flipCount == 3)
                fragment.rotate();
        }
    }

    private void adjustFirstColFragmentOrientation(Fragment fragment, String[] relativeTopBorder) {
        boolean flipped = false;
        int flipCount = 0;
        while (!fragmentsAnalyzer.bordersMatch(relativeTopBorder, fragment.getTopBorder())) {
            if (!flipped) {
                fragment.flipVertical();
                flipped = true;
            } else {
                fragment.flipHorizontal();
                flipped = false;
            }
            flipCount++;
            if (flipCount == 3)
                fragment.rotate();
        }
    }

    private void adjustInnerFragmentOrientation(Fragment fragment, String[] relativeLeftBorder, String[] relativeTopBorder) {
        boolean flipped = false;
        int flipCount = 0;
        while (!fragmentsAnalyzer.bordersMatch(relativeLeftBorder, fragment.getLeftBorder())
                && !fragmentsAnalyzer.bordersMatch(relativeTopBorder, fragment.getTopBorder())) {
            if (!flipped) {
                fragment.flipVertical();
                flipped = true;
            } else {
                fragment.flipHorizontal();
                flipped = false;
            }
            flipCount++;
            if (flipCount == 3)
                fragment.rotate();
        }
    }

    private boolean borderHasAnyMatch(final String[] border, final Set<Fragment> neighbors) {
        for (Fragment neighbor : neighbors)
            if (fragmentsAnalyzer.borderHasAnyMatch(border, neighbor))
                return true;
        return false;
    }

    private String[][] getPicture(Fragment[][] fragments) {
        int size = fragments.length;
        Fragment[][] fragmentsCropped = new Fragment[size][size];

        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++)
                fragmentsCropped[row][col] = deleteBorders(fragments[row][col].getImage());

        int croppedSize = fragmentsCropped[0][0].getImage().length;
        int picSize = size*croppedSize;
        String[][] picture = new String[picSize][picSize];
        int rowIndex;
        int colIndex;
        int colMemory;
        int rowMemory = 0;

        for (Fragment[] row : fragmentsCropped) {
            colMemory = 0;
            for (Fragment fragment : row) {
                rowIndex = rowMemory;
                for (String[] line : fragment.getImage()) {
                    colIndex = colMemory;
                    for (String letter : line) {
                        picture[rowIndex][colIndex] = letter;
                        colIndex++;
                    }
                    rowIndex++;
                }
                colMemory += croppedSize;
            }
            rowMemory += croppedSize;
        }
        return picture;
    }

    private Fragment deleteBorders(final String[][] image) {
        int size = image.length - 2;
        String[][] imageWithoutBorders = new String[size][size];
        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++)
                imageWithoutBorders[row][col] = image[row + 1][col + 1];
        Fragment result = new Fragment();
        result.setImage(imageWithoutBorders);
        return result;
    }
}
