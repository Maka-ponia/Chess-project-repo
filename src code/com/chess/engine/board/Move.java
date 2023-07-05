package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public abstract class Move {
    final Board board;
    final Piece movedPiece;
    final int destinaionCoords;

    Move(final Board board, final Piece movedPiece, final int destinaionCoords) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinaionCoords = destinaionCoords;
    }

    public abstract Board Exc();

    public static final class MajorMove extends Move {

        public MajorMove(Board board, Piece movedPiece, int destinaionCoords) {
            super(board, movedPiece, destinaionCoords);

        }

        @Override
        public Board Exc() {
            return null;
        }
    }

    public static final class AtkMove extends Move {

        final Piece atkedPiece;

        public AtkMove(Board board, Piece movedPiece, final Piece atkedPiece, int destinaionCoords) {
            super(board, movedPiece, destinaionCoords);
            this.atkedPiece = atkedPiece;
        }

        @Override
        public Board Exc() {
            return null;
        }

    }

    public int getNextMoveCoords() {
        return this.destinaionCoords;
    }

}
