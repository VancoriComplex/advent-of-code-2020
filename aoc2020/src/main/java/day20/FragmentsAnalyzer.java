package day20;

import java.util.*;

class FragmentsAnalyzer {

    private final Set<Fragment> fragments;

    private final Map<Fragment, Integer> matchingBordersMap = new HashMap<>();
    private final Map<Fragment, Set<Fragment>> matchingFragmentsMap = new HashMap<>();
    private final Map<Fragment, Set<Fragment>> cornerFragments = new HashMap<>();
    private final Map<Fragment, Set<Fragment>> sideFragments = new HashMap<>();
    private final Map<Fragment, Set<Fragment>> innerFragments = new HashMap<>();

    FragmentsAnalyzer(Set<Fragment> fragments) {
        this.fragments = fragments;
    }

    void analyze(final Fragment fragment) {
        matchingFragmentsMap.put(fragment, new HashSet<>());
        int matchingBordersCount = 0;
        for (Fragment fragmentToMatch : fragments) {
            if (fragment.equals(fragmentToMatch))
                continue;

            for (String[] border : getBorders(fragment)) {
                if (borderHasAnyMatch(border, fragmentToMatch)) {
                    matchingBordersCount++;
                    matchingFragmentsMap.get(fragment).add(fragmentToMatch);
                }
            }
        }
        matchingBordersMap.put(fragment, matchingBordersCount);

        if (matchingBordersCount == 2)
            cornerFragments.put(fragment, matchingFragmentsMap.get(fragment));
        if (matchingBordersCount == 3)
            sideFragments.put(fragment, matchingFragmentsMap.get(fragment));
        if (matchingBordersCount == 4)
            innerFragments.put(fragment, matchingFragmentsMap.get(fragment));
    }

    int getMatchingBordersCount(Fragment fragment) {
        return matchingBordersMap.get(fragment);
    }

    Map<Fragment, Set<Fragment>> getMatchingFragmentsMap() {
        return matchingFragmentsMap;
    }

    Map<Fragment, Set<Fragment>> getCornerFragments() {
        return cornerFragments;
    }

    Map<Fragment, Set<Fragment>> getSideFragments() {
        return sideFragments;
    }

    Map<Fragment, Set<Fragment>> getInnerFragments() {
        return innerFragments;
    }

    boolean borderHasAnyMatch(final String[] border, final Fragment fragment) {
        Fragment toMatch = new Fragment();
        toMatch.setImage(fragment.getImage());
        for (String[] borderToMatch : getBorders(toMatch))
            if (bordersMatch(border, borderToMatch))
                return true;
        toMatch.flipHorizontal();
        for (String[] borderToMatch : getBorders(toMatch))
            if (bordersMatch(border, borderToMatch))
                return true;
        toMatch.flipVertical();
        for (String[] borderToMatch : getBorders(toMatch))
            if (bordersMatch(border, borderToMatch))
                return true;
        return false;
    }

    boolean bordersMatch(String[] border, String[] borderToMatch) {
        for (int i = 0; i < border.length; i++) {
            if (!border[i].equals(borderToMatch[i]))
                return false;
        }
        return true;
    }

    private List<String[]> getBorders(final Fragment fragment) {
        List<String[]> borders = new ArrayList<>();
        borders.add(fragment.getTopBorder());
        borders.add(fragment.getBottomBorder());
        borders.add(fragment.getLeftBorder());
        borders.add(fragment.getRightBorder());
        return borders;
    }
}
