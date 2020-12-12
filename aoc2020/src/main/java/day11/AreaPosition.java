package day11;

class AreaPosition {

    private char state;
    int x;
    int y;

    AreaPosition(char state, int x, int y) {
        this.state = state;
        this.x = x;
        this.y = y;
    }

    void setState(char state) {
        this.state = state;
    }

    char getState() {
        return state;
    }

    @Override
    public String toString() {
        return String.format("%s x: %d y: %d\n", state, x, y);
    }
}
