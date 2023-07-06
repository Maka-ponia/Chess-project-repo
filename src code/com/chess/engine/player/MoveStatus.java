package com.chess.engine.player;

public enum MoveStatus {
    DONE {
        @Override
        public boolean isDone() {
            return true;
        }
    },
    IllegalMove {
        @Override
        public boolean isDone() {
            return false;
        }
    },
    LeavesPlayerInCheck {
        @Override
        public boolean isDone() {
            return false;
        }
    };

    public abstract boolean isDone();
}
