public abstract class Tile {
    int tileCoords;

    Tile(int tileCoords) {
        this.tileCoords = tileCoords;
    }

    // Returns if a tile is Occupied
    public abstract boolean isTileOccupied();

    // Returns the piece on tile
    public abstract Piece getPiece();

    public static final class EmptyTile extends Tile {

        EmptyTile(int tileCoords) {
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

        Piece pieceOnTile;

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
