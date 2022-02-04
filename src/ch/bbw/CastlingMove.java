package ch.bbw;

public class CastlingMove extends Move {
    private Vector2 fromRook;
    private Vector2 rookTo;

    public CastlingMove(Vector2 from, Vector2 to, Vector2 fromRook, Vector2 rookTo) {
        super(from, to);

        this.fromRook = fromRook;
        this.rookTo = rookTo;
    }

    @Override
    public void MoveMove(ChessBoard board) {
        super.MoveMove(board);

        ChessFigure rook = board.getBoard().getOrDefault(fromRook, null);
        board.getBoard().remove(rook.pos);
        board.getBoard().remove(rookTo);
        board.getBoard().put(rookTo, rook);
        rook.pos = rookTo;
    }
}

