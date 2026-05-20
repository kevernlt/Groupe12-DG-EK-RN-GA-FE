package iut.gon.sae201.g12.tests.tokens;

import iut.gon.sae201.g12.tokens.Token;
import iut.gon.sae201.g12.utils.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TokenTest {

    // Sous-classe concrète anonyme pour tester Token
    private Token createToken(Team team) {
        return new Token(team) {
            @Override
            public String charRepr() {
                return "T";
            }
        };
    }

    @Test
    public void testGetTeamBlack() {
        Token token = createToken(Team.BLACK);
        assertEquals(Team.BLACK, token.getTeam(), "L'équipe doit être BLACK.");
    }

    @Test
    public void testGetTeamWhite() {
        Token token = createToken(Team.WHITE);
        assertEquals(Team.WHITE, token.getTeam(), "L'équipe doit être WHITE.");
    }

    @Test
    public void testSetTeam() {
        Token token = createToken(Team.BLACK);
        token.setTeam(Team.WHITE);
        assertEquals(Team.WHITE, token.getTeam(), "Après setTeam, l'équipe doit être WHITE.");
    }

    @Test
    public void testSetTeamReplace() {
        Token token = createToken(Team.BLACK);
        Team oldTeam = token.getTeam();
        token.setTeam(Team.WHITE);
        assertNotEquals(oldTeam, token.getTeam(), "L'ancienne équipe ne doit plus être présente après setTeam.");
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        Token token = createToken(Team.BLACK);
        Object cloned = token.clone();
        assertNotNull(cloned, "Le clone ne doit pas être null.");
        assertNotSame(token, cloned, "Le clone doit être un objet différent de l'original.");
        assertEquals(token.getTeam(), ((Token) cloned).getTeam(), "Le clone doit avoir la même équipe que l'original.");
    }

    @Test
    public void testTokenNotNull() {
        Token token = createToken(Team.BLACK);
        assertNotNull(token, "Le token ne doit pas être null après instanciation.");
    }
}