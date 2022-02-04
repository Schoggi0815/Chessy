package ch.bbw;

import java.util.ArrayList;

@FunctionalInterface
public interface MovesFunction {
    ArrayList<Move> GetPossibleMoves(ChessFigure figure, ChessBoard board);
}
