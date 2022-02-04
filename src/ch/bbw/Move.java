package ch.bbw;

public class Move {
    private Vector2 from;
    private Vector2 to;

    public Move(Vector2 from, Vector2 to) {
        this.from = from;
        this.to = to;
    }

    public void MoveMove(ChessBoard board){
        board.setWhiteTurn(!board.isWhiteTurn());
        ChessFigure chessFigure = board.getBoard().getOrDefault(from, null);
        board.getBoard().remove(from);
        board.getBoard().remove(to);
        board.getBoard().put(to, chessFigure);
        chessFigure.pos = to;
        board.setEnPassantPosition(null);

        if (from.equals(new Vector2(0, 0))) {
            board.setCastleWhiteQueen(false);
        }
        if (from.equals(new Vector2(7, 0))) {
            board.setCastleWhiteKing(false);
        }
        if (from.equals(new Vector2(0, 7))){
            board.setCastleBlackQueen(false);
        }
        if (from.equals(new Vector2(7, 7))){
            board.setCastleBlackKing(false);
        }
        if (from.equals(new Vector2(4, 0))){
            board.setCastleWhiteKing(false);
            board.setCastleWhiteQueen(false);
        }
        if (from.equals(new Vector2(4, 7))){
            board.setCastleBlackKing(false);
            board.setCastleBlackQueen(false);
        }
    }

    public Vector2 getFrom() {
        return from;
    }

    public void setFrom(Vector2 from) {
        this.from = from;
    }

    public Vector2 getTo() {
        return to;
    }

    public void setTo(Vector2 to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Move{" +
                "from=" + from.toTextPos() +
                ", to=" + to.toTextPos() +
                '}';
    }
}