package iut.gon.sae201.g12.tests;

import iut.gon.sae201.g12.utils.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamTest {

    @Test
    public void testOtherReturnWhite() {
        Team team = Team.BLACK;
        Team opposite = team.other();
        assertEquals(Team.WHITE, opposite, "L'opposé BLACK doit être WHITE");
    }

    @Test
    public void testOtherReturnBlack() {
        Team team = Team.WHITE;
        Team opposite = team.other();
        assertEquals(Team.BLACK, opposite, "L'opposé WHITE doit être BLACK");
    }

    @Test
    public void testGetColorBlack() {
        Team team = Team.BLACK;
        assertEquals("\\e[47m", team.getColor(), "La couleur BLACK ne correspond pas");
    }

    @Test
    public void testGetColorWhite() {
        Team team = Team.WHITE;
        assertEquals("\\e[0;37m", team.getColor(), "La couleur WHITE ne correspond pas");
    }
}