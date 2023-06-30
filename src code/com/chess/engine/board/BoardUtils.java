package com.chess.engine.board;

public class BoardUtils {

    public static final boolean[] FirstColumn = null;
    public static final boolean[] SecondColumn = null;
    public static final boolean[] SeventhColumn = null;
    public static final boolean[] EightthColumn = null;

    private BoardUtils() {
        throw new RuntimeException("You cannot instantiate me!");
    }

    // Is given a possible coord that a piece could
    // go and cheacks if its a place on the board
    public static boolean isValidTileCoord(int possibleNextMoveCoords) {
        if (possibleNextMoveCoords >= 0 && possibleNextMoveCoords < 64) {
            return true;
        } else {
            return false;
        }
    }
}