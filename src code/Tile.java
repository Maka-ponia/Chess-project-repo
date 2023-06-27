public abstract class Tile {
    int tileCoords;

    Tile(int tileCoords) {
        this.tileCoords = tileCoords;
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();

    public static final class EmptyTile extends Tile {

        EmptyTile(int coords) {
            super(coords);
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
