package com.manhalrahman.gameengine.purejava.game;

public class Move {
    private Cell cell;
    private Player player;
    public Move(Cell cell, Player player) {
        this.cell = cell;
        this.player = player;
    }

    public Cell getCell() {
        return this.cell;
    }
}
