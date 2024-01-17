package fr.donovan.chess.exception;

public class BadInputException extends Exception{
    public BadInputException() {
        System.out.println(" L'input de chaque doit etre de la forme 'A2 A4'");
    }
}
