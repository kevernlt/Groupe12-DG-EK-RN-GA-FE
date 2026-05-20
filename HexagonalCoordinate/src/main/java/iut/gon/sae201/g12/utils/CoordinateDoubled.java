package iut.gon.sae201.g12.utils;

import iut.gon.sae201.g12.exceptions.DifferentAxisExeption;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
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

            int disX =  toCube.getX() - this.x;
            int disY =  toCube.getY()- this.y;

            if (this.x != toCube.getX() && (Math.abs(disX) != Math.abs(disY))) {

                throw new DifferentAxisExeption("Aucun axe en commun");
            }
            List<Coordinate> ListCo = new ArrayList<>();

            if (disX == 0) {
                for (int i = 2; i < Math.abs(disY); i = i + 2) {
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


            return this.O(Mode.POINTY);

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


        return new CoordinateDoubled(this.x , this.y + 2);
    }

    @Override
    public Coordinate O(Mode mode) {

        if (mode.equals(Mode.FLAT)) throw new InvalidParameterException("Tu ne peux pas aller a l'ouest");


        return new CoordinateDoubled(this.x , this.y - 2);

    }

    @Override
    public Coordinate N(Mode mode) {
        if (mode.equals(Mode.POINTY)) throw new InvalidParameterException("Tu ne peux pas aller au nord");


        return this.NO(Mode.POINTY);
    }

    @Override
    public Coordinate S(Mode mode) {
        if (mode.equals(Mode.POINTY)) throw new InvalidParameterException("Tu ne peux pas aller au sud");


        return this.SE(Mode.POINTY);
    }

    @Override
    public Coordinate SO(Mode mode) {

        return new CoordinateDoubled(this.x + 1, this.y - 1);

    }

    @Override
    public Coordinate SE(Mode mode) {
        if (mode.equals(Mode.FLAT)) {


            return this.E(Mode.POINTY);

        } else {


            return new CoordinateDoubled(this.x+1, this.y+1 );

        }
    }

}
