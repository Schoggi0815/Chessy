package ch.bbw;

import java.util.HashMap;

public class ChessBoard {
    private HashMap<Vector2, ChessFigure> board = new HashMap<>();
    private boolean whiteTurn;
    private boolean castleWhiteKing;
    private boolean castleWhiteQueen;
    private boolean castleBlackKing;
    private boolean castleBlackQueen;
    private Vector2 enPassantPosition;
    private ChessFigure enPassantFigure;

    public HashMap<Vector2, ChessFigure> getBoard() {
        return board;
    }

    public void setBoard(HashMap<Vector2, ChessFigure> board) {
        this.board = board;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }

    public boolean isCastleWhiteKing() {
        return castleWhiteKing;
    }

    public void setCastleWhiteKing(boolean castleWhiteKing) {
        this.castleWhiteKing = castleWhiteKing;
    }

    public boolean isCastleWhiteQueen() {
        return castleWhiteQueen;
    }

    public void setCastleWhiteQueen(boolean castleWhiteQueen) {
        this.castleWhiteQueen = castleWhiteQueen;
    }

    public boolean isCastleBlackKing() {
        return castleBlackKing;
    }

    public void setCastleBlackKing(boolean castleBlackKing) {
        this.castleBlackKing = castleBlackKing;
    }

    public boolean isCastleBlackQueen() {
        return castleBlackQueen;
    }

    public void setCastleBlackQueen(boolean castleBlackQueen) {
        this.castleBlackQueen = castleBlackQueen;
    }

    public Vector2 getEnPassantPosition() {
        return enPassantPosition;
    }

    public void setEnPassantPosition(Vector2 enPassantPosition) {
        this.enPassantPosition = enPassantPosition;
    }

    public ChessFigure getEnPassantFigure() {
        return enPassantFigure;
    }

    public void setEnPassantFigure(ChessFigure enPassantFigure) {
        this.enPassantFigure = enPassantFigure;
    }
}
