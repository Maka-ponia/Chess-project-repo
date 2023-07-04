package com.chess.engine.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.chess.engine.Alliance;
import com.chess.engine.pieces.Bishop;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Knight;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Queen;
import com.chess.engine.pieces.Rook;

public class Board {

    private final List<Tile> gameBoard;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;

    private Board(Builder builder) {
        this.gameBoard = makeGameBoard(builder);
        this.whitePieces = calcActivePieces(this.gameBoard, Alliance.White);
        this.blackPieces = calcActivePieces(this.gameBoard, Alliance.Black);
    }

    private Collection<Piece> calcActivePieces(List<Tile> gameBoard2, Alliance alliance) {

        final List<Piece> activepieces = new ArrayList<>();

        for (final Tile tile : gameBoard) {
            if (tile.isTileOccupied()) {
                final Piece piece = tile.getPiece();
                if (piece.getPieceSide() == alliance) {
                    activepieces.add(piece);
                }
            }
        }

        return Collections.unmodifiableList(activepieces);
    }

    public Tile getTile(int possibleNextMoveCoords) {
        return gameBoard.get(possibleNextMoveCoords);
    }

    private List<Tile> makeGameBoard(Builder builder) {
        final Tile[] tilesArray = new Tile[BoardUtils.numTitles];

        for (int index = 0; index < BoardUtils.numTitles; index++) {
            tilesArray[index] = Tile.createTle(index, builder.boardConfig.get(index));
        }

        List<Tile> tiles = Arrays.asList(tilesArray);

        return Collections.unmodifiableList(tiles);
    }

    public static Board makeBasicBoard() {
        final Builder builder = new Builder();

        builder.setPiece(new Rook(0, Alliance.Black));
        builder.setPiece(new Knight(1, Alliance.Black));
        builder.setPiece(new Bishop(2, Alliance.Black));
        builder.setPiece(new Queen(3, Alliance.Black));
        builder.setPiece(new King(4, Alliance.Black));
        builder.setPiece(new Bishop(5, Alliance.Black));
        builder.setPiece(new Knight(6, Alliance.Black));
        builder.setPiece(new Rook(7, Alliance.Black));
        builder.setPiece(new Pawn(8, Alliance.Black));
        builder.setPiece(new Pawn(9, Alliance.Black));
        builder.setPiece(new Pawn(10, Alliance.Black));
        builder.setPiece(new Pawn(11, Alliance.Black));
        builder.setPiece(new Pawn(12, Alliance.Black));
        builder.setPiece(new Pawn(13, Alliance.Black));
        builder.setPiece(new Pawn(14, Alliance.Black));
        builder.setPiece(new Pawn(15, Alliance.Black));

        builder.setPiece(new Rook(63, Alliance.White));
        builder.setPiece(new Knight(62, Alliance.White));
        builder.setPiece(new Bishop(61, Alliance.White));
        builder.setPiece(new Queen(60, Alliance.White));
        builder.setPiece(new King(59, Alliance.White));
        builder.setPiece(new Bishop(58, Alliance.White));
        builder.setPiece(new Knight(57, Alliance.White));
        builder.setPiece(new Rook(56, Alliance.White));
        builder.setPiece(new Pawn(55, Alliance.White));
        builder.setPiece(new Pawn(54, Alliance.White));
        builder.setPiece(new Pawn(53, Alliance.White));
        builder.setPiece(new Pawn(52, Alliance.White));
        builder.setPiece(new Pawn(51, Alliance.White));
        builder.setPiece(new Pawn(50, Alliance.White));
        builder.setPiece(new Pawn(49, Alliance.White));
        builder.setPiece(new Pawn(48, Alliance.White));

        builder.setMoveMaker(Alliance.White);

        return builder.Build();
    }

    public static class Builder {

        Map<Integer, Piece> boardConfig;
        Alliance nextMoveMaker;

        public Builder() {
        }

        public Builder setPiece(final Piece piece) {
            this.boardConfig.put(piece.getPiecePos(), piece);
            return this;
        }

        public Builder setMoveMaker(final Alliance nextMoveMaker) {
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }

        public Board Build() {
            return new Board(this);
        }
    }

}
