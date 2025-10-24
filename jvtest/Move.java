package jvtest;

public class Move {
    public int fromX, fromY, toX, toY;
    public String specialMove; // For moves like "Castle", "EnPassant", etc.

    public Move(int fromX, int fromY, int toX, int toY) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.specialMove = null;
    }

    public Move(int fromX, int fromY, int toX, int toY, String specialMove) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.specialMove = specialMove;
    }
}
