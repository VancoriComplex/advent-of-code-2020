package day12;

class Waypoint extends SeaObject {

    Waypoint() {
        super(10, 1);
    }

    @Override
    int getHorizontalPosition() {
        return super.getDirectionHorizontal();
    }

    @Override
    void setHorizontalPosition(int horizontalPosition) {
        super.setDirectionHorizontal(horizontalPosition);
    }

    @Override
    int getVerticalPosition() {
        return super.getDirectionVertical();
    }

    @Override
    void setVerticalPosition(int verticalPosition) {
        super.setDirectionVertical(verticalPosition);
    }
}
