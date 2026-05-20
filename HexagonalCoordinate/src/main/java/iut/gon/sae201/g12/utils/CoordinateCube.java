package iut.gon.sae201.g12.utils;

import iut.gon.sae201.g12.exceptions.DifferentAxisExeption;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Représentation d'une coordonnée hexagonale en coordonnées "cube".
 * <p>
 * Le système cube utilise trois coordonnées (q, r, s) telles que q + r + s == 0
 * pour représenter des positions sur une grille hexagonale. Cette classe fournit
 * les opérations de déplacement et le calcul des positions intermédiaires le long
 * d'un axe.
 */
@Getter
@AllArgsConstructor
public class CoordinateCube extends Coordinate {
    /** composante q du système cube */
    private int q;
    /** composante r du système cube */
    private int r;
    /** composante s du système cube */
    private int s;

    /**
     * Conversion simple vers une position 2D (pour affichage/tests). La transformation
     * actuelle est arbitraire et correspond à l'implémentation utilisée par le sujet.
     *
     * @return le {@link Point} 2D associé
     */
    @Override
    public Point to2DCoordinate() {

        int pointX = this.r + 5;

        int pointY = (this.q + (this.s * -1)) + 9;

        return new Point(pointX, pointY);

    }

    /**
     * Calcule les coordonnées strictement entre cette coordonnée et {@code to}
     * si elles partagent un axe. Lance {@link DifferentAxisExeption} sinon.
     *
     * @param mode non utilisé par cette implémentation (présent pour compatibilité)
     * @param to   coordonnée cible
     * @return la liste des coordonnées entre les deux
     * @throws DifferentAxisExeption si les deux coordonnées n'ont pas d'axe commun
     */
    @Override
    public List<Coordinate> between(Mode mode, Coordinate to) {
        if (to instanceof CoordinateCube toCube) {
            if (this.s != toCube.getS() && this.q != toCube.getQ() && this.r != toCube.getR()) {
                throw new DifferentAxisExeption("Aucun axe en commun");
            }

            int depQ = toCube.getQ() - this.q;
            int depR = toCube.getR() - this.r;
            int depS = toCube.getS() - this.s;

            if (depQ == 0 && depR == 0 && depS == 0) {
                return new ArrayList<>();
            }

            List<Coordinate> ListCo = new ArrayList<>();

            if (depS == 0) {

                for (int i = 1; i < Math.abs(depQ); i++) {
                    if (depQ < 0) {

                        ListCo.add(new CoordinateCube(this.q - i, this.r + i, this.s));

                    } else {
                        ListCo.add(new CoordinateCube(this.q + i, this.r - i, this.s));
                    }
                }

            } else if (depR == 0) {

                for (int i = 1; i < Math.abs(depQ); i++) {

                    if (depQ < 0) {
                        ListCo.add(new CoordinateCube(this.q - i, this.r, this.s + i));
                    } else {
                        ListCo.add(new CoordinateCube(this.q + i, this.r, this.s - i));
                    }

                }

            } else {
                for (int i = 1; i < Math.abs(depR); i++) {

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


    /**
     * Déplace la coordonnée vers le nord-ouest.
     *
     * @param mode le mode d'orientation
     * @return la nouvelle coordonnée
     */
    @Override
    public Coordinate NO(Mode mode) {

        if (mode.equals(Mode.FLAT)) {


            return new CoordinateCube(this.q - 1, this.r, this.s + 1);

        } else {


            return new CoordinateCube(this.q, this.r - 1, this.s + 1);

        }

    }

    /**
     * Déplace la coordonnée vers le nord-est.
     *
     * @param mode le mode d'orientation
     * @return la nouvelle coordonnée
     */
    @Override
    public Coordinate NE(Mode mode) {


        return new CoordinateCube(this.q + 1, this.r - 1, this.s);
    }

    /**
     * Déplacement vers l'est. Pour le mode FLAT cette opération est invalide
     * et provoque une {@link InvalidParameterException}.
     */
    @Override
    public Coordinate E(Mode mode) {

        if (mode.equals(Mode.FLAT)) throw new InvalidParameterException("Tu ne peux pas aller a l'est");


        return new CoordinateCube(this.q + 1, this.r, this.s - 1);
    }

    /**
     * Déplacement vers l'ouest. Pour le mode FLAT cette opération est invalide
     * et provoque une {@link InvalidParameterException}.
     */
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
