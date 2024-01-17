package fr.donovan.chess;

import static fr.donovan.chess.Game.playAGame;

public class MainChess {
    public static void main(String[] args) {
        playAGame();
    }
    public static void printMove(Cell startCell, Cell endCell, String string) {
        System.out.println(string + " " + startCell + " " + endCell);
    }
}
