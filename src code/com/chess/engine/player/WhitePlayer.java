package com.chess.engine.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.board.Move.castleMove;
import com.chess.engine.board.Move.castleMove.kingSideCastleMove;
import com.chess.engine.board.Move.castleMove.queenSideCastleMove;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;

public class WhitePlayer extends Player {

    public WhitePlayer(final Board board, final Collection<Move> whiteStanderdLegalMoves,
            final Collection<Move> blackStanderdLegalMoves) {
        super(board, whiteStanderdLegalMoves, blackStanderdLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePiece();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.White;
    }

    @Override
    public Player getOpp() {
        return this.board.blackPlayer();
    }

    @Override
    protected Collection<Move> calcKingCastles(final Collection<Move> plaerLegals, final Collection<Move> oppLegals) {

        final List<Move> kingCastle = new ArrayList<>();
        if (this.playerKing.isFirstMove() && !this.isInCheck()) {
            // white king side castle
            if (!this.board.getTile(61).isTileOccupied() && !this.board.getTile(62).isTileOccupied()) {
                final Tile rookTile = this.board.getTile(63);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    // TODO
                    if (Player.calcAtkOnTile(61, oppLegals).isEmpty() && rookTile.getPiece().isRook()) {

                    }
                    kingCastle.add(new kingSideCastleMove(this.board, this.playerKing, 62,
                            (Rook) (rookTile.getPiece()), rookTile.getTileCoords(), 61));
                }
            }
            if (!this.board.getTile(59).isTileOccupied() && !this.board.getTile(58).isTileOccupied()
                    && !this.board.getTile(57).isTileOccupied()) {

                final Tile rookTile = this.board.getTile(56);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()
                        && Player.calcAtkOnTile(58, oppLegals).isEmpty()
                        && Player.calcAtkOnTile(59, oppLegals).isEmpty()
                        && rookTile.getPiece().getPieceType().isRook()) {
                    kingCastle.add(new queenSideCastleMove(this.board, this.playerKing, 58,
                            (Rook) (rookTile.getPiece()), rookTile.getTileCoords(), 59));
                }
            }
        }

        return Collections.unmodifiableCollection(kingCastle);
    }
}
