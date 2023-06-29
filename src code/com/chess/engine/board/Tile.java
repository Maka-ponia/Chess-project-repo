package com.chess.engine.board;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.chess.engine.Pieces.Piece;

public abstract class Tile {
    protected final int tileCoords;
    private static final Map<Integer, EmptyTile> EmptyTileMap = createAllPossEmptyTile();

    private Tile(int tileCoords) {
        this.tileCoords = tileCoords;
    }

    // Returns if a tile is Occupied
    public abstract boolean isTileOccupied();

    // Returns the piece on tile
    public abstract Piece getPiece();

    public static Tile createTle(final int titleCoordinate, final Piece piece) {
        // In the og design the return statments would use a ? opperation where there
        // would be
        // a condition given and depending on if it was true or false woud retuen
        // different things
        // I used and if and else statment cause i am more familar with it and makes
        // more sense
        // semantically to me.
        if (piece != null) {

            return new OccupiedTile(titleCoordinate, piece);

        } else {

            return EmptyTileMap.get(titleCoordinate);

        }
    }

    private static Map<Integer, EmptyTile> createAllPossEmptyTile() {

        final Map<Integer, EmptyTile> EmptyTileMap = new HashMap<>();

        for (int index = 0; index < 64; index++) {
            EmptyTileMap.put(index, new EmptyTile(index));
        }
        // In the og design the method would return a ImuntableMap.copyOf(EmptyTileMap)
        // version of EmptyTileMap
        // which would prevent EmptyTileMap from being edited when referenced. I couldnt
        // be dammed to downlad
        // the libary and figure out how to use the libary so ill just wait for lab dem
        // help otherwise this should do the same thing.
        return Collections.unmodifiableMap(EmptyTileMap);
    }

    public static final class EmptyTile extends Tile {

        private EmptyTile(final int tileCoords) {
            super(tileCoords);
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }

    public static final class OccupiedTile extends Tile {

        private final Piece pieceOnTile;

        OccupiedTile(int tileCoords, Piece pieceOnTile) {
            super(tileCoords);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return this.pieceOnTile;
        }

    }
}
