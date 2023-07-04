package com.chess.engine.board;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.chess.engine.Alliance;
import com.chess.engine.pieces.Piece;

public class Board {

    private final List<Tile> gameBoard;

    public Board(Builder builder) {
        this.gameBoard = makeGameBoard(builder);
    }

    public Tile getTile(int possibleNextMoveCoords) {
        return null;
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
        return null;
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
