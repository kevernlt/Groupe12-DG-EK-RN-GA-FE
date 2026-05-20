package iut.gon.sae201.g12.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Énumération représentant les deux équipes du jeu Othello/Reversi.
 *
 * <p>Chaque équipe est associée à un code ANSI pour l'affichage en couleur
 * en terminal.
 */
@Getter
public enum Team {
    /** Équipe Noire avec son code couleur ANSI */
    BLACK("\\e[47m"),
    /** Équipe Blanche avec son code couleur ANSI */
    WHITE("\\e[0;37m");

    /** Code couleur ANSI de l'équipe */
    private String color;

    /**
     * Constructeur avec couleur.
     *
     * @param color le code couleur ANSI
     */
    Team(String color) {
        this.color = color;
    }

    Team() {
    }

    /**
     * Retourne l'équipe adverse.
     *
     * @return l'autre équipe (BLACK si this == WHITE, WHITE sinon)
     */
    public Team other(){
        return (this == WHITE ? BLACK : WHITE);
    }
}
