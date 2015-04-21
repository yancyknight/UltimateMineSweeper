import javax.swing.*;

public class MouseMaze extends JFrame {

    private MazePanel maze;
    private GUI frame;

    public MouseMaze(int xSize, int ySize, GUI inputFrame){
        this.frame = inputFrame;
        maze = new MazePanel(xSize, ySize, frame , this);

        this.add(maze);
        this.setSize(xSize+ (int)(xSize/(.1*xSize)), ySize + (int)(ySize/(.04*ySize)));
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}