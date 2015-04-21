import javax.swing.*;

public class Driver {
    GUI gui;

    public static void main(String args[]){
        Driver driver = new Driver();
        JDialog choices = new CustomGameOptions(driver);
    }

    public void makeGame(int x, int y, int num){
        if(gui!=null){
            gui.setVisible(false);
            gui.dispose();
        }
        gui = new GUI(x,y,num, this);
        gui.pane.b.printBoard();
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.pack();
        gui.setVisible(true);
    }
}
