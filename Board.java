import java.util.ArrayList;
import java.util.Random;

public class Board {
    static int boardWidth = 8;
    static int boardHeight = 4;
    static int numBombs = 8;
    static ArrayList<ArrayList<Integer>> board = new ArrayList<ArrayList<Integer>>();

    public Board() {
        initializeBoard();
        assignBombs(numBombs);
        assignNums();
    }

    public static void main(String[] args) {
        Board b = new Board();

        printBoard();
    }

    //initialize board to 0
    private void initializeBoard() {
        ArrayList<Integer> column = new ArrayList<Integer>();
        for (int i = 0; i < boardHeight; i++) {
            column.add(0);
        }

        for (int j = 0; j < boardWidth; j++) {
            column = new ArrayList<Integer>(column);
            board.add(column);
        }
    }

    //randomly place numBombs bombs
    private void assignBombs(int numBombs) {
        Random rand = new Random();
        int x = rand.nextInt(boardWidth);
        int y = rand.nextInt(boardHeight);

        for (int i = 0; i < numBombs; i++) {
            while (board.get(x).get(y) == -1) {
                x = rand.nextInt(boardWidth);
                y = rand.nextInt(boardHeight);
            }
            board.get(x).set(y, -1);
        }
    }

    public void assignNums(){
        for(int x = 0; x < boardWidth; x++){
            for(int y = 0; y < boardHeight; y++){
                if(board.get(x).get(y) == -1){
                    for(int xOff = -1; xOff < 2; xOff++) {
                        for (int yOff = -1; yOff < 2; yOff++){
                            if (x + xOff >= 0 && x + xOff < boardWidth && y + yOff >= 0 && y + yOff < boardHeight && board.get(x + xOff).get(y + yOff) != -1) {                               
                                board.get(x + xOff).set(y + yOff, board.get(x + xOff).get(y + yOff) + 1);
                            }
                        }
                    }
                }
            }
        }
    }

    //print in matrix form
    public static void printBoard() {
        for (ArrayList<Integer> column : board) {
            for (int e : column) {
                if(e == -1)
                    System.out.print(e + "  ");
                else
                    System.out.print(e + "   ");
            }
            System.out.println();
        }
    }

}
