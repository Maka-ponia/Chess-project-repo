package com.chess.engine.board;

import com.chess.engine.board.Board.Builder;
import com.chess.engine.board.Move.castleMove.nullMove;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;

public abstract class Move {
    protected final Board board;
    protected final Piece movedPiece;
    protected final int destinaionCoords;
    protected final boolean isFirstMove;

    public static final nullMove NullMove = new nullMove();

    private Move(final Board board, final Piece movedPiece, final int destinaionCoords, final boolean isFirstMove) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinaionCoords = destinaionCoords;
        this.isFirstMove = isFirstMove;
    }

    private Move(final Board board, final int destinaionCoords) {
        this.board = board;
        this.movedPiece = null;
        this.destinaionCoords = destinaionCoords;
        this.isFirstMove = false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + this.destinaionCoords;
        result = prime * result + this.movedPiece.hashCode();
        result = prime * result + this.movedPiece.getPiecePos();

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
                && getMovedpiece().equals(otherMove.getMovedpiece())
                && getCurrentCoord() == otherMove.getCurrentCoord();
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

    public Board exc() {

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

            @Override
            public boolean equals(final Object other){
                return this == other || other instanceof MajorMove && super.equals(other);
            }

            @Override
            public String toString(){
                retrun movedPiece.getPieceType().toString() + BoardUtils.getPosAtCoords(this.destinaionCoords);
            }
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
        public Board exc() {
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

    public static final class PawnJump extends Move {

        public PawnJump(final Board board, final Piece movedPiece, final int destinaionCoords) {
            super(board, movedPiece, destinaionCoords);
        }

        @Override
        public Board exc() {
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

    public static class PawnAtkMove extends AtkMove {

        public PawnAtkMove(final Board board, final Piece movedPiece, final int destinaionCoords,
                final Piece atkedPiece) {
            super(board, movedPiece, atkedPiece, destinaionCoords);
        }
    }

    public static final class pawnEnPassantAttackMove extends PawnAtkMove {

        public pawnEnPassantAttackMove(final Board board, final Piece movedPiece, final int destinaionCoords,
                final Piece pawnEnPassantAttackMove) {
            super(board, movedPiece, destinaionCoords, pawnEnPassantAttackMove);
        }
    }

    public static abstract class castleMove extends Move {

        protected final Rook castleRook;
        protected final int castleRookstart;
        protected final int castleRookNextMovie;

        public castleMove(Board board, Piece movedPiece, int destinaionCoords, final Rook castleRook,
                final int castleRookstart, final int castleRookNextMovie) {
            super(board, movedPiece, destinaionCoords);
            this.castleRook = castleRook;
            this.castleRookstart = castleRookstart;
            this.castleRookNextMovie = castleRookNextMovie;
        }

        public Rook getCastleRook() {
            return this.castleRook;
        }

        @Override
        public boolean isCastlingMove() {
            return true;
        }

        @Override
        public Board exc() {
            Builder builder = new Builder();
            for (final Piece piece : this.board.currentPlayer().getActivePieces()) {
                if (!this.movedPiece.equals(piece) && !this.castleRook.equals(piece)) {
                    builder.setPiece(piece);
                }
            }
            for (final Piece piece : this.board.currentPlayer().getOpp().getActivePieces()) {
                builder.setPiece(piece);
            }
            builder.setPiece(this.movedPiece.movPiece(this));
            builder.setPiece(new Rook(this.castleRookNextMovie, this.castleRook.getPieceSide()));
            builder.setMoveMaker(this.board.currentPlayer().getOpp().getAlliance());
            return builder.Build();
        }

        public static final class kingSideCastleMove extends castleMove {

            public kingSideCastleMove(final Board board, final Piece movedPiece, final int destinaionCoords,
                    final Rook castleRook,
                    final int castleRookstart, final int castleRookNextMovie) {
                super(board, movedPiece, destinaionCoords, castleRook,
                        castleRookstart, castleRookNextMovie);
            }

            @Override
            public String toString() {
                return "0=0";
            }
        }

        public static final class queenSideCastleMove extends castleMove {

            public queenSideCastleMove(final Board board, final Piece movedPiece, final int destinaionCoords,
                    final Rook castleRook,
                    final int castleRookstart, final int castleRookNextMovie) {
                super(board, movedPiece, destinaionCoords, castleRook,
                        castleRookstart, castleRookNextMovie);
            }

            @Override
            public String toString() {
                return "0-0-0";
            }
        }

        public static final class nullMove extends Move {

            public nullMove() {
                super(null, null, -1);
            }

            @Override
            public Board exc() {
                throw new Runtimeexception("cannot exe the null move!");
            }
        }

        public static class MoveFactory {
            private MoveFactory() {
                throw new Runtimeexception("not instaniable");
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
    }

    public int getCurrentCoord() {
        return this.movedPiece.getPiecePos();
    }

}
