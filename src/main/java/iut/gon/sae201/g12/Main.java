package iut.gon.sae201.g12;

import iut.gon.sae201.g12.utils.CoordinateCube;
import iut.gon.sae201.g12.utils.CoordinateDoubled;
import iut.gon.sae201.g12.utils.Mode;

/**
 * Classe de test pour le système de coordonnées hexagonales.
 *
 * <p>Teste les deux modèles de représentation de coordonnées : Cube et Doubled.
 */
public class Main {
    /**
     * Fonction principale de test.
     *
     * @param args les arguments de ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        CoordinateCube test1 = new CoordinateCube(0,0,0 );

        CoordinateCube test2 = new CoordinateCube(0,3,-3);

        CoordinateDoubled test3 = new CoordinateDoubled(5,9);

        CoordinateDoubled test4 = new CoordinateDoubled(9,5);

        System.out.println(test1.toString());
    }
}
