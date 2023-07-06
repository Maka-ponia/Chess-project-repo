import com.chess.*;
import com.chess.engine.board.Board;
import com.chess.gui.Table;

public class jChess {
    public static void main(String[] args) {
        Board board = Board.makeBasicBoard();

        System.out.println(board);

        System.out.println("test");

        Table table = new Table();
    }
}
