package iut.gon.sae201.g12.utils;

import iut.gon.sae201.g12.actions.Move;
import iut.gon.sae201.g12.actions.RemoveLine;
import iut.gon.sae201.g12.tokens.Pawn;
import iut.gon.sae201.g12.tokens.Token;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static iut.gon.sae201.g12.State.isInField;

/**
 * Interface définissant l'état du jeu Othello.
 *
 * <p>Cette interface expose les opérations disponibles sur l'état du jeu,
 * incluant les mouvements, manipulations de pièces et calcul des mouvements
 * disponibles.
 */
public interface IState {
    /**
     * Applique un mouvement au plateau actuel.
     *
     * @param move le mouvement à effectuer
     * @return le nouvel état après le mouvement
     * @throws InvocationTargetException si l'invocation réfléchie échoue
     * @throws InstantiationException si l'instantiation échoue
     * @throws IllegalAccessException si l'accès réfléchi échoue
     */
    public IState move(Move move) throws InvocationTargetException, InstantiationException, IllegalAccessException;

    /**
     * Supprime une ligne de pions.
     *
     * @param removeLine l'action de suppression de ligne
     * @return le nouvel état après suppression
     */
    public IState removeLine(RemoveLine removeLine);

    /**
     * Retourne les mouvements disponibles à partir d'une coordonnée.
     *
     * @param from la coordonnée source
     * @return ensemble des coordonnées destination possibles
     */
    public Set<Coordinate> availableMoves(Coordinate from);

    /**
     * Retourne le plateau de jeu.
     *
     * @return une map associant des coordonnées aux pièces présentes
     */
    public Map<Coordinate, Token> board();

    /**
     * Retourne les anneaux (rings) pour chaque équipe.
     *
     * @return une map équipe -> liste de coordonnées d'anneaux
     */
    public Map<Team, List<Coordinate>> rings();

    /**
     * Retourne les lignes de pions.
     *
     * @return une liste contenant les ensembles de coordonnées formant des lignes
     */
    public List<Set<Coordinate>> lines();

    /**
     * Retourne l'équipe dont c'est le tour.
     *
     * @return l'équipe actuelle
     */
    public Team turn();

    /**
     * Retire une pièce à une coordonnée donnée.
     *
     * @param c la coordonnée du pion à retirer
     * @return le nouvel état après retrait
     */
    public IState removeToken(Coordinate c);

    /**
     * Bascule une pièce à une coordonnée (transformation de type ou équipe).
     *
     * @param c la coordonnée de la pièce
     * @param tokenClass la classe de pièce cible
     * @param team l'équipe de la pièce
     * @return le nouvel état après basculement
     * @throws NoSuchMethodException si le constructeur souhaité n'existe pas
     * @throws InvocationTargetException si l'invocation réfléchie échoue
     * @throws InstantiationException si l'instantiation échoue
     * @throws IllegalAccessException si l'accès réfléchi échoue
     */
    public IState toggleToken(Coordinate c, Class<? extends Token> tokenClass, Team team) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    /**
     * Extrait les lignes de pions du plateau.
     *
     * @param board le plateau de jeu
     * @return liste des lignes de pions
     */
    public static List<Set<Coordinate>> getPawnsLines(Map<Coordinate, Token> board) {
        ArrayList<Set<Coordinate>> line = new ArrayList<>();
        HashSet<Coordinate> pawn = new HashSet<>();
        for (Coordinate c : board.keySet()) {
            if (board.get(c) instanceof Pawn) {
                pawn.add(c);
            }
        }
        for (Coordinate c : pawn) {
            HashSet<Coordinate> tryLine = new HashSet<>();
            tryLine.add(c);
            if (pawnLine(c, Direction.E, tryLine, board)) {
                line.add(tryLine);
            }
            tryLine.clear();
            tryLine.add(c);
            if (pawnLine(c, Direction.SE, tryLine, board)) {
                line.add(tryLine);
            }
            tryLine.clear();
            tryLine.add(c);
            if (pawnLine(c, Direction.NE, tryLine, board)) {
                line.add(tryLine);
            }
        }
        return line;
    }

    /**
     * Détecte récursivement les lignes de pions dans une direction donnée.
     *
     * @param c la coordonnée courante
     * @param direction la direction de recherche
     * @param line l'ensemble des pions détectés jusqu'à présent
     * @param board le plateau de jeu
     * @return {@code true} si une ligne de 5 pions a été détectée, {@code false} sinon
     */
    public static boolean pawnLine(Coordinate c, Direction direction, Set<Coordinate> line, Map<Coordinate, Token> board) {
        if (!isInField(c)) return false;
        Coordinate to = c.toDir(Mode.POINTY, direction);
        if (board.get(to) instanceof Pawn) {
            line.add(to);
            if (line.size() == 5) {
                return true;
            } else {
                return pawnLine(to, direction, line, board);
            }
        }
        return false;
    }
}
