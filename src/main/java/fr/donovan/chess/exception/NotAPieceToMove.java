package fr.donovan.chess.exception;

public class NotAPieceToMove extends Exception {
    public NotAPieceToMove() {
        System.out.println("Vous ne posséder pas de piéce sur cette case");
    }
}
