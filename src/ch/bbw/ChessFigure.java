package ch.bbw;

public class ChessFigure {
    public ChessFigureType chessFigureType;
    public boolean isWhite;

    public ChessFigure(ChessFigureType chessFigureType, boolean isWhite) {
        this.chessFigureType = chessFigureType;
        this.isWhite = isWhite;
    }
}
