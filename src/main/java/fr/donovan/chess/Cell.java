package fr.donovan.chess;

public class Cell {
    private Piece piece;
    private Color color;
    private int line;
    private int column;

    public Piece getPiece() {
        return piece;
    }
    public Color getColor() {
        return color;
    }
    public int getLine() {
        return line;
    }
    public int getColumn() {
        return column;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public void setLine(int line) {
        this.line = line;
    }
    public void setColumn(int column) {
        this.column = column;
    }

    public Cell() {
    }

    public Cell(Color color) {
        this.color = color;
    }

    public Cell(Color color, int line, int column) {
        this.color = color;
        this.line = line;
        this.column = column;
    }

//    @Override
//    public String toString() {
//        if (this.piece == null) {
//            if (this.color == Color.BLACK) {
//                return "\u001B[40m □ ";
//            }
//            return "\u001B[47m ■ ";
//        }
//        return " " + this.getPiece().getColor().toString() + this.piece.getImage() + "\u001B[0m ";
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
//        if (this.color == Color.BLACK) {
//            sb.append("\u001B[40m");//this.getColor().getValuePiece().replace("94","40"));
//        }
//        if (this.color == Color.WHITE) {
//            sb.append("\u001B[47m");
//        }
//        if (this.color == Color.SELECTED) {
//            sb.append("\u001B[43m");
//        }
        sb.append(this.color.getValueCell());
        if (this.piece != null) {
            sb.append(" ").append(this.getPiece().getColor().getValuePiece()).append(this.piece.getImage()).append(" ");
        } else {
            sb.append("   ");
        }
        sb.append("\u001B[0m");
        return sb.toString();
    }
}
