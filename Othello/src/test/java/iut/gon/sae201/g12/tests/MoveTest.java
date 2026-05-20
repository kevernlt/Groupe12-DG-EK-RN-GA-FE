package iut.gon.sae201.g12.tests;

import iut.gon.sae201.g12.actions.Move;
import iut.gon.sae201.g12.utils.Coordinate;
import iut.gon.sae201.g12.utils.CoordinateDoubled;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class MoveTest {

    private Coordinate initialFrom;
    private Coordinate initialTo;
    private Move moveAction;

    @BeforeEach
    public void setUp() {
        initialFrom = new CoordinateDoubled(0, 0);
        initialTo = new CoordinateDoubled(2, 0);
        moveAction = new Move(initialFrom, initialTo);
    }

    @Test
    public void testConstructorAndGetters() {
        assertNotNull(moveAction.getFrom(), "La coordonnée de départ ne doit pas être null.");
        assertNotNull(moveAction.getTo(), "La coordonnée d'arrivée ne doit pas être null.");
        assertEquals(initialFrom, moveAction.getFrom(), "getFrom doit retourner la coordonnée passée au constructeur.");
        assertEquals(initialTo, moveAction.getTo(), "getTo doit retourner la coordonnée passée au constructeur.");
    }

    @Test
    public void testSetFrom() {
        Coordinate newFrom = new CoordinateDoubled(4, 0);
        Coordinate oldFrom = initialFrom;
        moveAction.setFrom(newFrom);
        assertEquals(newFrom, moveAction.getFrom(), "getFrom doit retourner la nouvelle coordonnée après setFrom.");
        assertNotEquals(oldFrom, moveAction.getFrom(), "L'ancienne coordonnée de départ ne doit plus être présente.");
    }

    @Test
    public void testSetTo() {
        Coordinate newTo = new CoordinateDoubled(6, 0);
        Coordinate oldTo = initialTo;
        moveAction.setTo(newTo);
        assertEquals(newTo, moveAction.getTo(), "getTo doit retourner la nouvelle coordonnée après setTo.");
        assertNotEquals(oldTo, moveAction.getTo(), "L'ancienne coordonnée d'arrivée ne doit plus être présente.");
    }

    @Test
    public void testFromAndToAreDifferent() {
        assertNotEquals(moveAction.getFrom(), moveAction.getTo(), "La coordonnée de départ et d'arrivée ne doivent pas être identiques.");
    }
}
