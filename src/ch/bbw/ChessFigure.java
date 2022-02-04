package ch.bbw;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ChessFigure {
    public ChessFigureType chessFigureType;
    public boolean isWhite;
    public Vector2 pos;

    public ChessFigure clone(){
        ChessFigure chessFigure = new ChessFigure(chessFigureType, isWhite);
        chessFigure.pos = pos;

        return chessFigure;
    }

    public ChessFigure(ChessFigureType chessFigureType, boolean isWhite) {
        this.chessFigureType = chessFigureType;
        this.isWhite = isWhite;
    }

    public ArrayList<Move> GetAllPossibleMoves(ChessBoard board){
        MovesFunction movesFunction = chessFigureType.getMovesFunction();
        if (movesFunction != null) {
            return movesFunction.GetPossibleMoves(this, board);
        }
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "{" + (isWhite ? chessFigureType.getChessWhiteChar() : chessFigureType.getChessBlackChar()) + '}';
    }
}
