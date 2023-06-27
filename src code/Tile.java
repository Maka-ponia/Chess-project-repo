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
}
