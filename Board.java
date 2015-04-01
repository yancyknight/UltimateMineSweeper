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

    //Better. Finds bombs and increments all squares around it. Still ugly.
    public void assignNums() {
        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight; y++) {
                if (board.get(x).get(y) == -1) {
                    if (x - 1 >= 0 && y - 1 >= 0 && board.get(x - 1).get(y - 1) != -1) {
                        board.get(x - 1).set(y - 1, board.get(x - 1).get(y - 1) + 1);
                    }// increment -1, -1

                    if (x - 1 >= 0 && board.get(x - 1).get(y) != -1) {
                        board.get(x - 1).set(y, board.get(x - 1).get(y) + 1);
                    }// increment -1, 0

                    if (x - 1 >= 0 && y + 1 < boardHeight && board.get(x - 1).get(y + 1) != -1) {
                        board.get(x - 1).set(y + 1, board.get(x - 1).get(y + 1) + 1);
                    }// increment -1, +1

                    if (y - 1 >= 0 && board.get(x).get(y - 1) != -1) {
                        board.get(x).set(y - 1, board.get(x).get(y - 1) + 1);
                    }// increment 0, -1

                    if (y + 1 < boardHeight && board.get(x).get(y + 1) != -1) {
                        board.get(x).set(y + 1, board.get(x).get(y + 1) + 1);
                    }// increment 0, +1

                    if (x + 1 < boardWidth && y - 1 >= 0 && board.get(x + 1).get(y - 1) != -1) {
                        board.get(x + 1).set(y - 1, board.get(x + 1).get(y - 1) + 1);
                    }//increment +1, -1

                    if (x + 1 < boardWidth && board.get(x + 1).get(y) != -1) {
                        board.get(x + 1).set(y, board.get(x + 1).get(y) + 1);
                    }//increment +1, 0

                    if (x + 1 < boardWidth && y + 1 < boardHeight && board.get(x + 1).get(y + 1) != -1) {
                        board.get(x + 1).set(y + 1, board.get(x + 1).get(y + 1) + 1);
                    }//increment +1, +1
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
