package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.board.Move.AtkMove;
import com.chess.engine.board.Move.MajorMove;

public class Knight extends Piece {

    public void check() {
        boolean condition;
        do {
            System.out.println(getClass());
            condition = false;
        } while (condition);

    }

    // if we image a 8X8 board with the top left being 1 counting left to right then
    // these numbers
    // plus the Knights current coord are each position that the knight could jump
    // to
    // i.e knight coords : 31 then the knighPossMoves = knght coords +
    // possibleCoordsOffsets(i)
    private final static int[] possibleCoordsOffsets = { -17, -15, -10, -6, 6, 10, 15, 17 };

    public Knight(int pieceCoords, Alliance pieceSide) {
        super(pieceCoords, pieceSide);
    }

    // Creates a collection of Moves that the knight can do based of its current
    // postion.
    // Gets the Knghts current cords and applies the offset to it i.e
    // possibleNextMoveCoords.
    // After checks if possibleNextMoveCoords is a vaild tile coord. If it is a
    // vaild tile coord
    // then we look at the title and check it. If the tile is not Occupied then a
    // major move
    // i.e Moving from one tile to another, is added to the list of possible moves.
    // If it is
    // occupied then an attack move i.e replaceing the enemy piece at the
    // possibleNextMoveCoords,
    // is added to the list of possible moves. This is done for each offset against
    // the piece
    // current coors.
    public Collection<Move> calcLegalmMoves(final Board board) {
        Collection<Move> legalMoves = new ArrayList<>();

        for (final int currentOffset : possibleCoordsOffsets) {
            final int possibleNextMoveCoords = this.pieceCoords + currentOffset;

            if (BoardUtils.isValidTileCoord(possibleNextMoveCoords)) {

                if (isFirstColumnExlusion(this.pieceCoords, currentOffset)
                        || isSecondColumnExlusion(this.pieceCoords, currentOffset)
                        || isSeventhColumnExlusion(this.pieceCoords, currentOffset)
                        || isEightthColumnExlusion(this.pieceCoords, currentOffset)) {
                    continue;
                }

                final Tile possibleNextMoveTile = board.getTile(possibleNextMoveCoords);

                if (!possibleNextMoveTile.isTileOccupied()) {
                    legalMoves.add(new MajorMove(board, this, possibleNextMoveCoords));
                }

                else {
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

    @Override
    public String toString() {
        return PieceType.KNIGHT.toString();
    }

    // Is used to check if the knights on the first column are able to move to
    // certian possitions
    private static boolean isFirstColumnExlusion(final int currentCoords, final int possibleOffset) {
        return BoardUtils.FirstColumn[currentCoords]
                && (possibleOffset == -17 || possibleOffset == -10 || possibleOffset == 6 || possibleOffset == 15);
    }

    // Is used to check if the knights on the second column are able to move to
    // certian possitions
    private static boolean isSecondColumnExlusion(final int currentCoords, final int possibleOffset) {
        return BoardUtils.SecondColumn[currentCoords] && (possibleOffset == -10 || possibleOffset == 6);
    }

    // Is used to check if the knights on the seventh column are able to move to
    // certian possitions
    private static boolean isSeventhColumnExlusion(final int currentCoords, final int possibleOffset) {
        return BoardUtils.SeventhColumn[currentCoords] && (possibleOffset == -16 || possibleOffset == 10);
    }

    // Is used to check if the knights on the eightth column are able to move to
    // certian possitions
    private static boolean isEightthColumnExlusion(final int currentCoords, final int possibleOffset) {
        return BoardUtils.EightthColumn[currentCoords]
                && (possibleOffset == -15 || possibleOffset == -6 || possibleOffset == 10 || possibleOffset == 17);
    }

}
