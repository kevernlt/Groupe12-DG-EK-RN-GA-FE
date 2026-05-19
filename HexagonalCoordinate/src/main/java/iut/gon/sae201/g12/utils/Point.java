package iut.gon.sae201.g12.utils;

public record Point(int x, int y) {
    @Override
    public String toString() {
        return "Point[X=" + x + ", Y=" + y + "]";
    }
}
