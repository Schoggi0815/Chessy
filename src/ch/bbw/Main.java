package ch.bbw;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        ChessEngine chessEngine = new ChessEngine();
        ChessBoard board = chessEngine.loadGame(Files.readString(Path.of("./input.txt")));

        ChessEngine.printGameToConsole(board);

        ArrayList<Move> allPossibleMoves = chessEngine.getAllPossibleMoves(board, true);

        ArrayList<Move> resultMoves = new ArrayList<>();

        for (Move move : allPossibleMoves) {
            ChessBoard chessBoard = chessEngine.MoveOnNewBoard(move, board);

            ArrayList<Move> allPossibleMovesBlack = chessEngine.getAllPossibleMoves(chessBoard, true);

            boolean allCheckMate = true;
            Move endMove = null;
            Move blackEndMove = null;

            for (Move blackMove : allPossibleMovesBlack) {

                ChessBoard chessBoard1 = chessEngine.MoveOnNewBoard(blackMove, chessBoard);

                ArrayList<Move> allPossibleMoves1 = chessEngine.getAllPossibleMoves(chessBoard1, true);

                boolean isThisCheckMate = false;

                for (Move move1 : allPossibleMoves1){
                    ChessBoard chessBoard2 = chessEngine.MoveOnNewBoard(move1, chessBoard1);

                    ArrayList<Move> moveArrayList = chessEngine.getAllPossibleMoves(chessBoard2, true);

                    if (moveArrayList.size() == 0){
                        isThisCheckMate = true;
                        endMove = move1;
                    }
                }

                if (!isThisCheckMate){
                    allCheckMate = false;
                }

                blackEndMove = blackMove;
            }

            if (allCheckMate){
                resultMoves.add(move);
                resultMoves.add(blackEndMove);
                resultMoves.add(endMove);

                resultMoves.forEach(System.out::println);
                return;
            }
        }
    }
}
