package com.chess.engine.player;

import java.util.Collection;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.Piece;

public class BlackPlayer extends Player {

    public BlackPlayer(Board board, Collection<Move> whiteStanderdLegalMoves,
            Collection<Move> blackStanderdLegalMoves) {
        super(board, blackStanderdLegalMoves, whiteStanderdLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPiece();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.Black;
    }

    @Override
    public Player getOpp() {
        return this.board.whitePlayer();
    }
}
