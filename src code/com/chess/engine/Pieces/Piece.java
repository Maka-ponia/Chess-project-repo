package com.chess.engine.pieces;

import java.util.Collection;
import com.chess.engine.Alliance;
import com.chess.engine.board.Move;
import com.chess.engine.board.Board;

public abstract class Piece {
    protected final int pieceCoords;
    protected final Alliance pieceSide;
    protected final boolean isFirstMove;

    Piece(final int pieceCoords, final Alliance pieceSide) {
        this.pieceCoords = pieceCoords;
        this.pieceSide = pieceSide;
        this.isFirstMove = false;
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

    // A method that calculates the leagal moves of a piece
    public abstract Collection<Move> calcLegalmMoves(final Board board);

    public enum PieceType {

        PAWN("P"),
        KNIGHT("K"),
        BISHOP("B"),
        KING("KI"),
        QUEEN("QU"),
        ROOK("R");

        private String pieceName;

        PieceType(final String pieceName) {
            this.pieceName = pieceName;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }

    }

}
