
import java.awt.BorderLayout;
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
    
  //NEW
    JToolBar toolbar;

    boolean mazeIsDone;
    boolean mazeIsDefused;
    boolean isDead;
    
    //new
    boolean canWin;
    int currentState;
    int flagCounter;
    int badFlagCounter;
    
    
  //NEW
    static final int FLAG = 1;
    static final int REVEAL = 2;

    public GUI(int width, int height, int numBombs, Driver inputDriver){
    	//NEW
    	currentState = REVEAL;
    	canWin = true;
    	flagCounter = 0;
    	badFlagCounter = 0;
    	

        driver = inputDriver;
        pane = new GridPanel(width, height, numBombs, this);

        isDead = false;

        this.add(pane);

        menuBar = new JMenuBar();
        newGame = new JMenu("Start a new...");
        
      //NEW START
        flagAction action = new flagAction();
        
        toolbar = new JToolBar();
		JButton button = new JButton("Flag");
		button.addActionListener(action);
		toolbar.add(button);
		
		button = new JButton("Reveal");
		button.addActionListener(action);
		toolbar.add(button);
        
		add(toolbar, BorderLayout.PAGE_START);
		//NEW END
		
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
    //NEW START
    public void unFlag(int state){
    	if(state == -1)
    		flagCounter--;
    	else 
    		badFlagCounter--;
    }
    
    public void flag(int state){
    	if(state == -1)	
    		flagCounter++;
    	else
    		badFlagCounter++;
    }
    
    public void win(){
    	JOptionPane.showMessageDialog(null, "You win! You can now leave the 90s.");
    }
    //NEW END
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
    
  //NEW START
    private class flagAction implements ActionListener{

    	@Override
    	public void actionPerformed(ActionEvent e) {
    		
        	if(((JButton) e.getSource()).getText() == "Flag") {
    			currentState = FLAG;
        	}
        	else if (((JButton) e.getSource()).getText() == "Reveal") {
    			currentState = REVEAL;
        	}
    	}
    	
    }
  //NEW END
}



/*
one frame, add panels to frame.
GUI class should only call functions and create objects

have setters and getters for every private variable always

when maze panel is flipped to, set the focus to that. Move listeners to the GUI class.
There's probably a method to check where the focus is, and thereby make them only listen to the maze panel.

 */
