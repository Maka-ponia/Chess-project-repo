package com.chess.engine.player;

import java.util.Collection;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.Piece;

public class WhitePlayer extends Player {

    public WhitePlayer(final Board board, final Collection<Move> whiteStanderdLegalMoves,
            final Collection<Move> blackStanderdLegalMoves) {
        super(board, whiteStanderdLegalMoves, blackStanderdLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePiece();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.White;
    }

    @Override
    public Player getOpp() {
        return this.board.blackPlayer();
    }
}