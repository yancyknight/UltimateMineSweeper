import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class GUI extends JFrame implements ActionListener{
    int width, height, numBombs;
    static Board b;
    private ArrayList<ArrayList<JButton>> buttons = new ArrayList<ArrayList<JButton>>();
    Icon bn = new ImageIcon(getClass().getResource("bn.png"));

    public static void main(String args[]){
        GUI gui = new GUI();
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.pack();
        gui.setVisible(true);
    }

    public GUI(){
        this(10, 10, 10);
    }

    public GUI(int width, int height, int numBombs){
        this.width = width;
        this.height = height;
        this.numBombs = numBombs;
        b = new Board(width, height, numBombs);

        Container pane = getContentPane();
        pane.setLayout(new GridLayout(width, height));

        ArrayList<JButton> column = new ArrayList<JButton>();

        for(int j = 0; j < width; j++ ){
            for (int i = 0; i < height; i++) {
                JButton button = new JButton();
                button.setIcon(bn);
                int num = b.getTile(j,i);

                button.setActionCommand(Integer.toString(j) + Integer.toString(i) + Integer.toString(num) );
                button.addActionListener(this);

                pane.add(button);
                column.add(button);
            }
            buttons.add(column);
            column = new ArrayList<JButton>();
        }
    }

    private void revealTile(int x, int y){
        buttons.get(x).get(y).doClick();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        String xS = actionCommand.substring(0, 1);
        String yS = actionCommand.substring(1, 2);
        String valueS = actionCommand.substring(2);

        int state = Integer.parseInt(valueS);
        int x = Integer.parseInt(xS);
        int y = Integer.parseInt(yS);

        switch(state) {
            case -1:
                Icon bb = new ImageIcon(getClass().getResource("bb.png"));
                buttons.get(x).get(y).setIcon(bb);
                break;
            case 0:
                Icon b0 = new ImageIcon(getClass().getResource("b0.png"));
                buttons.get(x).get(y).setIcon(b0);
                if(x - 1 >= 0 && buttons.get(x - 1).get(y).getIcon() == bn) {
                    revealTile(x - 1, y);
                }
                if(x + 1 < width && buttons.get(x + 1).get(y).getIcon() == bn) {
                    revealTile(x + 1, y);
                }
                if(y - 1 >= 0 && buttons.get(x).get(y - 1).getIcon() == bn) {
                    revealTile(x, y - 1);
                }
                if(y + 1 < height && buttons.get(x).get(y + 1).getIcon() == bn) {
                    revealTile(x, y + 1);
                }
                break;
            case 1:
                Icon b1 = new ImageIcon(getClass().getResource("b1.png"));
                buttons.get(x).get(y).setIcon(b1);
                break;
            case 2:
                Icon b2 = new ImageIcon(getClass().getResource("b2.png"));
                buttons.get(x).get(y).setIcon(b2);
                break;
            case 3:
                Icon b3 = new ImageIcon(getClass().getResource("b3.png"));
                buttons.get(x).get(y).setIcon(b3);
                break;
            case 4:
                Icon b4 = new ImageIcon(getClass().getResource("b4.png"));
                buttons.get(x).get(y).setIcon(b4);
                break;
            case 5:
                Icon b5 = new ImageIcon(getClass().getResource("b5.png"));
                buttons.get(x).get(y).setIcon(b5);
                break;
            case 6:
                Icon b6 = new ImageIcon(getClass().getResource("b6.png"));
                buttons.get(x).get(y).setIcon(b6);
                break;
            case 7:
                Icon b7 = new ImageIcon(getClass().getResource("b7.png"));
                buttons.get(x).get(y).setIcon(b7);
                break;
            case 8:
                Icon b8 = new ImageIcon(getClass().getResource("b8.png"));
                buttons.get(x).get(y).setIcon(b8);
                break;
        }
    }
}