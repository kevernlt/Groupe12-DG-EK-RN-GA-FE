package iut.gon.sae201.g12.tokens;

import iut.gon.sae201.g12.utils.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public abstract class Token implements Cloneable {
    @Getter
    @Setter
    protected Team team;

    public abstract String charRepr();

    @Override
    public Token clone() throws CloneNotSupportedException {
        return (Token) super.clone();
    }
}