package day17;

import java.util.Objects;

class CubePosition {
    final int x;
    final int y;
    final int z;
    final int w;

    CubePosition(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CubePosition that = (CubePosition) o;
        return x == that.x && y == that.y && z == that.z && w == that.w;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, w);
    }
}
