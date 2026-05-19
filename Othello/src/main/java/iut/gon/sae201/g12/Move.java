package iut.gon.sae201.g12;

import iut.gon.sae201.g12.utils.Coordinate;

public class Move extends Action {
    private Coordinate from;
    private Coordinate to;

    public Move(Coordinate from, Coordinate to) {
        super();
        this.from = from;
        this.to = to;
    }

    public Coordinate getFrom() {
        return this.from;
    }

    public Coordinate getTo() {
        return this.to;
    }

    public Coordinate SetFrom(Coordinate newFrom) {
        return this.from = newFrom;
    }

    public Coordinate SetTo(Coordinate newTo) {
        return this.to = newTo;
    }
}