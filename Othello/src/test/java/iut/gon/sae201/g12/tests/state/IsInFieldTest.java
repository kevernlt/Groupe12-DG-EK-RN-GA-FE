package iut.gon.sae201.g12.tests.state;

import iut.gon.sae201.g12.State;
import iut.gon.sae201.g12.utils.CoordinateCube;
import iut.gon.sae201.g12.utils.CoordinateDoubled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IsInFieldTest {

    @Test
    public void testNullReturnsFalse() {
        assertFalse(State.isInField(null), "null doit retourner false.");
    }

    @Test
    public void testCenterIsInField() {
        // Case centrale du terrain CoordinateDoubled : [5,9]
        assertTrue(State.isInField(new CoordinateDoubled(5, 9)), "La case centrale doit être dans le terrain.");
    }

    @Test
    public void testValidCornerIsInField() {
        // Ligne 0 : colonnes valides entre 6 et 12 par pas de 2
        assertTrue(State.isInField(new CoordinateDoubled(0, 6)), "Une case valide en bordure doit être dans le terrain.");
    }

    @Test
    public void testValidCornerIsInField2() {
        // Ligne 0 : autre extrémité
        assertTrue(State.isInField(new CoordinateDoubled(0, 12)), "Une case valide en bordure doit être dans le terrain.");
    }

    @Test
    public void testOutsideBoardReturnsFalse() {
        // Ligne inexistante
        assertFalse(State.isInField(new CoordinateDoubled(15, 9)), "Une case hors du terrain doit retourner false.");
    }

    @Test
    public void testOddOffsetReturnsFalse() {
        // Colonne avec mauvaise parité sur la ligne 5
        assertFalse(State.isInField(new CoordinateDoubled(5, 2)), "Une case avec mauvaise parité de colonne doit retourner false.");
    }

    @Test
    public void testNegativeCoordinateReturnsFalse() {
        assertFalse(State.isInField(new CoordinateDoubled(-1, 9)), "Une coordonnée négative doit retourner false.");
    }

    @Test
    public void testCubeValidIsInField() {
        // CoordinateCube valide : q+r+s = 0
        assertTrue(State.isInField(new CoordinateCube(0, 0, 0)), "Une CoordinateCube valide au centre doit être dans le terrain.");
    }

    @Test
    public void testCubeInvalidConstraintReturnsFalse() {
        // CoordinateCube invalide : q+r+s != 0
        assertFalse(State.isInField(new CoordinateCube(1, 1, 1)), "Une CoordinateCube avec q+r+s != 0 doit retourner false.");
    }
}