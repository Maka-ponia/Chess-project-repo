package com.chess.engine.board;

import com.chess.engine.board.Board.Builder;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;

public abstract class Move {
    final Board board;
    final Piece movedPiece;
    final int destinaionCoords;

    public static final Move NullMove = new nullMove();

    Move(final Board board, final Piece movedPiece, final int destinaionCoords) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinaionCoords = destinaionCoords;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + this.destinaionCoords;
        result = prime * result + this.movedPiece.hashCode();

        return result;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Move)) {
            return false;
        }
        final Move otherMove = (Move) other;
        return getNextMoveCoords() == otherMove.getNextMoveCoords()
                && getMovedpiece().equals(otherMove.getMovedpiece());
    }

    public int getNextMoveCoords() {
        return this.destinaionCoords;
    }

    public Piece getMovedpiece() {
        return this.movedPiece;
    }

    public boolean isAttack() {
        return false;
    }

    public boolean isCastlingMove() {
        return false;
    }

    public Piece getAtkedPiece() {
        return null;
    }

    public Board Exc() {

        final Builder builder = new Builder();

        for (final Piece piece : this.board.currentPlayer().getActivePieces()) {
            if (!this.movedPiece.equals(piece)) {
                builder.setPiece(piece);
            }
        }

        for (final Piece piece : this.board.currentPlayer().getOpp().getActivePieces()) {
            builder.setPiece(piece);
        }

        // move the moved piece
        builder.setPiece(this.movedPiece.movPiece(this));
        builder.setMoveMaker(this.board.currentPlayer().getOpp().getAlliance());
        return builder.Build();
    }

    public static final class MajorMove extends Move {

        public MajorMove(Board board, Piece movedPiece, int destinaionCoords) {
            super(board, movedPiece, destinaionCoords);
        }
    }

    public static class AtkMove extends Move {

        final Piece atkedPiece;

        public AtkMove(final Board board, Piece movedPiece, final Piece atkedPiece, final int destinaionCoords) {
            super(board, movedPiece, destinaionCoords);
            this.atkedPiece = atkedPiece;
        }

        @Override
        public int hashCode() {
            return this.atkedPiece.hashCode() + super.hashCode();
        }

        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AtkMove)) {
                return false;
            }
            final AtkMove otherAtkMove = (AtkMove) other;
            return super.equals(otherAtkMove) && getAtkedPiece().equals(otherAtkMove.getAtkedPiece());
        }

        @Override
        public Board Exc() {
            return null;
        }

        @Override
        public boolean isAttack() {
            return true;
        }

        @Override
        public Piece getAtkedPiece() {
            return this.atkedPiece;
        }
    }

    public static final class pawnMove extends Move {

        public pawnMove(final Board board, final Piece movedPiece, final int destinaionCoords) {
            super(board, movedPiece, destinaionCoords);
        }
    }

    public static final class pawnJump extends Move {

        public pawnJump(final Board board, final Piece movedPiece, final int destinaionCoords) {
            super(board, movedPiece, destinaionCoords);
        }

        @Override
        public Board Exc() {
            final Builder builder = new Builder();
            for (final Piece piece : this.board.currentPlayer().getActivePieces()) {
                if (!this.movedPiece.equals(piece)) {
                    builder.setPiece(piece);
                }
            }
            for (final Piece piece : this.board.currentPlayer().getOpp().getActivePieces()) {
                builder.setPiece(piece);
            }
            final Pawn movedPawn = (Pawn) this.movedPiece.movPiece(this);
            builder.setPiece(movedPiece);
            builder.setEnPassantpawn(movedPawn);
            builder.setMoveMaker((this.board.currentPlayer().getOpp().getAlliance()));
            return builder.Build();
        }

    }

    public static class pawnAtkMove extends AtkMove {

        public pawnAtkMove(final Board board, final Piece movedPiece, final int destinaionCoords,
                final Piece atkedPiece) {
            super(board, movedPiece, atkedPiece, destinaionCoords);
        }
    }

    public static final class pawnEnPassantAttackMove extends pawnAtkMove {

        public pawnEnPassantAttackMove(final Board board, final Piece movedPiece, final int destinaionCoords,
                final Piece pawnEnPassantAttackMove) {
            super(board, movedPiece, destinaionCoords, pawnEnPassantAttackMove);
        }
    }

    static abstract class castleMove extends Move {

        public castleMove(Board board, Piece movedPiece, int destinaionCoords) {
            super(board, movedPiece, destinaionCoords);
        }
    }

    public static final class kingSideCastleMovie extends Move {

        public kingSideCastleMovie(final Board board, final Piece movedPiece, final int destinaionCoords) {
            super(board, movedPiece, destinaionCoords);
        }
    }

    public static final class queenSideCastleMovie extends Move {

        public queenSideCastleMovie(final Board board, final Piece movedPiece, final int destinaionCoords) {
            super(board, movedPiece, destinaionCoords);
        }
    }

    public static final class nullMove extends Move {

        public nullMove() {
            super(null, null, -1);
        }

        @Override
        public Board Exc() {
            throw new RuntimeException("cannot exe the null move!");
        }
    }

    public static class MoveFactory {
        private MoveFactory() {
            throw new RuntimeException("not instaniable");
        }

        public static Move creatMove(final Board board, final int currentCoords, final int destinaionCoords) {

            for (final Move move : board.getAllLegelMoves()) {
                if (move.getCurrentCoord() == currentCoords && move.getNextMoveCoords() == destinaionCoords) {
                    return move;
                }
            }
            return null;
        }
    }

    public int getCurrentCoord() {
        return this.movedPiece.getPiecePos();
    }
}
