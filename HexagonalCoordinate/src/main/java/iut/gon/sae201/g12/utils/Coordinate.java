package iut.gon.sae201.g12.utils;

import java.util.List;
import java.util.Objects;

public abstract class Coordinate {
    public abstract Point to2DCoordinate();

    public Coordinate toDir(Mode mode, Direction direction) {
        return switch (direction) {
            case NO -> this.NO(mode);
            case N -> this.N(mode);
            case NE -> this.NE(mode);
            case E -> this.E(mode);
            case SE -> this.SE(mode);
            case S -> this.S(mode);
            case SO -> this.SO(mode);
            case O -> this.O(mode);
        };
    }

    public List<Coordinate> getNeighbors(Mode mode) {

        List<Coordinate> ListCo;

        if (mode.equals(Mode.FLAT)) {
            ListCo = List.of(

                    this.N(mode), this.S(mode), this.NO(mode), this.NE(mode), this.SE(mode), this.SO(mode)

            );
        }else {

            ListCo = List.of(

                    this.E(mode), this.O(mode), this.NO(mode), this.NE(mode), this.SE(mode), this.SO(mode)

            );

        }

        return ListCo;


    }

    public abstract List<Coordinate> between(Mode mode, Coordinate to);

    public abstract Coordinate NO(Mode mode);

    public abstract Coordinate NE(Mode mode);

    public abstract Coordinate E(Mode mode);

    public abstract Coordinate O(Mode mode);

    public abstract Coordinate N(Mode mode);

    public abstract Coordinate S(Mode mode);

    public abstract Coordinate SO(Mode mode);

    public abstract Coordinate SE(Mode mode);

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Coordinate co && this.to2DCoordinate().equals(co.to2DCoordinate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(to2DCoordinate());
    }
}
