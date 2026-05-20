package iut.gon.sae201.g12.tests.tokens;

import iut.gon.sae201.g12.tokens.Ring;
import iut.gon.sae201.g12.utils.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RingTest {

    @Test
    public void testCharReprBlack() {
        Ring ring = new Ring(Team.BLACK);
        assertEquals("\u001B[31mO\u001B[37m", ring.charRepr(), "Un anneau noir doit être représenté par '\u001B[31mO\u001B[37m'.");
    }

    @Test
    public void testCharReprWhite() {
        Ring ring = new Ring(Team.WHITE);
        assertEquals("\u001B[34mo\u001B[37m", ring.charRepr(), "Un anneau blanc doit être représenté par '\u001B[34mo\u001B[37m'.");
    }

    @Test
    public void testGetTeamBlack() {
        Ring ring = new Ring(Team.BLACK);
        assertEquals(Team.BLACK, ring.getTeam(), "L'équipe doit être BLACK.");
    }

    @Test
    public void testGetTeamWhite() {
        Ring ring = new Ring(Team.WHITE);
        assertEquals(Team.WHITE, ring.getTeam(), "L'équipe doit être WHITE.");
    }

    @Test
    public void testRingIsNotNull() {
        Ring ring = new Ring(Team.BLACK);
        assertNotNull(ring, "L'anneau ne doit pas être null après instanciation.");
    }
}