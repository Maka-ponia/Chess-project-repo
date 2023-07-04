package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.AtkMove;
import com.chess.engine.board.Move.MajorMove;
import com.chess.engine.board.Tile;

public class King extends Piece {

    // if we image a 8X8 board with the top left being 1 counting left to right then
    // these numbers
    // plus the King current coord are each position that the King could jump
    // to
    // i.e King coords : 31 then the KingPossMoves = King coords +
    // possibleCoordsOffsets(i)
    private final static int[] possibleCoordsOffsets = { 1, 7, 8, 9, -1, -7, -8, -9 };

    King(int pieceCoords, Alliance pieceSide) {
        super(pieceCoords, pieceSide);
    }

    @Override
    public Collection<Move> calcLegalmMoves(Board board) {
        Collection<Move> legalMoves = new ArrayList<>();

        for (final int currentOffset : possibleCoordsOffsets) {
            final int possibleNextMoveCoords = this.pieceCoords + currentOffset;

            if (isEightthColumnExlusion(this.pieceCoords, currentOffset)
                    || isFirstColumnExlusion(this.pieceCoords, currentOffset)) {
                continue;
            }

            if (BoardUtils.isValidTileCoord(possibleNextMoveCoords)) {
                final Tile possibleNextMoveTile = board.getTile(possibleNextMoveCoords);

                if (!possibleNextMoveTile.isTileOccupied()) {
                    legalMoves.add(new MajorMove(board, this, possibleNextMoveCoords));

                } else {
                    final Piece pieceAtDestination = possibleNextMoveTile.getPiece();
                    final Alliance pieceSide = pieceAtDestination.getPieceSide();

                    if (this.pieceSide != pieceSide) {
                        legalMoves.add(new AtkMove(board, this, pieceAtDestination, possibleNextMoveCoords));
                    }
                }
            }

        }
        return Collections.unmodifiableCollection(legalMoves);
    }

    // Is used to check if the King on the first column are able to move to
    // certian possitions
    private static boolean isFirstColumnExlusion(final int currentCoords, final int possibleOffset) {
        return BoardUtils.FirstColumn[currentCoords]
                && (possibleOffset == -9 || possibleOffset == -1 || possibleOffset == 7);
    }

    // Is used to check if the King on the second column are able to move to
    // certian possitions
    private static boolean isEightthColumnExlusion(final int currentCoords, final int possibleOffset) {
        return BoardUtils.EightthColumn[currentCoords]
                && (possibleOffset == 9 || possibleOffset == 1 || possibleOffset == -7);
    }

}
