package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;

public class Knight extends Piece {

    // if we image a 8X8 board with the top left being 1 counting left to right then
    // these numbers
    // plus the Knights current coord are each position that the knight could jump
    // to
    // i.e knight coords : 31 then the knighPossMoves = knght coords +
    // possibleMoveCoords(i)
    private final static int[] possibleMoveCoords = { -17, -15, -10, -6, 6, 10, 15, 17 };

    Knight(int pieceCoords, Alliance pieceSide) {
        super(pieceCoords, pieceSide);
    }

    @Override
    public List<Move> calcLegalmMoves(Board board) {

        int possibleNextMoveCoords;
        List<Move> legalMoves = new ArrayList<>();

        for (final int currentPossMoveCoords : possibleMoveCoords) {

            possibleNextMoveCoords = this.pieceCoords + currentPossMoveCoords;

            if (true) {

                final Tile possibleNextMoveTile = board.getTile(possibleNextMoveCoords);
                if (!possibleNextMoveTile.isTileOccupied()) {
                    legalMoves.add(new Move());
                }

                else {

                    final Piece pieceAtDestination = possibleNextMoveTile.getPiece();
                    final Alliance pieceSide = pieceAtDestination.getPieceSide();

                    if (this.pieceSide != pieceSide) {
                        legalMoves.add(new Move());
                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }
}
