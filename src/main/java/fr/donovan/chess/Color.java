package fr.donovan.chess;

public enum Color {
    WHITE(new String[]{"\u001B[1;91m", "\u001B[1;47m"}),
    BLACK(new String[]{"\u001B[1;94m", "\u001B[1;40m"}),
    SELECTED(new String[]{"\u001B[1;93m", "\u001B[1;43m"}),
    MOVE(new String[]{"\u001B[1;95m", "\u001B[1;45m"}),
    POSSIBLE(new String[]{"\u001B[1;95m", "\u001B[1;42m"});
    private String[] value;

    Color(String[] value) {
        this.value = value;
    }
    public String getValuePiece() {
        return value[0];
    }
    public String getValueCell() {
        return value[1];
    }
}
