package uaic.info.game;

public class Game {

    private Board board;
    private Player player1;
    private Player player2;

    private boolean firstTurn;

    public Game(Player player1) {
        this.board = new Board(19);
        this.player1 = player1;
        firstTurn = true;
    }

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board(19);
        firstTurn = true;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public boolean makeMove(int row, int col)
    {
        if(board.validMove(row,col) == false)
            return false;
        if(firstTurn == true)
            board.makeMove(1,row,col);
        else board.makeMove(2,row,col);
        return true;
    }
}
