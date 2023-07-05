package com.chess.engine.player;

import java.util.Collection;

import javax.management.RuntimeErrorException;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Piece;

public abstract class Player {

    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;

    Player(final Board board, final Collection<Move> legalMoves, final Collection<Move> oppMoves) {

        this.board = board;
        this.playerKing = establishKing();
        this.legalMoves = legalMoves;
    }

    private King establishKing() {

        for (final Piece piece : getActivePieces()) {
            if (piece.getPieceType().isKing()) {
                return (King) piece;
            }
        }

        throw new RuntimeErrorException(null, "should no reach here! Not a vali board");
    }

    public boolean isMoveLegal(final Move move) {
        return this.legalMoves.contains(move);
    }

    // TODO implement
    public boolean isInCheck() {
        return false;
    }

    public boolean isInCheckmate() {
        return false;
    }

    public boolean isInStalemate() {
        return false;
    }

    public boolean isCastled() {
        return false;
    }

    public MoveTrans makeMove(final Move move) {
        return null;
    }

    public abstract Collection<Piece> getActivePieces();

    public abstract Alliance getAlliance();

    public abstract Player getOpp();
}
