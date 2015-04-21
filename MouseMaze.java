package CS2410;

import javax.swing.*;
import java.awt.*;

public class MouseMaze extends JFrame {

    public MazePanel maze;
    private GUI frame;
    int x,y;

    public MouseMaze(int xSize, int ySize, GUI inputFrame){
        this.frame = inputFrame;
        maze = new MazePanel(xSize, ySize, frame , this);
        x = (int)(xSize*1.4);
        y = (int)(ySize*1.4);
        this.add(maze);
        this.setSize(x, y);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
