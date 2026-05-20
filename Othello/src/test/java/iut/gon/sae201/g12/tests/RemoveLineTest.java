package iut.gon.sae201.g12.tests;
import iut.gon.sae201.g12.actions.RemoveLine;
import iut.gon.sae201.g12.utils.Coordinate;
import iut.gon.sae201.g12.utils.CoordinateDoubled;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;

public class RemoveLineTest {

    private HashSet<Coordinate> initialLine;
    private Coordinate initialRing;
    private RemoveLine removeLineAction;

    @BeforeEach
    public void setUp() {
        initialLine = new HashSet<>();
        initialRing = new CoordinateDoubled(0, 0);
        removeLineAction = new RemoveLine(initialLine, initialRing);
    }

    @Test
    public void testConstructorAndGetters() {
        assertNotNull(removeLineAction.getLine(), "La ligne ne doit pas être null après initialisation.");
        assertEquals(initialLine, removeLineAction.getLine(), "La ligne retournée doit être celle passée au constructeur.");
        assertEquals(initialRing, removeLineAction.getRing(), "L'anneau retourné doit être celui passé au constructeur.");
    }

    @Test
    public void testSetLine() {
        HashSet<Coordinate> newLine = new HashSet<>();
        newLine.add(new CoordinateDoubled(1, 0));
        HashSet<Coordinate> oldLine = initialLine;
        removeLineAction.setline(newLine);
        assertEquals(newLine, removeLineAction.getLine(), "Le getter doit retourner la nouvelle ligne.");
        assertNotEquals(oldLine, removeLineAction.getLine(), "L'ancienne ligne ne doit plus être présente.");
    }

    @Test
    public void testSetRing() {
        Coordinate newRing = new CoordinateDoubled(2, 0);
        Coordinate oldRing = initialRing;
        removeLineAction.setRing(newRing);
        assertEquals(newRing, removeLineAction.getRing(), "Le getter doit retourner le nouvel anneau.");
        assertNotEquals(oldRing, removeLineAction.getRing(), "L'ancien anneau ne doit plus être présent.");
    }
}