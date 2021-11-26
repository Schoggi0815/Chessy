package ch.bbw;

public enum ChessFigureType {
    Pawn("p", position -> {}),
    Bishop("b", movesFunction),
    Rook("r", movesFunction),
    Knight("n", movesFunction),
    Queen("q", movesFunction),
    King("k", movesFunction);

    private final String lowerFigureString;
    private final MovesFunction movesFunction;

    ChessFigureType(String lowerFigureString, MovesFunction movesFunction) {
        this.lowerFigureString = lowerFigureString;
        this.movesFunction = movesFunction;
    }

    public String getLowerFigureString() {
        return lowerFigureString;
    }

    public MovesFunction getMovesFunction() {
        return movesFunction;
    }
}
