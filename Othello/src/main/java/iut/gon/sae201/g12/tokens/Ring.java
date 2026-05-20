package iut.gon.sae201.g12.tokens;

import iut.gon.sae201.g12.utils.Team;

public class Ring extends Token {
    public Ring(Team team) {
        super(team);
    }

    @Override
    public String charRepr() {
        return this.team == Team.BLACK ? "\u001B[31mO\u001B[37m" : "\u001B[34mo\u001B[37m";
    }
}