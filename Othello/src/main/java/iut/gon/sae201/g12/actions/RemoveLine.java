package iut.gon.sae201.g12.actions;

import iut.gon.sae201.g12.utils.Coordinate;

import java.util.Set;

public class RemoveLine extends Action {
    private Set<Coordinate> line;
    private Coordinate ring;

    public RemoveLine(Set<Coordinate> line, Coordinate ring) {
        super();
        this.line = line;
        this.ring = ring;
    }

    public Set<Coordinate> getLine() {
        return this.line;
    }

    public void setline(Set<Coordinate> newLine) {this.line = newLine; }

    public Coordinate getRing() {
        return this.ring;
    }

    public void setRing(Coordinate newRing) {
        this.ring = newRing;
    }
}