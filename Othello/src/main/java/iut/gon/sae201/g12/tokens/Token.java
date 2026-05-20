package iut.gon.sae201.g12.tokens;

import iut.gon.sae201.g12.utils.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Token {
    protected Team team;

    public abstract String charRepr();

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}