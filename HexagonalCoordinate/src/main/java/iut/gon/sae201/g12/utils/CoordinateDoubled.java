package iut.gon.sae201.g12.utils;

import iut.gon.sae201.g12.exceptions.DifferentAxisExeption;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Représentation d'une coordonnée hexagonale en système "doubled" (double coord).
 * <p>
 * Le modèle "doubled" stocke deux composantes entières (x, y) et fournit une
 * conversion simple vers 2D ainsi que les opérations de déplacement adaptées
 * selon le {@link Mode}.
 */
@Getter
@AllArgsConstructor
public class CoordinateDoubled extends Coordinate {
    /** composante x */
    private int x;
    /** composante y */
    private int y;

    @Override
    public Point to2DCoordinate() {

        return new Point(this.x, this.y);

    }

    /**
     * Calcule les coordonnées strictement entre cette coordonnée et {@code to}
     * selon la géométrie "doubled". Lance {@link DifferentAxisExeption} si elles
     * ne sont pas sur un même axe valide.
     *
     * @param mode non utilisé par cette implémentation
     * @param to   coordonnée cible
     * @return la liste des coordonnées intermédiaires
     * @throws DifferentAxisExeption si les deux coordonnées n'ont pas d'axe commun
     */
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

    /**
     * Déplacement vers l'est (valide seulement en POINTY).
     */
    @Override
    public Coordinate E(Mode mode) {

        if (mode.equals(Mode.FLAT)) throw new InvalidParameterException("Tu ne peux pas aller a l'est");


        return new CoordinateDoubled(this.x , this.y + 2);
    }

    /**
     * Déplacement vers l'ouest (valide seulement en POINTY).
     */
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
