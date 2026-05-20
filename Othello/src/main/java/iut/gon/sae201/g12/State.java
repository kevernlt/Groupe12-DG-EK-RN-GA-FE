package iut.gon.sae201.g12;

import iut.gon.sae201.g12.actions.Move;
import iut.gon.sae201.g12.actions.RemoveLine;
import iut.gon.sae201.g12.tokens.Pawn;
import iut.gon.sae201.g12.tokens.Ring;
import iut.gon.sae201.g12.tokens.Token;
import iut.gon.sae201.g12.utils.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Représentation immuable d'un état du jeu Othello/Reversi.
 *
 * <p>Un état encapsule le plateau de jeu, le tour du joueur actuel et les lignes
 * de pions détectées. Cette classe implémente {@link IState} pour exposer l'API
 * standard de gestion du jeu.
 *
 * @param board le plateau de jeu (coordonnées -> pièces)
 * @param turn l'équipe dont c'est le tour
 * @param lines les lignes de pions détectées
 */
public record State(Map<Coordinate, Token> board, Team turn, List<Set<Coordinate>> lines) implements IState {
    private static final int[][] BOARD_POSES = {
            {6, 12}, {3, 15}, {2, 16}, {1, 17}, {0, 18},
            {1, 17}, {0, 18}, {1, 17}, {2, 16}, {3, 15}, {6, 12}
    };

    /**
     * Vérifie si une coordonnée se trouve à l'intérieur du plateau de jeu.
     *
     * @param c la coordonnée à vérifier
     * @return {@code true} si la coordonnée est valide sur le plateau, {@code false} sinon
     */
    public static boolean isInField(Coordinate c) {
        if (c == null) return false;

        if (c instanceof CoordinateCube cube && (cube.getQ() + cube.getR() + cube.getS() != 0)) {
            return false;
        }

        Point p = c.to2DCoordinate();
        int x = p.x();
        int y = p.y();
        if (x < 0 || x >= BOARD_POSES.length) return false;

        int minY = BOARD_POSES[x][0];
        int maxY = BOARD_POSES[x][1];
        if (y < minY || y > maxY) return false;
        return (y - minY) % 2 == 0;
    }

    /**
     * Détermine l'équipe gagnante en fonction du nombre d'anneaux restants.
     *
     * @return l'équipe gagnante (celle avec 2 ou moins d'anneaux), ou {@code null} si pas encore de gagnant
     */
    public Team winner() {
        if(rings().get(Team.WHITE).size()<=2)return Team.WHITE;
        if(rings().get(Team.BLACK).size()<=2)return Team.BLACK;
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        State other = (State) o;
        if (!board.equals(other.board)) return false;
        if (!turn.equals(other.turn)) return false;
        return lines.equals(other.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, turn, lines);
    }

    /**
     * Effectue un mouvement de pion en créant un anneau à la destination
     * et en déplaçant le pion original.
     *
     * @param move le mouvement à effectuer
     * @return le nouvel état après le mouvement
     * @throws IllegalArgumentException si le mouvement est invalide
     * @throws IndexOutOfBoundsException si la destination est hors du plateau
     * @throws InvocationTargetException si l'invocation réfléchie échoue
     * @throws InstantiationException si l'instantiation échoue
     * @throws IllegalAccessException si l'accès réfléchi échoue
     */
    @Override
    public IState move(Move move) throws RuntimeException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (!availableMoves(move.getFrom()).contains(move.getTo())) throw new IllegalArgumentException("Mouvement invalide");
        if (rings().get(turn).contains(move.getFrom())) throw new IllegalArgumentException("Mouvement invalide");
        if (isInField(move.getTo())) throw new IndexOutOfBoundsException("Mouvement invalide");
        if (!(lines == null)) throw new RuntimeException();
        toggleToken(move.getTo(), Ring.class,turn);
        removeToken(move.getFrom());
        toggleToken(move.getFrom(), Pawn.class,turn);
        return this;
    }

    /**
     * Supprime une ligne de pions confirmée ainsi que l'anneau spécifié.
     *
     * @param removeLine l'action de suppression contenant la ligne et l'anneau
     * @return le nouvel état après suppression
     * @throws RuntimeException si la ligne ne peut pas être supprimée (invalide ou de mauvaise taille)
     */
    @Override
    @SuppressWarnings("suspicious")
    public IState removeLine(RemoveLine removeLine) throws RuntimeException {
        if (removeLine == null) throw new RuntimeException("Cette ligne ne peut pas être suprimer");
        if (removeLine.getLine() == null) throw new RuntimeException("Cette ligne ne peut pas être suprimer");
        if (removeLine.getLine().size() != 5) throw new RuntimeException("Cette ligne ne peut pas être suprimer");
        Team t = board.get((Coordinate) removeLine.getLine().toArray()[0]).getTeam();
        for (Coordinate c : removeLine.getLine()) {
            if (board.get(c).getTeam() != t) {
                throw new RuntimeException();
            }
        }
        for (Coordinate c : removeLine.getLine()) {
            if (!isInField(c)) throw new RuntimeException("Erreur sur la position");
        }
        for (Coordinate c : removeLine.getLine()) {
            removeToken(c);
        }
        removeToken(removeLine.getRing());
        return this;
    }

    /**
     * Calcule les mouvements disponibles à partir d'une coordonnée source.
     *
     * @param from la coordonnée de départ
     * @return ensemble des coordonnées où un pion peut être déplacé
     */
    @Override
    public Set<Coordinate> availableMoves(Coordinate from) {
        Set<Coordinate> moves = new HashSet<>();
        keepGoing(moves,from,Direction.O,false);
        keepGoing(moves,from,Direction.E,false);
        keepGoing(moves,from,Direction.NE,false);
        keepGoing(moves,from,Direction.NO,false);
        keepGoing(moves,from,Direction.SO,false);
        keepGoing(moves,from,Direction.SE,false);
        return moves;
    }

    /**
     * Explore récursivement les coordonnées disponibles dans une direction donnée.
     *
     * @param coordsSet ensemble des coordonnées trouvées (modifié)
     * @param from la coordonnée courante
     * @param direction la direction d'exploration
     * @param pion indique si on a déjà traversé un pion
     */
    public void keepGoing(Set<Coordinate> coordsSet, Coordinate from, Direction direction,boolean pion) {
        Coordinate to = from.toDir(Mode.POINTY, direction);
        if (!isInField(to)) return;
        if (board.get(to) instanceof Ring) return;
        if (!(board.get(to) instanceof Pawn)){
            coordsSet.add(to);
            if(pion)return;
        } else {
            pion=true;
        }
        keepGoing(coordsSet, to, direction,pion);
    }


    @Override
    public Map<Coordinate, Token> board() {
        return board;
    }

    /**
     * Retourne les anneaux (pièces de type Ring) pour chaque équipe.
     *
     * @return une map associant chaque équipe à sa liste d'anneaux
     */
    @Override
    public Map<Team, List<Coordinate>> rings() {
        HashMap<Team,List<Coordinate>> ring = new HashMap<>();
        ArrayList<Coordinate> whiteRing = new ArrayList<>();
        ArrayList<Coordinate> blackRing = new ArrayList<>();
        for(Coordinate c: board.keySet()){
            if(board.get(c) instanceof Ring && board.get(c).getTeam()==Team.WHITE)whiteRing.add(c);
            if(board.get(c) instanceof Ring && board.get(c).getTeam()==Team.BLACK)blackRing.add(c);
        }
        ring.put(Team.WHITE,whiteRing);
        ring.put(Team.BLACK,blackRing);
        return ring;
    }

    @Override
    public List<Set<Coordinate>> lines() {
        return lines;
    }



    /**
     * Retourne l'équipe dont c'est le tour de jouer (équipe opposée à celle actuelle).
     *
     * @return l'autre équipe
     */
    @Override
    public Team turn() {
        return turn.other();
    }

    /**
     * Retire une pièce du plateau à la coordonnée spécifiée.
     *
     * @param c la coordonnée de la pièce à retirer
     * @return l'état modifié
     */
    @Override
    public IState removeToken(Coordinate c) {
        Token t = board.get(c);
        if(t!=null){
            board.remove(c);
            board.put(c,null);
        }
        return this;
    }

    /**
     * Crée une nouvelle pièce du type spécifié à la coordonnée donnée.
     *
     * @param c la coordonnée cible
     * @param tokenClass la classe de pièce à créer (Pawn ou Ring)
     * @param team l'équipe de la nouvelle pièce
     * @return l'état modifié
     * @throws InvocationTargetException si l'invocation réfléchie échoue
     * @throws InstantiationException si l'instantiation échoue
     * @throws IllegalAccessException si l'accès réfléchi échoue
     */
    @Override
    public IState toggleToken(Coordinate c, Class<? extends Token> tokenClass, Team team) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> constructor = tokenClass.getConstructors()[0];
        Token token = (Token) constructor.newInstance(team);
        if(token.getTeam() == board.get(c).getTeam() && token.getClass() == board.get(c).getClass())removeToken(c);
        return this;
    }
}