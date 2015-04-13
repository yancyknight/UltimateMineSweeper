import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.*;

import javax.swing.*;

public class menuBars implements ActionListener{

    JMenuBar menuBar;
    JFrame frame;
    JMenu newGame;

    JMenuItem customGame;
    JMenuItem randomGame;

    menuBars(){

        JFrame frame = new JFrame();
        frame.setTitle("TITLE GOES HERE YO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = new JMenuBar();
        newGame = new JMenu("Start a new...");
        customGame = new JMenuItem("Custom Game");
        randomGame = new JMenuItem("Random Game");

        //Add action listeners
        customGame.addActionListener(this);
        randomGame.addActionListener(this);

        newGame.add(customGame);
        newGame.add(randomGame);

        menuBar.add(newGame);
        frame.setJMenuBar(menuBar);


        frame.setSize(500,500);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        menuBars mb = new menuBars();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == customGame){
            System.out.println("custon game");
            customGameOptions cg = new customGameOptions();
        }

        else if (e.getSource() == randomGame){
            System.out.println("Random Game");
        }
    }
}

class customGameOptions extends JFrame implements ActionListener{
    private JLabel num_bombsL, grid_sizeL, fill;
    private JTextField num_bombsTF, grid_sizeTF;
    private JButton startB, cancelB;

    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private startButtonHandler sbHandler;
    private cancelButtonHandler cbHandler;

    menuBars mb;

    public customGameOptions(){

        setTitle("Custom Game Options");
        setSize(WIDTH, HEIGHT);

        num_bombsL = new JLabel("Enter the number\n of bombs desired: ");
        grid_sizeL = new JLabel ("Please enter the grid\n size (must be greater than 'n': ");
        fill = new JLabel(" ");

        num_bombsTF = new JTextField(3);
        grid_sizeTF = new JTextField(3);

        //Create the button and add action listeners
        startB = new JButton("Start!");
        sbHandler = new startButtonHandler();
        startB.addActionListener(sbHandler);

        cancelB = new JButton("Cancel");
        cbHandler = new cancelButtonHandler();
        cancelB.addActionListener(cbHandler);

        Container pane = getContentPane();
        pane.setLayout(new GridLayout(9,30));

        GridBagConstraints gridBagConstraintsJLabel_num_bombs = new GridBagConstraints();
        gridBagConstraintsJLabel_num_bombs.gridx = 0;
        gridBagConstraintsJLabel_num_bombs.gridy = 0;
        gridBagConstraintsJLabel_num_bombs.insets = new Insets(5,5,5,5);
        pane.add(num_bombsL, gridBagConstraintsJLabel_num_bombs);

        GridBagConstraints gridBagConstraintsJText_num_bombs = new GridBagConstraints();
        gridBagConstraintsJText_num_bombs.gridx = 1;
        gridBagConstraintsJText_num_bombs.insets = new Insets(5,5,5,5);
        gridBagConstraintsJText_num_bombs.gridy = 0;
        gridBagConstraintsJText_num_bombs.gridwidth = 1;
        gridBagConstraintsJText_num_bombs.fill = GridBagConstraints.BOTH;
        pane.add(num_bombsTF, gridBagConstraintsJText_num_bombs);

        GridBagConstraints gridBagConstraintsJLabel_grid_size = new GridBagConstraints();
        gridBagConstraintsJLabel_grid_size.gridx = 0;
        gridBagConstraintsJLabel_grid_size.gridy = 0;
        gridBagConstraintsJLabel_grid_size.insets = new Insets(5,5,5,5);
        pane.add(grid_sizeL, gridBagConstraintsJLabel_grid_size);

        GridBagConstraints gridBagConstraintsJText_grid_size = new GridBagConstraints();
        gridBagConstraintsJText_grid_size.gridx = 1;
        gridBagConstraintsJText_grid_size.insets = new Insets(5,5,5,5);
        gridBagConstraintsJText_grid_size.gridy = 0;
        gridBagConstraintsJText_grid_size.gridwidth = 1;
        gridBagConstraintsJText_grid_size.fill = GridBagConstraints.BOTH;
        pane.add(grid_sizeTF, gridBagConstraintsJText_grid_size);

        for (int i = 0; i < 27; i++){
            pane.add(fill);
        }

        pane.add(startB);
        pane.add(cancelB);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private class startButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){

            System.out.println("You just started a custom game!");
        }
    }

    private class cancelButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){

            System.out.println("You just canceled brah!");
            setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}