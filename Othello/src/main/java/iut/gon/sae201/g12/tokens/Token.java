package iut.gon.sae201.g12.tokens;

import iut.gon.sae201.g12.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public abstract class Token {
    @Getter
    @Setter
    protected Team team;

    public abstract String charRepr();

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}