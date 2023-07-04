package com.chess.engine.pieces;

import java.util.Collection;
import com.chess.engine.Alliance;
import com.chess.engine.board.Move;
import com.chess.engine.board.Board;

public abstract class Piece {
    protected final int pieceCoords;
    protected final Alliance pieceSide;
    protected final boolean isFirstMove;
    protected final PieceType pieceType;

    Piece(PieceType pieceType, final int pieceCoords, final Alliance pieceSide) {
        this.pieceCoords = pieceCoords;
        this.pieceSide = pieceSide;
        this.isFirstMove = false;
        this.pieceType = pieceType;
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

    public enum PieceType {

        PAWN("P") {
            @Override
            public boolean isKing() {
                return true;
            }
        },
        KNIGHT("K") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        BISHOP("B") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        KING("KI") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        QUEEN("QU") {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        ROOK("R") {
            @Override
            public boolean isKing() {
                return false;
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

    }

}
