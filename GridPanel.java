package CS2410;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class GridPanel extends JPanel implements ActionListener {
    int boardWidth, boardHeight, numBombs, mazeCount;
    public Board b;
    public ArrayList<ArrayList<JButton>> buttons = new ArrayList<ArrayList<JButton>>();
    Icon bn = new ImageIcon(getClass().getResource("bn.png"));
    Icon bb = new ImageIcon(getClass().getResource("bb.png"));
    Icon bf = new ImageIcon(getClass().getResource("bf.png"));
    Icon b0 = new ImageIcon(getClass().getResource("b0.png"));
    Icon b1 = new ImageIcon(getClass().getResource("b1.png"));
    Icon b2 = new ImageIcon(getClass().getResource("b2.png"));
    Icon b3 = new ImageIcon(getClass().getResource("b3.png"));
    Icon b4 = new ImageIcon(getClass().getResource("b4.png"));
    Icon b5 = new ImageIcon(getClass().getResource("b5.png"));
    Icon b6 = new ImageIcon(getClass().getResource("b6.png"));
    Icon b7 = new ImageIcon(getClass().getResource("b7.png"));
    Icon b8 = new ImageIcon(getClass().getResource("b8.png"));
    Icon be = new ImageIcon(getClass().getResource("explosion1_2.png"));

    String delims = ",";
    GUI frame;
    MouseMaze mouseFrame;

    public int getBoardHeight(){
        return boardHeight;
    }

    public int getBoardWidth(){
        return boardWidth;
    }

    public int getNumBombs(){
        return numBombs;
    }

    public GridPanel(int width, int height, int number, GUI frame) {
        mazeCount = 0;
        this.frame = frame;
        boardWidth = width;
        boardHeight = height;
        numBombs = number;
        b = new Board(width, height, numBombs);
        this.setLayout(new GridLayout(width, height));
        this.setVisible(true);

        ArrayList<JButton> column = new ArrayList<JButton>();

        //adds appropriate images to grid
        for(int j = 0; j < boardWidth; j++ ){
            for (int i = 0; i < boardHeight; i++) {
                JButton button = new JButton();
                button.setIcon(bn);
                int num = b.getTile(j,i);

                button.setActionCommand(Integer.toString(j) + delims + Integer.toString(i) + delims + Integer.toString(num));
                button.setPreferredSize(new Dimension(22,22));
                button.addActionListener(this);

                this.add(button);
                column.add(button);
            }
            buttons.add(column);
            column = new ArrayList<JButton>();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!frame.isDead) {
            String actionCommand = e.getActionCommand();
            StringTokenizer st = new StringTokenizer(actionCommand, delims);
            String xS = st.nextToken();
            String yS = st.nextToken();
            String valueS = st.nextToken();

            int state = Integer.parseInt(valueS);
            int x = Integer.parseInt(xS);
            int y = Integer.parseInt(yS);

            switch (state) {
                case -999:
                    System.out.println("An error has occurred while reading the board.");
                    break;
                case -1:
                    if (mazeCount < 4) {

                        buttons.get(x).get(y).setIcon(bb);
                        JOptionPane.showMessageDialog(null, "A bomb timer has started.\n" +
                                        "Click \"Start\" and drag to \"End\" to\ndefuse the bomb!",
                                "Beep...Beep...Beep...", JOptionPane.INFORMATION_MESSAGE);

                        int xSize = 500 - (90 * mazeCount);
                        int ySize = 500 - (90 * mazeCount);
                        mouseFrame = new MouseMaze(xSize, ySize, frame);
                    }
                    else{
                        buttons.get(x).get(y).setIcon(be);
                        frame.mazeIsDefused = false;
                        frame.checkDone();
                    }
                    break;

                case 0:
                    buttons.get(x).get(y).setIcon(b0);
                    if (x - 1 >= 0 && buttons.get(x - 1).get(y).getIcon() == bn) {
                        buttons.get(x - 1).get(y).doClick();
                    }
                    if (x + 1 < boardWidth && buttons.get(x + 1).get(y).getIcon() == bn) {
                        buttons.get(x + 1).get(y).doClick();
                    }
                    if (y - 1 >= 0 && buttons.get(x).get(y - 1).getIcon() == bn) {
                        buttons.get(x).get(y - 1).doClick();

                    }
                    if (y + 1 < boardHeight && buttons.get(x).get(y + 1).getIcon() == bn) {
                        buttons.get(x).get(y + 1).doClick();
                    }
                    break;
                case 1:
                    buttons.get(x).get(y).setIcon(b1);
                    break;
                case 2:
                    buttons.get(x).get(y).setIcon(b2);
                    break;
                case 3:
                    buttons.get(x).get(y).setIcon(b3);
                    break;
                case 4:
                    buttons.get(x).get(y).setIcon(b4);
                    break;
                case 5:
                    buttons.get(x).get(y).setIcon(b5);
                    break;
                case 6:
                    buttons.get(x).get(y).setIcon(b6);
                    break;
                case 7:
                    buttons.get(x).get(y).setIcon(b7);
                    break;
                case 8:
                    buttons.get(x).get(y).setIcon(b8);
                    break;
            }
        }
    }

    public void reveal(){
        int currentNum;
        for(int i = 0; i < boardWidth; i++){
            for(int j = 0; j < boardHeight; j++)
            {
                currentNum = b.getTile(i, j);
                switch(currentNum) {
                    case -999:
                        System.out.println("An error has occurred while reading the board.");
                        break;
                    case -1:
                        buttons.get(i).get(j).setIcon(bb);
                        break;
                    case 0:
                        buttons.get(i).get(j).setIcon(b0);
                        break;
                    case 1:
                        buttons.get(i).get(j).setIcon(b1);
                        break;
                    case 2:
                        buttons.get(i).get(j).setIcon(b2);
                        break;
                    case 3:
                        buttons.get(i).get(j).setIcon(b3);
                        break;
                    case 4:
                        buttons.get(i).get(j).setIcon(b4);
                        break;
                    case 5:
                        buttons.get(i).get(j).setIcon(b5);
                        break;
                    case 6:
                        buttons.get(i).get(j).setIcon(b6);
                        break;
                    case 7:
                        buttons.get(i).get(j).setIcon(b7);
                        break;
                    case 8:
                        buttons.get(i).get(j).setIcon(b8);
                        break;
                }
            }
        }
    }
}

