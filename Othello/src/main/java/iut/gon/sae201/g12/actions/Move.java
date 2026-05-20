package iut.gon.sae201.g12.actions;
import iut.gon.sae201.g12.actions.Action;
import iut.gon.sae201.g12.utils.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
/**
 * Représente un mouvement dans le jeu Othello.
 *
 * <p>Un mouvement contient une coordonnée source et une coordonnée destination.
 */
public class Move extends Action {
    private Coordinate from;
    private Coordinate to;
}