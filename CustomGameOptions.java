import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

class CustomGameOptions extends JDialog{
    private JLabel num_bombsL, grid_sizeL, fill;
    private JTextField num_bombsTF, grid_sizeTF;
    private JButton startB, cancelB;

    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;

    private startButtonHandler sbHandler;
    private cancelButtonHandler cbHandler;
    private Driver driver;

    public CustomGameOptions(Driver inputDriver){

        setTitle("Minesweeper Settings");
        driver = inputDriver;
        setSize(WIDTH, HEIGHT);

        num_bombsL = new JLabel("Enter the number\n of bombs desired: ");
        grid_sizeL = new JLabel ("Please enter the grid\n size (must be between 10 and 35): ");
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
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private class startButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int size = -1;
            int num = -1;

            while(size < 0 || num < 0) {
                //try {
                size = Integer.parseInt(grid_sizeTF.getText());
                num = Integer.parseInt(num_bombsTF.getText());
                if(size < 10)
                {
                    size = 10;
                }
                if(size > 35)
                {
                    size = 35;
                }
                /*} catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(null, "Type a number in both fields.");

                }*/
            }
            driver.makeGame(size, size, num);
            setVisible(false);
            dispose();
        }
    }

    private class cancelButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            setVisible(false);
            dispose();
        }
    }
}
