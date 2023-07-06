package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.MajorMove;

public class Pawn extends Piece {

    public void check() {
        boolean condition;
        do {
            System.out.println(getClass());
            condition = false;
        } while (condition);

    }

    // if we image a 8X8 board with the top left being 1 counting left to right then
    // these numbers
    // plus the Pawn current coord are each position that the Pawn could jump
    // to
    // i.e Pawn coords : 31 then the PawnPossMoves = Pawn coords +
    // possibleCoordsOffsets(i)
    private final static int[] possibleCoordsOffsets = { 8, 16, 7, 9 };

    public Pawn(int pieceCoords, final Alliance pieceSide) {
        super(PieceType.PAWN, pieceCoords, pieceSide);
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
                // TODO more work to be done here
                legalMoves.add(new MajorMove(board, this, possibleNextMoveCoords));

            } else if (currentOffset == 16 && this.isFirstMove() && (BoardUtils.SeventhRank[this.pieceCoords])
                    && this.getPieceSide().isBlack()
                    || BoardUtils.SecondRank[this.pieceCoords] && this.getPieceSide().isWhite()) {
                final int behindPossibleNextMoveCoords = this.pieceCoords + (this.pieceSide.getDirection() * 8);

                if (!board.getTile(behindPossibleNextMoveCoords).isTileOccupied()
                        && !board.getTile(possibleNextMoveCoords).isTileOccupied()) {
                    legalMoves.add(new MajorMove(board, this, possibleNextMoveCoords));
                }

            } else if (currentOffset == 7 && !(BoardUtils.EightthColumn[this.pieceCoords] && this.pieceSide.isWhite())
                    || (BoardUtils.FirstColumn[this.pieceCoords] && this.pieceSide.isBlack())) {
                if (board.getTile(possibleNextMoveCoords).isTileOccupied()) {
                    final Piece pieceOnPossibleTile = board.getTile(possibleNextMoveCoords).getPiece();

                    if (pieceOnPossibleTile.getPieceSide() != this.pieceSide) {
                        // TODO more work to be done here
                        legalMoves.add(new MajorMove(board, this, possibleNextMoveCoords));
                    }
                }

            } else if (currentOffset == 9 && !(BoardUtils.FirstColumn[this.pieceCoords] && this.pieceSide.isWhite())
                    || (BoardUtils.EightthColumn[this.pieceCoords] && this.pieceSide.isBlack())) {
                if (board.getTile(possibleNextMoveCoords).isTileOccupied()) {
                    final Piece pieceOnPossibleTile = board.getTile(possibleNextMoveCoords).getPiece();

                    if (pieceOnPossibleTile.getPieceSide() != this.pieceSide) {
                        // TODO more work to be done here
                        legalMoves.add(new MajorMove(board, this, possibleNextMoveCoords));
                    }
                }
            }
        }
        return Collections.unmodifiableCollection(legalMoves);
    }

    @Override
    public String toString() {
        return PieceType.PAWN.toString();
    }

    public boolean isFirstMove() {
        return false;
    }

    @Override
    public Pawn movPiece(final Move move) {
        return new Pawn(move.getNextMoveCoords(), move.getMovedpiece().getPieceSide());
    }
}
