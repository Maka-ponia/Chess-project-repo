package com.chess.engine.Pieces;

import java.util.List;
import com.chess.engine.Alliance;
import com.chess.engine.board.Move;
import com.chess.engine.board.Board;

public abstract class Piece {
    protected final int pieceCoords;
    protected final Alliance pieceSide;

    Piece(final int pieceCoords, final Alliance pieceSide) {
        this.pieceCoords = pieceCoords;
        this.pieceSide = pieceSide;
}
public abstract List<Move> calcLegalmMoves(final Board board);
}
