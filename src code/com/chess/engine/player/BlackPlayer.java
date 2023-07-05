package com.chess.engine.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Piece;

public class BlackPlayer extends Player {

    public BlackPlayer(final Board board, final Collection<Move> whiteStanderdLegalMoves,
            final Collection<Move> blackStanderdLegalMoves) {
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

    @Override
    protected Collection<Move> calcKingCastles(Collection<Move> plaerLegals, Collection<Move> oppLegals) {
        final List<Move> kingCastle = new ArrayList<>();
        if (this.playerKing.isFirstMove() && !this.isInCheck()) {
            // blck king side castle
            if (!this.board.getTile(5).isTileOccupied() && !this.board.getTile(6).isTileOccupied()) {
                final Tile rookeTile = this.board.getTile(7);
                if (rookeTile.isTileOccupied() && rookeTile.getPiece().isFirstMove()) {
                    // TODO
                    if (Player.calcAtkOnTile(5, oppLegals).isEmpty() && Player.calcAtkOnTile(6, oppLegals).isEmpty()
                            && rookeTile.getPiece().getPieceType().isRook()) {

                    }
                    kingCastle.add(null);
                }
            }
            if (!this.board.getTile(1).isTileOccupied() && !this.board.getTile(2).isTileOccupied()
                    && !this.board.getTile(3).isTileOccupied()) {

                final Tile rookTile = this.board.getTile(0);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    // TODO add castle mov
                    kingCastle.add(null);
                }
            }
        }

        return Collections.unmodifiableCollection(kingCastle);
    }
}
