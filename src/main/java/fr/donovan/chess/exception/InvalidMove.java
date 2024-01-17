package fr.donovan.chess.exception;

import fr.donovan.chess.piece.*;

public class InvalidMove extends Exception {
    public InvalidMove(Queen piece) {
        System.out.println(" Invalid move for the Queen");
        System.out.println(" The Queen move on multiple cells in rows or diagonals");
    }
    public InvalidMove(King piece) {
        System.out.println(" Invalid move for the King");
        System.out.println(" The King move of one cell in rows or diagonals");
    }
    public InvalidMove(Rook piece) {
        System.out.println(" Invalid move for the Rook");
        System.out.println(" The Rook move on multiple cells in rows");
    }
    public InvalidMove(Knight piece) {
        System.out.println(" Invalid move for the Knight");
        System.out.println(" The Knight move with 2 move horizontal and 1 vertical or");
        System.out.println(" 1 move horizontal and 2 vertical");
    }
    public InvalidMove(Bishop piece) {
        System.out.println(" Invalid move for the Bishop");
        System.out.println(" The Bishop move on multiple cells in diagonals");
    }
    public InvalidMove(Pawn piece) {
        System.out.println(" Invalid move for the Pawn");
        System.out.println(" The Pawn move on one cell in front of the piece or");
        System.out.println(" he can take an opponent piece in one cell diagonals in front of the piece ");
    }
    public InvalidMove(Boolean bool) {
        System.out.println(" Invalid move ");
        System.out.println(" A piece is on the path ");
    }
    public InvalidMove() {
        System.out.println(" Invalid move ");
        System.out.println(" You can't take you own piece ");
    }
}
