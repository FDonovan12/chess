package fr.donovan.chess;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static fr.donovan.chess.Game.playAGame;

public class Main {

    public static void main(String[] args) {
        playAGame();
    }
    public static void printMove(Cell startCell, Cell endCell, String string) {
        System.out.println(string + " " + startCell + " " + endCell);
    }

}