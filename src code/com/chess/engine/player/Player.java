package com.chess.engine.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.management.RuntimeErrorexception;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.castleMove.kingSideCastleMove;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Piece;

public abstract class Player {

    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;
    private final boolean isInCheck;

    Player(final Board board, final Collection<Move> legalMoves, final Collection<Move> oppMoves) {

        this.board = board;
        this.playerKing = establishKing();
        this.legalMoves = Collections.unmodifiableCollection(makeLegalMoves(legalMoves, oppMoves));
        this.isInCheck = !Player.calcAtkOnTile(this.playerKing.getPiecePos(), oppMoves).isEmpty();
    }

    public Collection<Move> makeLegalMoves(Collection<Move> legalMoves, final Collection<Move> oppMoves) {
        List<Move> legalXOppMoves = new ArrayList<>();
        legalXOppMoves.addAll(legalMoves);
        legalXOppMoves.addAll(calcKingCastles(legalMoves, oppMoves));
        return legalXOppMoves;
    }

    public King getPlayerKing() {
        return this.playerKing;
    }

    public Collection<Move> getlegalMoves() {
        return this.legalMoves;
    }

    protected static Collection<Move> calcAtkOnTile(int piecePos, Collection<Move> oppMoves) {
        final List<Move> atkMoves = new ArrayList<>();

        for (final Move move : oppMoves) {
            if (piecePos == move.getNextMoveCoords()) {
                atkMoves.add(move);
            }
        }

        return Collections.unmodifiableCollection(atkMoves);
    }

    private King establishKing() {

        for (final Piece piece : getActivePieces()) {
            if (piece.getPieceType().isKing()) {
                return (King) piece;
            }
        }

        throw new Exception(null, "should no reach here Not a valid board");
    }

    public boolean isMoveLegal(final Move move) {
        return this.legalMoves.contains(move);
    }

    public boolean isInCheck() {
        return this.isInCheck;
    }

    public boolean isInCheckmate() {
        return this.isInCheck && !hasEscapeMoves();
    }

    public boolean isInStalemate() {
        return !this.isInCheck && !hasEscapeMoves();
    }

    // TODO implement
    public boolean isCastled() {
        return false;
    }

    public MoveTrans makeMove(final Move move) {

        if (!isMoveLegal(move)) {
            return new MoveTrans(this.board, move, MoveStatus.IllegalMove);
        }

        final Board transBoard = move.exc();

        final Collection<Move> kingAtks = Player.calcAtkOnTile(
                transBoard.currentPlayer().getOpp().getPlayerKing().getPiecePos(),
                transBoard.currentPlayer().getlegalMoves());

        if (!kingAtks.isEmpty()) {
            return new MoveTrans(transBoard, move, MoveStatus.LeavesPlayerInCheck);
        }

        return new MoveTrans(transBoard, move, MoveStatus.DONE);
    }

    protected boolean hasEscapeMoves() {

        for (final Move move : this.legalMoves) {
            final MoveTrans trans = makeMove(move);
            if (trans.getMoveStatus().isDone()) {
                return true;
            }
        }

        return false;
    }

    public abstract Collection<Piece> getActivePieces();

    public abstract Alliance getAlliance();

    public abstract Player getOpp();

    protected abstract Collection<Move> calcKingCastles(final Collection<Move> plaerLegals,
            final Collection<Move> oppLegals);
}
