package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public class Move {
    final Board board;
    final Piece movedPiece;
    final int destinaionCoords;

    Move(final Board board, final Piece movedPiece, final int destinaionCoords) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinaionCoords = destinaionCoords;
    }

    public static final class MajorMove extends Move {

        public MajorMove(Board board, Piece movedPiece, int destinaionCoords) {
            super(board, movedPiece, destinaionCoords);

        }
    }

    public static final class AtkMove extends Move {

        final Piece atkedPiece;

        public AtkMove(Board board, Piece movedPiece, final Piece atkedPiece, int destinaionCoords) {
            super(board, movedPiece, destinaionCoords);
            this.atkedPiece = atkedPiece;
        }

    }

    public int getNextMoveCoords() {
        return this.destinaionCoords;
    }

}
