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
        gui.pane.b.print_Board();
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gui.pack();
        gui.setVisible(true);
    }
}

//TODO: figure out maze timer?, make an Options menu item instead of current setup, maybe have that have a reveal option

