package iut.gon.sae201.g12.utils;

import iut.gon.sae201.g12.exceptions.DifferentAxisExeption;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class CoordinateDoubled extends Coordinate {
    private int x;
    private int y;

    @Override
    public Point to2DCoordinate() {

        return new Point(this.x, this.y);

    }

    @Override
    public List<Coordinate> between(Mode mode, Coordinate to) {

        if (to instanceof CoordinateDoubled toCube) {

            int disX = this.x - toCube.getX();
            int disY = this.y - toCube.getY();

            if (this.x != toCube.getX() && (Math.abs(disX) != Math.abs(disY))) {

                throw new DifferentAxisExeption("Aucun axe en commun");
            }
            List<Coordinate> ListCo = new ArrayList<>();

            if (disX == 0) {
                for (int i = 1; i < Math.abs(disY); i = i + 2) {
                    if (disY < 0) {
                        ListCo.add(new CoordinateDoubled(this.x, this.y - i));
                    } else {
                        ListCo.add(new CoordinateDoubled(this.x, this.y + i));
                    }

                }
            } else {

                for (int i = 1; i < Math.abs(disY); i++) {
                    ListCo.add(new CoordinateDoubled(this.x + (i * (disX / Math.abs(disX))), this.y + (i * (disY / Math.abs(disY)))));
                }

            }
            return ListCo;
        }
        return List.of();
    }


    @Override
    public Coordinate NO(Mode mode) {

        if (mode.equals(Mode.FLAT)) {


            return this.O(mode);

        } else {


            return new CoordinateDoubled(this.x-1, this.y-1);

        }

    }

    @Override
    public Coordinate NE(Mode mode) {


        return new CoordinateDoubled(this.x - 1, this.y + 1);
    }

    @Override
    public Coordinate E(Mode mode) {

        if (mode.equals(Mode.FLAT)) throw new InvalidParameterException("Tu ne peux pas aller a l'est");


        return new CoordinateDoubled(this.x + 2, this.y);
    }

    @Override
    public Coordinate O(Mode mode) {

        if (mode.equals(Mode.FLAT)) throw new InvalidParameterException("Tu ne peux pas aller a l'ouest");


        return new CoordinateDoubled(this.x - 2, this.y);

    }

    @Override
    public Coordinate N(Mode mode) {
        if (mode.equals(Mode.POINTY)) throw new InvalidParameterException("Tu ne peux pas aller au nord");


        return this.NO(mode);
    }

    @Override
    public Coordinate S(Mode mode) {
        if (mode.equals(Mode.POINTY)) throw new InvalidParameterException("Tu ne peux pas aller au sud");


        return this.SE(mode);
    }

    @Override
    public Coordinate SO(Mode mode) {

        return new CoordinateDoubled(this.x + 1, this.y - 1);

    }

    @Override
    public Coordinate SE(Mode mode) {
        if (mode.equals(Mode.FLAT)) {


            return this.E(mode);

        } else {


            return new CoordinateDoubled(this.x+1, this.y+1 );

        }
    }

}
