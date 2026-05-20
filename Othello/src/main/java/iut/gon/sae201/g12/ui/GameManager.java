package iut.gon.sae201.g12.ui;

import iut.gon.sae201.g12.Model;
import iut.gon.sae201.g12.State;
import iut.gon.sae201.g12.factory.FactoryCube;
import iut.gon.sae201.g12.factory.FactoryDoubled;
import iut.gon.sae201.g12.tokens.Pawn;
import iut.gon.sae201.g12.tokens.Ring;
import iut.gon.sae201.g12.tokens.Token;
import iut.gon.sae201.g12.utils.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class GameManager {
    @Getter
    IFactory factory;
    @Setter
    IState gameState;
    @Setter
    PlayMode playMode;
    @Setter
    Mode boardMode;
    @Setter
    DimensionMode dimensionMode;

    public void start() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        showPlate();
        Model model = new Model(gameState);
        if(gameState instanceof State){
            ringsPlacement(model);
            while (((State) gameState).winner()==null){
                if(!model.getPawnsLines().isEmpty()){

                }
            }
        }


    }

    public void ringsPlacement(Model model) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        int blackPawn = 5;
        int whitePawn = 5;
        int x,y;
        while(whitePawn!=0 || blackPawn!=0){
            System.out.println("Entrez les coordonées du "+(6-whitePawn)+"e anneau blanc.");
            System.out.println("x exemple : 0");
            x=InputManager.awaitNumberInput();
            System.out.println("y exemple : 6");
            y=InputManager.awaitNumberInput();
            model.toggleToken(factory.fromDoubled(x,y),Team.WHITE, Ring.class);
            whitePawn-=1;
            showPlate();
            System.out.println("Entrez les coordonées du "+(6-whitePawn)+"e anneau noir.");
            System.out.println("x exemple : 0");
            x=InputManager.awaitNumberInput();
            System.out.println("y exemple : 6");
            y=InputManager.awaitNumberInput();
            model.toggleToken(factory.fromDoubled(x,y),Team.BLACK, Ring.class);
            blackPawn-=1;
            showPlate();
        }
    }

    public void buildBoardFactory() {
        if (this.dimensionMode.equals(DimensionMode.CUBE)) {
            this.factory = new FactoryCube();
        }
        if (this.dimensionMode.equals(DimensionMode.DOUBLED)) {
            this.factory = new FactoryDoubled();
        }
    }

    public void showPlate() {
        System.out.print(renderBoard());
    }

    public String renderBoard() {
        Map<Point, Token> board = new HashMap<>();
        for (Map.Entry<Coordinate, Token> entry : this.gameState.board().entrySet()) {
            board.put(entry.getKey().to2DCoordinate(), entry.getValue());
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 18; j++) {
                Point point = new Point(i, j);
                if (board.containsKey(point)) {
                    Token token = board.get(point);
                    if (token == null) {
                        builder.append('-');
                    } else if (token instanceof Pawn p) {
                        builder.append(p.charRepr());
                    } else if (token instanceof Ring r) {
                        builder.append(r.charRepr());
                    } else {
                        builder.append('?');
                    }
                } else {
                    builder.append(' ');
                }

                if (j < 18) {
                    builder.append(' ');
                }
            }

            builder.append(System.lineSeparator());
        }

        return builder.toString();
    }

    public enum PlayMode {
        PLAYER_VERSUS,
        AI_VERSUS;
    }

    public enum DimensionMode {
        CUBE,
        DOUBLED;
    }
}
