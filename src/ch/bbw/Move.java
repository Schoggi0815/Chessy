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
        board.getBoard().remove(chessFigure.pos);
        board.getBoard().remove(to);
        board.getBoard().put(to, chessFigure);
        chessFigure.pos = to;
        board.setEnPassantPosition(null);
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