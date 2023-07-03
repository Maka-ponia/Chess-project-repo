package com.chess.engine.board;

public class BoardUtils {

    // Creates a an array of boolean where the indexs of the array is used as the
    // coords of the board. If a piece is on a coord we can reference that piece
    // coord againest the arrays and determine if its in the first, second etc etc
    // column of the board (a column is down and a row is across dumb dumb)
    public static final boolean[] FirstColumn = intColumn(0);
    public static final boolean[] SecondColumn = intColumn(1);
    public static final boolean[] SeventhColumn = intColumn(6);
    public static final boolean[] EightthColumn = intColumn(7);
    public static final int numTitles = 64;
    public static final int numTitlesPerRow = 8;

    // Somthing or other idk idk
    private BoardUtils() {
        throw new RuntimeException("You cannot instantiate me!");
    }

    // A generic method used to fill the column array with true statments depending
    // on which column is used
    private static boolean[] intColumn(int columnNumber) {
        final boolean[] column = new boolean[64];
        do {
            column[columnNumber] = true;
            columnNumber += 8;
        } while (columnNumber < 64);

        return column;
    }

    // Is given a possible coord that a piece could
    // go and cheacks if its a place on the board
    public static boolean isValidTileCoord(final int possibleNextMoveCoords) {
        if (possibleNextMoveCoords >= 0 && possibleNextMoveCoords < 64) {
            return true;
        } else {
            return false;
        }
    }
}