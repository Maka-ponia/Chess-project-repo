package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

public class MoveTrans {

    private final Board transBoard;
    private final Move move;
    private final MoveStatus MoveStatus;

    public MoveTrans(final Board transBoard, Move move, MoveStatus MoveStatus) {
        this.transBoard = transBoard;
        this.move = move;
        this.MoveStatus = MoveStatus;
    }

    public MoveStatus getMoveStatus() {
        return this.MoveStatus;
    }

    public Board getTranBoard() {
        return this.transBoard;
    }

}
