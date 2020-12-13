package day12;

abstract class SeaObject {

    private int horizontalPosition;
    private int verticalPosition;
    private int directionHorizontal;
    private int directionVertical;


    SeaObject(int directionHorizontal, int directionVertical) {
        this.directionHorizontal = directionHorizontal;
        this.directionVertical = directionVertical;
    }

    SeaObject(int horizontalPosition, int verticalPosition, int directionHorizontal, int directionVertical) {
        this.horizontalPosition = horizontalPosition;
        this.verticalPosition = verticalPosition;
        this.directionHorizontal = directionHorizontal;
        this.directionVertical = directionVertical;
    }

    void rotate(int rotationFactor, int rotationDirection) {
        int directionHorizontalTemp = directionHorizontal;
        int directionVerticalTemp = directionVertical;
        for (int i = 0; i < rotationFactor; i++) {
            directionHorizontal = directionVerticalTemp*rotationDirection;
            directionVertical = directionHorizontalTemp*rotationDirection*-1;
            directionHorizontalTemp = directionHorizontal;
            directionVerticalTemp = directionVertical;
        }
    }

    int getHorizontalPosition() {
        return horizontalPosition;
    }

    void setHorizontalPosition(int horizontalPosition) {
        this.horizontalPosition = horizontalPosition;
    }

    int getVerticalPosition() {
        return verticalPosition;
    }

    void setVerticalPosition(int verticalPosition) {
        this.verticalPosition = verticalPosition;
    }

    int getDirectionHorizontal() {
        return directionHorizontal;
    }

    void setDirectionHorizontal(int directionHorizontal) {
        this.directionHorizontal = directionHorizontal;
    }

    int getDirectionVertical() {
        return directionVertical;
    }

    void setDirectionVertical(int directionVertical) {
        this.directionVertical = directionVertical;
    }
}
