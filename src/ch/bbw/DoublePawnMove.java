package ch.bbw;

public class DoublePawnMove extends Move {
    private Vector2 enPassantPos;

    public DoublePawnMove(Vector2 from, Vector2 to, Vector2 enPassantPos) {
        super(from, to);

        this.enPassantPos = enPassantPos;
    }

    @Override
    public void MoveMove(ChessBoard board) {
        board.setEnPassantPosition(enPassantPos);
        board.setEnPassantFigure(board.getBoard().getOrDefault(getFrom(), null));
        super.MoveMove(board);
    }
}
