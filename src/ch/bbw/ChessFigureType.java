package ch.bbw;

import java.util.ArrayList;

public enum ChessFigureType {
    Pawn("p", '♙', '♟', ChessFigureType::getMovesPawn),
    Bishop("b", '♗', '♝', ChessFigureType::getMovesBishop),
    Rook("r", '♖', '♜', ChessFigureType::getMovesRook),
    Knight("n", '♘', '♞', ChessFigureType::getMovesKnight),
    Queen("q", '♕', '♛', ChessFigureType::getMovesQueen),
    King("k", '♔', '♚', ChessFigureType::getMovesKing);

    private final String lowerFigureString;
    private final char chessWhiteChar;
    private final char chessBlackChar;
    private final MovesFunction movesFunction;

    ChessFigureType(String lowerFigureString, char chessBlackChar, char chessWhiteChar, MovesFunction movesFunction) {
        this.lowerFigureString = lowerFigureString;
        this.chessWhiteChar = chessWhiteChar;
        this.chessBlackChar = chessBlackChar;
        this.movesFunction = movesFunction;
    }

    public String getLowerFigureString() { return lowerFigureString; }
    public MovesFunction getMovesFunction() { return movesFunction; }
    public char getChessWhiteChar() { return chessWhiteChar; }
    public char getChessBlackChar() { return chessBlackChar; }

    private static ArrayList<Move> getMovesPawn(ChessFigure figure, ChessBoard board){
        ArrayList<Move> moves = new ArrayList<>();

        Vector2 newPos;
        if (figure.isWhite) {
            newPos = new Vector2(figure.pos.x(), figure.pos.y() + 1);
        }
        else {
            newPos = new Vector2(figure.pos.x(), figure.pos.y() - 1);
        }

        if (!board.getBoard().containsKey(newPos)) {
            moves.add(new Move(figure.pos, newPos));
        }

        Vector2 sidePos = new Vector2(newPos.x() - 1, newPos.y());
        ChessFigure figure1 = board.getBoard().getOrDefault(sidePos, null);

        if ((figure1 != null && figure1.isWhite != figure.isWhite)){
            moves.add(new Move(figure.pos, sidePos));
        }

        if (sidePos.equals(board.getEnPassantPosition())){
            moves.add(new EnPassantMove(figure.pos, sidePos));
        }

        sidePos = new Vector2(newPos.x() + 1, newPos.y());
        figure1 = board.getBoard().getOrDefault(sidePos, null);

        if ((figure1 != null && figure1.isWhite != figure.isWhite)){
            moves.add(new Move(figure.pos, sidePos));
        }

        if (sidePos.equals(board.getEnPassantPosition())){
            moves.add(new EnPassantMove(figure.pos, sidePos));
        }

        if (!board.getBoard().containsKey(new Vector2(figure.pos.x(), figure.pos.y() + 1))){
            if (figure.isWhite && figure.pos.y() == 1){
                newPos = new Vector2(figure.pos.x(), figure.pos.y() + 2);
                if (!board.getBoard().containsKey(newPos)) {
                    moves.add(new DoublePawnMove(figure.pos, newPos, new Vector2(figure.pos.x(), figure.pos.y() + 1)));
                }
            }
        }

        if (!board.getBoard().containsKey(new Vector2(figure.pos.x(), figure.pos.y() - 1))){
            if (!figure.isWhite && figure.pos.y() == 6){
                newPos = new Vector2(figure.pos.x(), figure.pos.y() - 2);
                if (!board.getBoard().containsKey(newPos)) {
                    moves.add(new DoublePawnMove(figure.pos, newPos, new Vector2(figure.pos.x(), figure.pos.y() - 1)));
                }
            }
        }

        return moves;
    }

    private static ArrayList<Move> getMovesRook(ChessFigure figure, ChessBoard board){
        ArrayList<Move> moves = new ArrayList<>();

        Vector2 newPos;

        for (int i = figure.pos.x() + 1; i < 8; i++){
            newPos = new Vector2(i, figure.pos.y());

            if (TryAddFigure(figure, board, moves, newPos)) break;
        }

        for (int i = figure.pos.x() - 1; i >= 0; i--){
            newPos = new Vector2(i, figure.pos.y());

            if (TryAddFigure(figure, board, moves, newPos)) break;
        }

        for (int i = figure.pos.y() - 1; i >= 0; i--){
            newPos = new Vector2(figure.pos.x(), i);

            if (TryAddFigure(figure, board, moves, newPos)) break;
        }

        for (int i = figure.pos.y() + 1; i < 8; i++){
            newPos = new Vector2(figure.pos.x(), i);

            if (TryAddFigure(figure, board, moves, newPos)) break;
        }

        return moves;
    }

    private static ArrayList<Move> getMovesBishop(ChessFigure figure, ChessBoard board){
        ArrayList<Move> moves = new ArrayList<>();

        Vector2 newPos = new Vector2(figure.pos.x() + 1, figure.pos.y() + 1);

        while (newPos.x() >= 0 && newPos.x() < 8 && newPos.y() >= 0 && newPos.y() < 8){
            if (TryAddFigure(figure, board, moves, newPos)) break;

            newPos = new Vector2(newPos.x() + 1, newPos.y() + 1);
        }

        newPos = new Vector2(figure.pos.x() - 1, figure.pos.y() + 1);

        while (newPos.x() >= 0 && newPos.x() < 8 && newPos.y() >= 0 && newPos.y() < 8){
            if (TryAddFigure(figure, board, moves, newPos)) break;

            newPos = new Vector2(newPos.x() - 1, newPos.y() + 1);
        }

        newPos = new Vector2(figure.pos.x() + 1, figure.pos.y() - 1);

        while (newPos.x() >= 0 && newPos.x() < 8 && newPos.y() >= 0 && newPos.y() < 8){
            if (TryAddFigure(figure, board, moves, newPos)) break;

            newPos = new Vector2(newPos.x() + 1, newPos.y() - 1);
        }

        newPos = new Vector2(figure.pos.x() - 1, figure.pos.y() - 1);

        while (newPos.x() >= 0 && newPos.x() < 8 && newPos.y() >= 0 && newPos.y() < 8){
            if (TryAddFigure(figure, board, moves, newPos)) break;

            newPos = new Vector2(newPos.x() - 1, newPos.y() - 1);
        }

        return moves;
    }

    private static ArrayList<Move> getMovesKnight(ChessFigure figure, ChessBoard board){
        ArrayList<Move> moves = new ArrayList<>();

        TryAddFigure(figure, board, moves, new Vector2(figure.pos.x() + 1, figure.pos.y() + 2));
        TryAddFigure(figure, board, moves, new Vector2(figure.pos.x() + 2, figure.pos.y() + 1));
        TryAddFigure(figure, board, moves, new Vector2(figure.pos.x() + 2, figure.pos.y() - 1));
        TryAddFigure(figure, board, moves, new Vector2(figure.pos.x() + 1, figure.pos.y() - 2));
        TryAddFigure(figure, board, moves, new Vector2(figure.pos.x() - 1, figure.pos.y() - 2));
        TryAddFigure(figure, board, moves, new Vector2(figure.pos.x() - 2, figure.pos.y() - 1));
        TryAddFigure(figure, board, moves, new Vector2(figure.pos.x() - 2, figure.pos.y() + 1));
        TryAddFigure(figure, board, moves, new Vector2(figure.pos.x() - 1, figure.pos.y() + 2));

        return moves;
    }

    private static ArrayList<Move> getMovesKing(ChessFigure figure, ChessBoard board){
        ArrayList<Move> moves = new ArrayList<>();

        for (int x = -1; x < 2; x++){
            for (int y = -1; y < 2; y++){
                TryAddFigure(figure, board, moves, new Vector2(figure.pos.x() + x, figure.pos.y() + y));
            }
        }

        return moves;
    }

    private static ArrayList<Move> getMovesQueen(ChessFigure figure, ChessBoard board){
        ArrayList<Move> moves = getMovesBishop(figure, board);

        moves.addAll(getMovesRook(figure, board));

        return moves;
    }

    private static boolean TryAddFigure(ChessFigure figure, ChessBoard board, ArrayList<Move> moves, Vector2 newPos) {
        if (newPos.x() < 0 || newPos.x() >= 8 || newPos.y() < 0 || newPos.y() >= 8) return false;

        ChessFigure chessFigure = board.getBoard().getOrDefault(newPos, null);

        if (chessFigure != null){
            if (chessFigure.isWhite != figure.isWhite){
                moves.add(new Move(figure.pos, newPos));
            }

            return true;
        }

        moves.add(new Move(figure.pos, newPos));
        return false;
    }
}
