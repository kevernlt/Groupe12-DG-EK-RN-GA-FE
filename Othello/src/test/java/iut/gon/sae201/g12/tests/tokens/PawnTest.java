package iut.gon.sae201.g12.tests.tokens;

import iut.gon.sae201.g12.tokens.Pawn;
import iut.gon.sae201.g12.utils.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {

    @Test
    public void testCharReprBlack() {
        Pawn pawn = new Pawn(Team.BLACK);
        assertEquals("x", pawn.charRepr(), "Un pion noir doit être représenté par 'x'.");
    }

    @Test
    public void testCharReprWhite() {
        Pawn pawn = new Pawn(Team.WHITE);
        assertEquals(".", pawn.charRepr(), "Un pion blanc doit être représenté par '.'.");
    }

    @Test
    public void testGetTeamBlack() {
        Pawn pawn = new Pawn(Team.BLACK);
        assertEquals(Team.BLACK, pawn.getTeam(), "L'équipe doit être BLACK.");
    }

    @Test
    public void testGetTeamWhite() {
        Pawn pawn = new Pawn(Team.WHITE);
        assertEquals(Team.WHITE, pawn.getTeam(), "L'équipe doit être WHITE.");
    }

    @Test
    public void testChangeTeamBlackToWhite() {
        Pawn pawn = new Pawn(Team.BLACK);
        pawn.changeTeam();
        assertEquals(Team.WHITE, pawn.getTeam(), "Après changeTeam, un pion noir doit devenir blanc.");
    }

    @Test
    public void testChangeTeamWhiteToBlack() {
        Pawn pawn = new Pawn(Team.WHITE);
        pawn.changeTeam();
        assertEquals(Team.BLACK, pawn.getTeam(), "Après changeTeam, un pion blanc doit devenir noir.");
    }

    @Test
    public void testChangeTeamUpdatesCharRepr() {
        Pawn pawn = new Pawn(Team.BLACK);
        pawn.changeTeam();
        assertEquals(".", pawn.charRepr(), "Après changeTeam, la représentation doit changer de 'x' à '.'.");
    }

    @Test
    public void testPawnIsNotNull() {
        Pawn pawn = new Pawn(Team.BLACK);
        assertNotNull(pawn, "Le pion ne doit pas être null après instanciation.");
    }
}