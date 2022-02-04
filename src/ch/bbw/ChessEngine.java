package ch.bbw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ChessEngine {
    public ChessBoard board = new ChessBoard();

    public ArrayList<Move> getAllPossibleMoves(){
        return getAllPossibleMoves(board, true);
    }

    public ArrayList<Move> getAllPossibleMoves(ChessBoard board, boolean checkForChess){
        ArrayList<Move> moves = new ArrayList<>();

        board.getBoard().forEach((vector2, figure) -> {
            if (figure.isWhite == board.isWhiteTurn()){
                moves.addAll(figure.GetAllPossibleMoves(board));
            }
        });

        if (board.isWhiteTurn()){
            if (board.isCastleWhiteKing()){
                moves.add(new CastlingMove(
                    new Vector2(4, 0),
                    new Vector2(6, 0),
                    new Vector2(7, 0),
                    new Vector2(5, 0)
                ));
            }

            if (board.isCastleWhiteQueen()){
                moves.add(new CastlingMove(
                        new Vector2(4, 0),
                        new Vector2(2, 0),
                        new Vector2(0, 0),
                        new Vector2(3, 0)
                ));
            }
        }
        else {
            if (board.isCastleBlackKing()){
                moves.add(new CastlingMove(
                        new Vector2(4, 8),
                        new Vector2(6, 8),
                        new Vector2(7, 8),
                        new Vector2(5, 8)
                ));
            }

            if (board.isCastleBlackQueen()){
                moves.add(new CastlingMove(
                        new Vector2(4, 8),
                        new Vector2(2, 8),
                        new Vector2(0, 8),
                        new Vector2(3, 8)
                ));
            }
        }

        if (!checkForChess){
            return moves;
        }

        ArrayList<Move> allPossibleMoves = new ArrayList<>();

        for (Move move : moves) {
            ChessBoard chessBoard = MoveOnNewBoard(move);
            if (!isInChess(chessBoard)){
                allPossibleMoves.add(move);
            }
        }

        return allPossibleMoves;
    }

    public void Move(Move move){
        move.MoveMove(board);
    }

    public boolean isInChess(ChessBoard board){
        final ChessFigure[] king = new ChessFigure[1];
        board.getBoard().forEach((vector2, figure) -> {
            if (figure.chessFigureType == ChessFigureType.King && figure.isWhite != board.isWhiteTurn()){
                king[0] = figure;
            }
        });

        ArrayList<Move> allPossibleMoves = getAllPossibleMoves(board, false);
        for (Move move: allPossibleMoves) {
            if (move.getTo().equals(king[0].pos)){
                return true;
            }
        }

        return false;
    }

    public ChessBoard MoveOnNewBoard(Move move){
        ChessBoard clone = board.clone();
        move.MoveMove(clone);

        return clone;
    }

    public ChessBoard MoveOnNewBoard(Move move, ChessBoard board){
        ChessBoard clone = board.clone();
        move.MoveMove(clone);

        return clone;
    }

    public void loadGame(String fenString){
        String[] strings = fenString.split(" ");

        // Positions
        loadPositions(strings[0]);

        // Turn
        board.setWhiteTurn(strings[1].equals("w"));

        // Castling
        for (int i = 0; i < strings[2].length(); i++){
            switch (strings[2].substring(i, i + 1)) {
                case "K" -> board.setCastleWhiteKing(true);
                case "Q" -> board.setCastleWhiteQueen(true);
                case "k" -> board.setCastleBlackKing(true);
                case "q" -> board.setCastleBlackQueen(true);
            }
        }

        // en-passant
        if (!strings[3].equals("-")){
            int x = Character.getNumericValue(strings[3].charAt(0)) - 10;
            int y = Integer.parseInt(strings[3].substring(1, 2)) - 1;

            board.setEnPassantPosition(new Vector2(x, y));
            board.setEnPassantFigure(board.getBoard().get(new Vector2(y == 6 ? 5 : 4, x)));
        }
    }

    public void printGameToConsole(){
        printGameToConsole(board);
    }

    public static void printGameToConsole(ChessBoard board){
        for (int y = 7; y >= 0; y--){
            System.out.print(y + 1 + " ");
            System.out.print("\t");
            for (int x = 0; x < 8; x++){
                ChessFigure chessFigure = board.getBoard().getOrDefault(new Vector2(x, y), null);

                if (chessFigure == null){
                    System.out.print("⛶");
                }
                else{
                    System.out.print(chessFigure.isWhite ? chessFigure.chessFigureType.getChessWhiteChar() : chessFigure.chessFigureType.getChessBlackChar());
                }

                System.out.print("\t");
            }
            System.out.println();
        }

        System.out.println("\tA\tB\tC\tD\tE\tF\tG\tH");
        System.out.println();

        System.out.println((board.isWhiteTurn() ? "White" : "Black") + " Turn");
        System.out.println("Castelling: wk: " + board.isCastleWhiteKing() + ", wq: " + board.isCastleWhiteQueen() + ", bk: " + board.isCastleBlackKing() + ", bq: " + board.isCastleBlackQueen());
        System.out.println("En Passant: " + (board.getEnPassantFigure() == null ? "-" : board.getEnPassantPosition().toTextPos()));
    }

    private void loadPositions(String positionsString){
        String[] lines = positionsString.split("/");

        int lineIndex = 0;
        for (int y = 7; y >= 0; y--){
            int xOffset = 0;
            for (int i = 0; i < lines[lineIndex].length(); i++){
                String character = (String) lines[lineIndex].subSequence(i, i + 1);

                for (ChessFigureType chessFigureType : ChessFigureType.values()) {
                    if (chessFigureType.getLowerFigureString().equals(character.toLowerCase(Locale.ROOT))){
                        ChessFigure chessFigure = new ChessFigure(chessFigureType, character.toUpperCase(Locale.ROOT).equals(character));
                        Vector2 key = new Vector2(i + xOffset, y);
                        board.getBoard().put(key, chessFigure);
                        chessFigure.pos = key;
                        break;
                    }
                }

                try {
                    int integer = Integer.parseInt(character);
                    xOffset += integer-1;
                } catch (NumberFormatException ignored) { }
            }

            lineIndex++;
        }
    }
}
