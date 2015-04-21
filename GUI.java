import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener{
    GridPanel pane;
    Driver driver;

    JMenuBar menuBar;
    JMenu newGame;

    JMenuItem customGame;
    JMenuItem randomGame;

    boolean mazeIsDone;
    boolean mazeIsDefused;
    boolean isDead;

    public GUI(int width, int height, int numBombs, Driver inputDriver){


        driver = inputDriver;
        pane = new GridPanel(width, height, numBombs, this);

        isDead = false;

        this.add(pane);

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
        this.setResizable(false);
        this.setTitle("Minesweeper");
        this.setJMenuBar(menuBar);
    }

    public void checkDone(){
        if(mazeIsDefused)
        {
            JOptionPane.showMessageDialog(null, "You defused it! Phew.");
            pane.mazeCount++;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "BOOM! You are very dead.");
            //TODO: have some sort of reveal function here
            isDead = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == customGame){
            System.out.println("custom game");
            JDialog cg = new CustomGameOptions(driver);
        }

        else if (e.getSource() == randomGame){
            Random numGenerator = new Random();
            int randSize = 0;
            while(randSize < 10){
                randSize = numGenerator.nextInt(35);
            }
            int randBombs = 100;
            while(randBombs >= randSize*randSize || randBombs < 1){
                randBombs = numGenerator.nextInt(20);
            }
            driver.makeGame(randSize, randSize, randBombs);
        }
    }
}

/*
one frame, add panels to frame.
GUI class should only call functions and create objects

have setters and getters for every private variable always

when maze panel is flipped to, set the focus to that. Move listeners to the GUI class.
There's probably a method to check where the focus is, and thereby make them only listen to the maze panel.

 */