package day11;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Seat extends AreaPosition {

    private static final Map<Character, Boolean> stateOccupiedMap = new HashMap<>();
    private static final Map<Character, Character> stateSwitchMap = new HashMap<>();

    static {
        stateOccupiedMap.put('L', false);
        stateOccupiedMap.put('#', true);
        stateSwitchMap.put('L', '#');
        stateSwitchMap.put('#', 'L');
    }

    Seat(char state, int x, int y) {
        super(state, x, y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getState(), super.x, super.y);
    }

    @Override
    public boolean equals(Object obj) {
        assert obj instanceof Seat;
        return ((Seat) obj).getState() == super.getState()
                && ((Seat) obj).x == super.x
                && ((Seat) obj).y == super.y;
    }

    boolean isOccupied() {
        return stateOccupiedMap.get(super.getState());
    }

    void switchState() {
        super.setState(stateSwitchMap.get(super.getState()));
    }
}
