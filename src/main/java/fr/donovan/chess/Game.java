package fr.donovan.chess;

import fr.donovan.chess.exception.BadInputException;
import fr.donovan.chess.exception.InputNotInTheChess;
import fr.donovan.chess.exception.NotAPieceToMove;
import fr.donovan.chess.piece.*;

import java.util.*;

public class Game {
    //    private List<Piece> pieces;
    private ArrayList<ArrayList<Cell>> board;
    private Cell lastMoveCell;
    private Cell selectedCell;
    private Piece lastMovePiece;
    private Color currentPlayer;

    public ArrayList<ArrayList<Cell>> getBoard() {
        return board;
    }

    public void setBoard(ArrayList<ArrayList<Cell>> board) {
        this.board = board;
    }
    public void setCurrentPlayer(Color currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Game() {
        this.addCase();
        this.addPiece();
    }
    public Game(Game game) {
        this.board = (ArrayList<ArrayList<Cell>>) game.board.clone();
        this.lastMoveCell = game.lastMoveCell;
        this.selectedCell = game.selectedCell;
        this.lastMovePiece = game.lastMovePiece;
        this.currentPlayer = game.currentPlayer;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    private void addCase() {
        ArrayList<ArrayList<Cell>> board = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            board.add(new ArrayList<Cell>());
            for (int j = 0; j < 8; j++) {
                Color color = (i + j) % 2 == 0 ? Color.BLACK : Color.WHITE;
                Cell square = new Cell(color, i, j);
                board.get(i).add(square);
            }
        }
        this.board = board;
    }

    public void resetCase() {
//        System.out.println("Reset");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Color color = (i + j) % 2 == 0 ? Color.BLACK : Color.WHITE;
                if (board.get(i).get(j).getColor() == Color.POSSIBLE || board.get(i).get(j).getColor() == Color.SELECTED) {
                    board.get(i).get(j).setColor(color);
                }
            }
        }
    }

    private void addPiece() {
        this.addRook();
        this.addKnight();
        this.addBishop();
        this.addPawn();
        this.addQueen();
        this.addKing();
//        Piece piece = new Pawn(Color.WHITE);
//        this.board.get(6).get(7).setPiece(piece);
//        Piece piece2 = new Queen(Color.WHITE);
//        this.board.get(5).get(3).setPiece(piece2);
    }

    private void addRook() {
        int[] lines = {0, 7};
        int[] columns = {0, 7};
        for (int line : lines) {
            for (int column : columns) {
                Color color = line <= 1 ? Color.WHITE : Color.BLACK;
                Piece piece = new Rook(color);
                this.board.get(line).get(column).setPiece(piece);
            }
        }
    }
    private void addKnight() {
        int[] lines = {0, 7};
        int[] columns = {1, 6};
        for (int line : lines) {
            for (int column : columns) {
                Color color = line <= 1 ? Color.WHITE : Color.BLACK;
                Piece piece = new Knight(color);
                this.board.get(line).get(column).setPiece(piece);
            }
        }
    }
    private void addBishop() {
        int[] lines = {0, 7};
        int[] columns = {2, 5};
        for (int line : lines) {
            for (int column : columns) {
                Color color = line <= 1 ? Color.WHITE : Color.BLACK;
                Piece piece = new Bishop(color);
                this.board.get(line).get(column).setPiece(piece);
            }
        }
    }
    private void addPawn() {
        int[] lines = {1, 6};
        int[] columns = {0, 1, 2, 3, 4, 5, 6, 7};
        for (int line : lines) {
            for (int column : columns) {
                Color color = line <= 1 ? Color.WHITE : Color.BLACK;
                Piece piece = new Pawn(color);
                this.board.get(line).get(column).setPiece(piece);
            }
        }
    }
    private void addQueen() {
        int[] lines = {0, 7};
        int column = 3;
        for (int line : lines) {
            Color color = line <= 1 ? Color.WHITE : Color.BLACK;
            Piece piece = new Queen(color);
            this.board.get(line).get(column).setPiece(piece);
        }
    }
    private void addKing() {
        int[] lines = {0, 7};
        int column = 4;
        for (int line : lines) {
            Color color = line <= 1 ? Color.WHITE : Color.BLACK;
            Piece piece = new King(color);
            this.board.get(line).get(column).setPiece(piece);
        }
    }

    public int gameIsOver() {
//        boolean whiteKingDead = true;
//        boolean blackKingDead = true;
        boolean isChecked = this.isChecked();
        int countMove = countMove();
        if (countMove == 0) {
            if (isChecked) {
                return 1;
            }
            return 0;
        }
        return -1;
    }

    public Boolean isChecked() {
        Cell kingCell = null;
        boolean isCheck = false;
        List<Cell> kingCellList = this.getBoard().stream().flatMap(Collection::stream)
                .filter(cell -> cell.getPiece() instanceof King && cell.getPiece().getColor() == currentPlayer).toList();
        List<Cell> boardFlat = this.getBoard().stream().flatMap(Collection::stream).toList();
        kingCellList = boardFlat.stream().filter(cell -> cell.getPiece() instanceof King && cell.getPiece().getColor() == currentPlayer).toList();
//        System.out.println(kingCellList);
//        System.out.println(boardFlat.size());
//        System.out.println(kingCellList.get(0) + " : " + kingCellList.get(0).getLine() + " : " + kingCellList.get(0).getColumn());
        kingCell = kingCellList.get(0);
//        for (ArrayList<Cell> lineEnd : this.getBoard()) {
//            for (Cell endCell : lineEnd) {
//                if (endCell.getPiece() != null && endCell.getPiece().getColor() == currentPlayer && endCell.getPiece() instanceof King) {
//                    kingCell = endCell;
//                    break;
//                }
//            }
//            if(kingCell != null) {
//                break;
//            }
//        }
//        System.out.println("kingCell : " + kingCell);
        if(kingCell == null) {
//            System.out.println(" King don't exist");
            return false;
        }
        return thisCellIsChecked(kingCell);
    }

    public Boolean thisCellIsChecked(Cell thisCell) {
        boolean isCheck = false;
        List<Cell> opponentPiece =  this.getBoard().stream().flatMap(Collection::stream)
                .filter(startCell -> startCell.getPiece() != null && startCell.getPiece().getColor() != currentPlayer).toList();
        List<Cell> opponentPieceCanChecked = opponentPiece.stream().filter(startCell -> startCell.getPiece().moveValide(startCell, thisCell, this, false)).toList();
        return !opponentPieceCanChecked.isEmpty();
//        for (Cell startCell : opponentPiece) {
//            isCheck = startCell.getPiece().moveValide(startCell, thisCell, this, false) || isCheck;
//        }
//        return !this.getBoard().stream().flatMap(Collection::stream)
//                .filter(startCell -> startCell.getPiece() != null && startCell.getPiece().getColor() != currentPlayer)
//                .filter(startCell -> startCell.getPiece().moveValide(startCell, thisCell, this, false)).toList().isEmpty();
//        for (ArrayList<Cell> lineStart : this.getBoard()) {
//            for (Cell startCell : lineStart) {
//                if (startCell.getPiece() != null && startCell.getPiece().getColor() != currentPlayer) {
//                    isCheck = startCell.getPiece().moveValide(startCell, thisCell, this, false);
//                    if (isCheck) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
    }

    public int countMove() {
        int count = 0;
        for (ArrayList<Cell> lineStart : this.getBoard()) {
            for (Cell startCell : lineStart) {
                if (startCell.getPiece() != null && startCell.getPiece().getColor() == currentPlayer) {
                    for (ArrayList<Cell> lineEnd : this.getBoard()) {
                        for (Cell endCell : lineEnd) {
                            if (startCell.getPiece().moveValide(startCell, endCell, this, false)) {
                                count++;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    public void playATour() {
        Scanner scan = new Scanner(System.in);
        boolean moved = false;
        do {
            try {
                boolean isChecked = this.isChecked();
                int countMove = countMove();
                if (isChecked) {
                    System.out.println(currentPlayer.getValuePiece() + " Player : " + currentPlayer + " is checked" + "\u001B[0m");
                }
                System.out.println(currentPlayer.getValuePiece() + " Player : " + currentPlayer + " what piece you want move");
                System.out.println(" You have " + countMove + " move possible" + "\u001B[0m");

                Cell startCell = getCellScan(currentPlayer, scan, true);
                this.selectedCell = startCell;
                this.selectedCell.setColor(Color.SELECTED);
                this.selectedCell.getPiece().possibleMove(startCell, this);
                System.out.println(this);
                System.out.println(currentPlayer.getValuePiece() + " Player : " + currentPlayer + " where you want to move this piece\u001B[0m");
                Cell endCell = getCellScan(currentPlayer, scan, false);
                moved = startCell.getPiece().move(startCell, endCell, this);

                Color colorCellSelected = (this.selectedCell.getLine() + this.selectedCell.getColumn()) % 2 == 0 ? Color.BLACK : Color.WHITE;
                this.selectedCell.setColor(colorCellSelected);

                if (this.lastMovePiece != null) {
                    Color colorCellMoved = (this.lastMoveCell.getLine() + this.lastMoveCell.getColumn()) % 2 == 0 ? Color.BLACK : Color.WHITE;
                    this.lastMoveCell.setColor(colorCellMoved);
                }
                this.lastMoveCell = startCell;
                this.lastMoveCell.setColor(Color.MOVE);

                if (this.lastMovePiece != null) {
                    this.lastMovePiece.setColor(currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE);
                }
                this.lastMovePiece = endCell.getPiece();
                this.lastMovePiece.setColor(Color.MOVE);
                System.out.println(this);

            } catch (Exception e) {
                System.out.println(this + " Exception");
            }
        } while (!moved);
    }

    private Cell getCellScan(Color color, Scanner scan, Boolean bool) throws BadInputException, InputNotInTheChess, NotAPieceToMove {
        String CellString = scan.next();
        if (CellString.length() != 2) {
            throw new BadInputException();
        }
        int line = (int) CellString.charAt(1) - 49;
        int column = (int) CellString.toUpperCase().charAt(0) - 65;
        if (line >= 8 || line < 0 || column >= 8 || column < 0) {
            throw new InputNotInTheChess();
        }
        Cell cell = this.board.get(line).get(column);
        if (bool) {
            if (cell.getPiece() == null || cell.getPiece().getColor() != color) {
                throw new NotAPieceToMove();
            }
        }
        return cell;
    }

    public static void playAGame() {
        Game game = new Game();
        System.out.println(game);
        game.currentPlayer = Color.WHITE;
        while (game.gameIsOver() == -1) {
            game.playATour();
            game.currentPlayer = game.currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
        }
        if (game.gameIsOver() == 0) {
            System.out.println(" This game is a draw");
            return;
        }
        Color winnerPlayer = game.currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
        System.out.println(winnerPlayer.getValuePiece() + " Player : " + winnerPlayer + " a gagné la partie" + "\u001B[0m");

    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        ArrayList<ArrayList<Cell>> boardCopy = (ArrayList<ArrayList<Cell>>) this.board.clone();
        Collections.reverse(boardCopy);
        boardString.append("   ");
        for (int i = 0; i < boardCopy.size(); i++) {
            boardString.append((char) (i + 65) + "  ");
        }
        boardString.append("\n");
        for (int i = 0; i < boardCopy.size(); i++) {
            ArrayList<Cell> line = boardCopy.get(i);
            boardString.append((8 - i) + "|");
            for (Cell square : line) {
                boardString.append(square);
            }
            boardString.append("|" + (8 - i));
            boardString.append("\n");
        }
        boardString.append("   ");
        for (int i = 0; i < boardCopy.size(); i++) {
            boardString.append((char) (i + 65) + "  ");
        }
        boardString.append("\n");
        return boardString.toString();
    }
}
