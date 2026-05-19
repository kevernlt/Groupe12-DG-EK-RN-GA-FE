package iut.gon.sae201.g12.utils;

import iut.gon.sae201.g12.exceptions.DifferentAxisExeption;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class CoordinateCube extends Coordinate {
    private int q;
    private int r;
    private int s;

    @Override
    public Point to2DCoordinate() {

        int pointX = this.r + 5;

        int pointY = (this.q + (this.s * -1)) + 9;

        return new Point(pointX, pointY);

    }

    @Override
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

    @Override
    public List<Coordinate> getNeighbors(Mode mode) {

        List<Coordinate> ListCo = List.of(

                new CoordinateCube(this.q + 1, this.r - 1, this.s), new CoordinateCube(this.q + 1, this.r, this.s - 1), new CoordinateCube(this.q - 1, this.r + 1, this.s), new CoordinateCube(this.q, this.r + 1, this.s - 1), new CoordinateCube(this.q - 1, this.r, this.s + 1), new CoordinateCube(this.q, this.r - 1, this.s + 1)

        );

        return ListCo;
    }

    @Override
    public List<Coordinate> between(Mode mode, Coordinate to) {
        if (to instanceof CoordinateCube toCube) {
            if (this.s != toCube.getS() && this.q != toCube.getQ() && this.r != toCube.getR()) {
                throw new DifferentAxisExeption("Aucun axe en commun");
            }

            int depQ = this.q - toCube.getQ();
            int depR = this.r - toCube.getR();
            int depS = this.s - toCube.getS();

            if (depQ == 0 && depR == 0 && depS == 0) {
                return new ArrayList<>();
            }

            List<Coordinate> ListCo = new ArrayList<>();

            if (depS == 0) {

                for (int i = 0; i < Math.abs(depQ); i++) {
                    if (depQ < 0) {

                        ListCo.add(new CoordinateCube(this.q - i, this.r + i, this.s));

                    } else {
                        ListCo.add(new CoordinateCube(this.q + i, this.r - i, this.s));
                    }
                }

            } else if (depR == 0) {

                for (int i = 0; i < Math.abs(depQ); i++) {

                    if (depQ < 0) {
                        ListCo.add(new CoordinateCube(this.q - i, this.r, this.s + i));
                    } else {
                        ListCo.add(new CoordinateCube(this.q + i, this.r, this.s - i));
                    }

                }

            } else {
                for (int i = 0; i < Math.abs(depR); i++) {

                    if (depR < 0) {
                        ListCo.add(new CoordinateCube(this.q, this.r - i, this.s + i));
                    } else {
                        ListCo.add(new CoordinateCube(this.q, this.r + i, this.s - i));
                    }
                }
            }

            return ListCo;
        }
        return null;
    }


    @Override
    public Coordinate NO(Mode mode) {

        if (mode.equals(Mode.FLAT)) {


            return new CoordinateCube(this.q - 1, this.r, this.s + 1);

        } else {


            return new CoordinateCube(this.q, this.r - 1, this.s + 1);

        }

    }

    @Override
    public Coordinate NE(Mode mode) {


        return new CoordinateCube(this.q + 1, this.r - 1, this.s);
    }

    @Override
    public Coordinate E(Mode mode) {

        if (mode.equals(Mode.FLAT)) throw new InvalidParameterException("Tu ne peux pas aller a l'est");


        return new CoordinateCube(this.q + 1, this.r, this.s - 1);
    }

    @Override
    public Coordinate O(Mode mode) {

        if (mode.equals(Mode.FLAT)) throw new InvalidParameterException("Tu ne peux pas aller a l'ouest");


        return new CoordinateCube(this.q - 1, this.r, this.s + 1);

    }

    @Override
    public Coordinate N(Mode mode) {
        if (mode.equals(Mode.POINTY)) throw new InvalidParameterException("Tu ne peux pas aller au nord");


        return new CoordinateCube(this.q, this.r - 1, this.s + 1);
    }

    @Override
    public Coordinate S(Mode mode) {
        if (mode.equals(Mode.POINTY)) throw new InvalidParameterException("Tu ne peux pas aller au sud");


        return new CoordinateCube(this.q, this.r + 1, this.s - 1);
    }

    @Override
    public Coordinate SO(Mode mode) {


        return new CoordinateCube(this.q - 1, this.r + 1, this.s);
    }

    @Override
    public Coordinate SE(Mode mode) {
        if (mode.equals(Mode.FLAT)) {


            return new CoordinateCube(this.q + 1, this.r, this.s - 1);

        } else {


            return new CoordinateCube(this.q, this.r + 1, this.s - 1);

        }
    }

}
