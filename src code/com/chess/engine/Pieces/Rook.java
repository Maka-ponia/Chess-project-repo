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

public class Rook extends Piece {

    // if we image a 8X8 board with the top left being 1 counting left to right then
    // these numbers
    // plus the Rook current coord are each position that the Rook could jump
    // to
    // i.e Rook coords : 31 then the RookPossMoves = Rook coords +
    // possibleCoordsOffsets(i)
    private final static int[] possibleCoordsOffsets = { -8, -1, 1, 8 };

    public Rook(int pieceCoords, Alliance pieceSide) {
        super(pieceCoords, pieceSide);
    }

    // Creates a collection of Moves that the Rook can do based of its current
    // postion.
    // Stores the piece current position in possibleNextMoveCoords and has one of
    // the offset
    // applied to the possibleNextMoveCoords. The results are stored in
    // possibleNextMoveTile.
    // while possibleNextMoveTile is a vaild board coord the tile at
    // possibleNextMoveTile
    // is checked for what moves can be made. If the tile is unoccupied then a major
    // move
    // i.e Moving from one tile to another, is added to the list of possible moves.
    // In this case
    // the while loop continus. If the tile is occupied then different conditions
    // are applied.
    // if the tile is occupied by a foe then an attacking move i.e replaceing the
    // enemy piece at the
    // possibleNextMoveCoords, is added to the list of possible moves. if it is
    // occupied by friend then
    // no moves are added to the list. In both case the while loop is broken and the
    // next offset is appiled
    // to the coords of the Rook to repeat the proccess all over.
    @Override
    public Collection<Move> calcLegalmMoves(final Board board) {
        final Collection<Move> legalMoves = new ArrayList<>();

        for (final int currentOffset : possibleCoordsOffsets) {
            int possibleNextMoveCoords = this.pieceCoords;

            while (BoardUtils.isValidTileCoord(possibleNextMoveCoords)) {
                possibleNextMoveCoords += currentOffset;

                if (isFirstColumnExlusion(possibleNextMoveCoords, currentOffset)
                        || isEightThColumnExlusion(possibleNextMoveCoords, currentOffset)) {
                    break;
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
                        break;
                    }
                }
            }
        }
        return Collections.unmodifiableCollection(legalMoves);
    }

    // Is used to check if the Rook on the first column are able to move to
    // certian possitions
    private static boolean isFirstColumnExlusion(final int currentCoords, final int possibleOffset) {
        return BoardUtils.FirstColumn[currentCoords] && (possibleOffset == -1);
    }

    // Is used to check if the Rook on the eigthth column are able to move to
    // certian possitions
    private static boolean isEightThColumnExlusion(final int currentCoords, final int possibleOffset) {
        return BoardUtils.EightthColumn[currentCoords] && (possibleOffset == 1);
    }

}
