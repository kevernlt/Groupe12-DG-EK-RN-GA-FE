package iut.gon.sae201.g12.tokens;

import iut.gon.sae201.g12.Team;

public class Ring extends Token {
    public Ring(Team team) {
        super(team);
    }

    @Override
    public String charRepr() {
        return this.team == Team.BLACK ? "O" : "o";
    }
}