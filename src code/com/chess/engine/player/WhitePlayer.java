package com.chess.engine.player;

import java.util.Collection;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.Piece;

public class WhitePlayer extends Player {

    public WhitePlayer(Board board, Collection<Move> whiteStanderdLegalMoves,
            Collection<Move> blackStanderdLegalMoves) {
        super(board, whiteStanderdLegalMoves, blackStanderdLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePiece();
    }

}
