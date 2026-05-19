package iut.gon.sae201.g12.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CoordinateDoubled extends Coordinate {
    private int x;
    private int y;

    @Override
    public Point to2DCoordinate() {
        return null;
    }

    @Override
    public Coordinate toDir(Mode mode, Direction direction) {
        return null;
    }

    @Override
    public List<Coordinate> getNeighbors(Mode mode) {
        return List.of();
    }

    @Override
    public List<Coordinate> between(Mode mode, Coordinate to) {
        return List.of();
    }

    @Override
    public Coordinate NO(Mode mode) {
        return null;
    }

    @Override
    public Coordinate NE(Mode mode) {
        return null;
    }

    @Override
    public Coordinate E(Mode mode) {
        return null;
    }

    @Override
    public Coordinate O(Mode mode) {
        return null;
    }

    @Override
    public Coordinate N(Mode mode) {
        return null;
    }

    @Override
    public Coordinate S(Mode mode) {
        return null;
    }

    @Override
    public Coordinate SO(Mode mode) {
        return null;
    }
}
