package day12;

class Ship implements Point {

    private int horizontal;
    private int vertical;
    private int directionHorizontal;
    private int directionVertical;

    Ship() {
        this(0, 0, 1, 0);
    }

    @Override
    public void rotate(int rotationFactor, int rotationDirection) {
        int directionHorizontalTemp = directionHorizontal;
        int directionVerticalTemp = directionVertical;
        for (int i = 0; i < rotationFactor; i++) {
            directionHorizontal = directionVerticalTemp*rotationDirection;
            directionVertical = directionHorizontalTemp*rotationDirection*-1;
            directionHorizontalTemp = directionHorizontal;
            directionVerticalTemp = directionVertical;
        }
    }

    private Ship(int horizontal, int vertical, int directionHorizontal, int directionVertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.directionHorizontal = directionHorizontal;
        this.directionVertical = directionVertical;
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

    int getDirectionHorizontal() {
        return directionHorizontal;
    }

    int getDirectionVertical() {
        return directionVertical;
    }
}
