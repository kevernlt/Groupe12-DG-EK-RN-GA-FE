package iut.gon.sae201.g12;

import iut.gon.sae201.g12.utils.Coordinate;

import java.util.HashSet;

public class RemoveLine extends Action {
    private HashSet<Coordinate> line;
    private Coordinate ring;

    public RemoveLine(HashSet<Coordinate> line, Coordinate ring) {
        super();
        this.line = line;
        this.ring = ring;
    }

    public HashSet<Coordinate> getLine() {
        return this.line;
    }

    public void setline(HashSet<Coordinate> line) {
        this.line = line;
    }

    public Coordinate getRing() {
        return this.ring;
    }

    public void setRing(Coordinate ring) {
        this.ring = ring;
    }
}