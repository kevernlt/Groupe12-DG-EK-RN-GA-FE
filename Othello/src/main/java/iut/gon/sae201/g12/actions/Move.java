package iut.gon.sae201.g12.actions;
import iut.gon.sae201.g12.actions.Action;
import iut.gon.sae201.g12.utils.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Move extends Action {
    private Coordinate from;
    private Coordinate to;
}