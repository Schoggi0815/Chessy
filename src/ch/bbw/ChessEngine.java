package ch.bbw;

import java.util.HashMap;
import java.util.Locale;

public class ChessEngine {
    private ChessBoard board;

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
        for (int y = 7; y >= 0; y--){
            for (int x = 0; x < 8; x++){
                ChessFigure chessFigure = board.getBoard().getOrDefault(new Vector2(x, y), null);

                if (chessFigure == null){
                    System.out.print(".");
                    continue;
                }

                System.out.print(chessFigure.isWhite ? chessFigure.chessFigureType.getLowerFigureString().toUpperCase(Locale.ROOT) : chessFigure.chessFigureType.getLowerFigureString());
            }
            System.out.println();
        }

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
                        board.getBoard().put(new Vector2(i + xOffset, y), new ChessFigure(chessFigureType, character.toUpperCase(Locale.ROOT).equals(character)));

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
