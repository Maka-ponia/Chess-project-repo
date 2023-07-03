package com.chess.engine;

public enum Alliance {
    // i think this is what is used to determine if a piece is white or black
    White {
        @Override
        public int getDirection() {
            return -1;
        }
    },
    Black {
        @Override
        public int getDirection() {
            return 1;
        }
    };

    public abstract int getDirection();

}
