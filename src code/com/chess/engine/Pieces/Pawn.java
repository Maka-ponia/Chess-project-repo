package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.MajorMove;

public class Pawn extends Piece {

    // if we image a 8X8 board with the top left being 1 counting left to right then
    // these numbers
    // plus the Pawn current coord are each position that the Pawn could jump
    // to
    // i.e Pawn coords : 31 then the PawnPossMoves = Pawn coords +
    // possibleCoordsOffsets(i)
    private final static int[] possibleCoordsOffsets = { 8 };

    Pawn(int pieceCoords, final Alliance pieceSide) {
        super(pieceCoords, pieceSide);
    }

    @Override
    public Collection<Move> calcLegalmMoves(final Board board) {
        final Collection<Move> legalMoves = new ArrayList<>();

        for (final int currentOffset : possibleCoordsOffsets) {
            final int possibleNextMoveCoords = this.pieceCoords + (this.getPieceSide().getDirection() * currentOffset);

            if (!BoardUtils.isValidTileCoord(possibleNextMoveCoords)) {
                continue;
            }

            if (currentOffset == 8 && !board.getTile(possibleNextMoveCoords).isTileOccupied()) {
                legalMoves.add(new MajorMove(board, this, possibleNextMoveCoords));

            } else if (currentOffset == 16 && this.isFirstMove() && (BoardUtils.SecondRow[this.pieceCoords])
                    && this.getPieceSide().isBlack()
                    || BoardUtils.SeventhRow[this.pieceCoords] && this.getPieceSide().isWhite()) {
                final int behindPossibleNextMoveCoords = this.pieceCoords + (this.pieceSide.getDirection() * 8);

                if (!board.getTile(behindPossibleNextMoveCoords).isTileOccupied()
                        && !board.getTile(possibleNextMoveCoords).isTileOccupied()) {
                    legalMoves.add(new MajorMove(board, this, possibleNextMoveCoords));
                }
            }
        }
        return null;
    }

    public boolean isFirstMove() {
        return false;
    }

}
