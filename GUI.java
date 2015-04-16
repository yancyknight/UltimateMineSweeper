import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener{
    int boardWidth, boardHeight, numBombs;
    static Board b;
    private ArrayList<ArrayList<JButton>> buttons = new ArrayList<ArrayList<JButton>>();
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
    String delims = ",";


    public static void main(String args[]){
        GUI gui = new GUI(20,20,10);
        b.print_Board();
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.pack();
        gui.setVisible(true);
    }

    public GUI(){
        this(10, 10, 10);
    }

    public GUI(int width, int height, int numBombs){
        boardWidth = width;
        boardHeight = height;
        this.numBombs = numBombs;
        b = new Board(width, height, numBombs);

        Container pane = getContentPane();
        pane.setLayout(new GridLayout(width, height));

        ArrayList<JButton> column = new ArrayList<JButton>();

        for(int j = 0; j < boardWidth; j++ ){
            for (int i = 0; i < boardHeight; i++) {
                JButton button = new JButton();
                button.setIcon(bn);
                int num = b.getTile(j,i);

                button.setActionCommand(Integer.toString(j) + delims + Integer.toString(i) + delims + Integer.toString(num));
                button.setPreferredSize(new Dimension(22,22));
                button.addActionListener(this);

                pane.add(button);
                column.add(button);
            }
            buttons.add(column);
            column = new ArrayList<JButton>();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        StringTokenizer st = new StringTokenizer(actionCommand, delims);
        String xS = st.nextToken();
        String yS = st.nextToken();
        String valueS = st.nextToken();

        int state = Integer.parseInt(valueS);
        int x = Integer.parseInt(xS);
        int y = Integer.parseInt(yS);

        switch(state) {
            case -999:
                System.out.println("An error has occurred while reading the board.");
                break;
            case -1:
                buttons.get(x).get(y).setIcon(bb);
                JOptionPane.showMessageDialog(null, "You done got dead", "BOOM", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 0:
                buttons.get(x).get(y).setIcon(b0);
                if(x - 1 >= 0 && buttons.get(x - 1).get(y).getIcon() == bn) {
                    buttons.get(x - 1).get(y).doClick();
                }
                if(x + 1 < boardWidth && buttons.get(x + 1).get(y).getIcon() == bn) {
                    buttons.get(x + 1).get(y).doClick();
                }
                if(y - 1 >= 0 && buttons.get(x).get(y - 1).getIcon() == bn) {
                    buttons.get(x).get(y - 1).doClick();

                }
                if(y + 1 < boardHeight && buttons.get(x).get(y + 1).getIcon() == bn) {
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