package iut.gon.sae201.g12.tokens;

import iut.gon.sae201.g12.utils.Team;

public class Pawn extends Token {
    public Pawn(Team team) {
        super(team);
    }

    public void changeTeam() {
        this.team = (this.team == Team.BLACK ? Team.WHITE : Team.BLACK); // Reverse team
    }

    @Override
    public String charRepr() {
        return this.team == Team.BLACK ? "x" : ".";
    }
}