package ch.bbw;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ChessEngine chessEngine = new ChessEngine();
        chessEngine.loadGame("r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R w KQkq - 1 0");
        //chessEngine.loadGame("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"); //Default position

        chessEngine.printGameToConsole();

        ArrayList<Move> allPossibleMoves = chessEngine.getAllPossibleMoves();

        ArrayList<Move> resultMoves = new ArrayList<>();

        for (Move move : allPossibleMoves) {
            ChessBoard chessBoard = chessEngine.MoveOnNewBoard(move);

            ArrayList<Move> allPossibleMovesBlack = chessEngine.getAllPossibleMoves(chessBoard, true);

            for (Move blackMove : allPossibleMovesBlack) {
                ChessBoard chessBoard1 = chessEngine.MoveOnNewBoard(blackMove, chessBoard);

                ArrayList<Move> allPossibleMoves1 = chessEngine.getAllPossibleMoves(chessBoard1, true);

                boolean allCheckMate = true;
                Move endMove = null;

                for (Move move1 : allPossibleMoves1){
                    ChessBoard chessBoard2 = chessEngine.MoveOnNewBoard(move1, chessBoard1);

                    ArrayList<Move> moveArrayList = chessEngine.getAllPossibleMoves(chessBoard2, true);

                    if (moveArrayList.size() > 0){
                        allCheckMate = false;
                    }

                    endMove = move1;
                }

                if (allCheckMate){
                    resultMoves.add(move);
                    resultMoves.add(blackMove);
                    resultMoves.add(endMove);

                    resultMoves.forEach(System.out::println);
                    return;
                }
            }
        }
    }
}
