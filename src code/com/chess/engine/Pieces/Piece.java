
package com.chess.engine.Pieces;

import java.util.Collection;
import com.chess.engine.Alliance;
import com.chess.engine.board.Move;
import com.chess.engine.board.Board;

public abstract class Piece {
    protected final int pieceCoords;
    protected final Alliance pieceSide;
    protected final boolean isFirstMove;
    protected final PieceType pieceType;
    private final int CachedHashCode;

    Piece(PieceType pieceType, final int pieceCoords, final Alliance pieceSide, final boolean isFirstMove) {
        this.pieceType = pieceType;
        this.pieceCoords = pieceCoords;
        this.pieceSide = pieceSide;
        this.isFirstMove = isFirstMove;
        this.CachedHashCode = calcHashCode();

    }

    private int calcHashCode() {
        int result = pieceType.hashCode();
        result = 31 * result + pieceSide.hashCode();
        result = 31 * result + pieceCoords;
        result = 31 * result + (isFirstMove ? 1 : 0);

        return result;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Piece)) {
            return false;
        }

        final Piece otherpiece = (Piece) other;
        return pieceCoords == otherpiece.getPiecePos() && pieceType == otherpiece.getPieceType()
                && pieceSide == otherpiece.getPieceSide() && isFirstMove == otherpiece.isFirstMove();
    }

    @Override
    public int hashCode() {
        return this.CachedHashCode;
    }

    public int getPiecePos() {
        return this.pieceCoords;
    }

    public Alliance getPieceSide() {
        return this.pieceSide;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }

    // A method that calculates the leagal moves of a piece
    public abstract Collection<Move> calcLegalmMoves(final Board board);

    public abstract Piece movPiece(Move move);

    public enum PieceType {

        PAWN("P") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KNIGHT("N") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        BISHOP("B") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        KING("K") {
            @Override
            public boolean isKing() {
                return true;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        QUEEN("Q") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return false;
            }
        },
        ROOK("R") {
            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isRook() {
                return true;
            }
        };

        private String pieceName;

        PieceType(final String pieceName) {
            this.pieceName = pieceName;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }

        public abstract boolean isKing();

        public abstract boolean isRook();

    }

    public boolean isRook() {
        return false;
    }

}
