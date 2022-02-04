package ch.bbw;

public class EnPassantMove extends Move {
    public EnPassantMove(Vector2 from, Vector2 to) {
        super(from, to);
    }

    @Override
    public void MoveMove(ChessBoard board) {
        board.getBoard().remove(board.getEnPassantFigure().pos);
        super.MoveMove(board);
    }
}
