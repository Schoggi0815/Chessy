package ch.bbw;

public class Main {

    public static void main(String[] args) {
        ChessEngine chessEngine = new ChessEngine();
        chessEngine.loadGame("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"); //Default position
        chessEngine.printGameToConsole();
    }
}
