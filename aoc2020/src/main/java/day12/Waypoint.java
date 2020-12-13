package day12;

class Waypoint implements Point {

    private int horizontal;
    private int vertical;

    Waypoint() {
        this(10, 1);
    }

    @Override
    public void rotate(int rotationFactor, int rotationDirection) {
        int horizontalTemp = horizontal;
        int verticalTemp = vertical;
        for (int i = 0; i < rotationFactor; i++) {
            horizontal = verticalTemp*rotationDirection;
            vertical = horizontalTemp*rotationDirection*-1;
            horizontalTemp = horizontal;
            verticalTemp = vertical;
        }
    }

    private Waypoint(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    int getHorizontal() {
        return horizontal;
    }

    void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    int getVertical() {
        return vertical;
    }

    void setVertical(int vertical) {
        this.vertical = vertical;
    }
}
