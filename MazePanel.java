package CS2410;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

public class MazePanel extends JPanel implements MouseMotionListener, MouseListener {

    private Point prevPoint;
    private int X;
    private int Y;
    private int xSize;
    private int ySize;
    private GUI frame;
    private MouseMaze maze;
    Image img;

    public MazePanel(int x, int y, GUI inputFrame, MouseMaze inputMaze){
        this.frame = inputFrame;
        this.maze = inputMaze;
        prevPoint = new Point(0,0);
        xSize = x;
        ySize = y;
        X = xSize/8;
        Y = ySize/8;
        this.setSize(xSize, ySize);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.setBackground(new Color(22,33,44));
    }

    public void dead(){
        frame.mazeIsDone = true;
        frame.mazeIsDefused = false;
        frame.checkDone();
        maze.setVisible(false);
        maze.dispose();
    }

    public void notDead(){
        frame.mazeIsDone = true;
        frame.mazeIsDefused = true;
        frame.checkDone();
        maze.setVisible(false);
        maze.dispose();
    }

    public void setImageFile(){
        try {
            img = ImageIO.read(new File("maze_1.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(new Color(137, 137, 137));
        g.fillRect(0, 7 * Y, X, 8 * Y);
        g.setColor(new Color(29, 139, 55));
        g.fillRect(7*X,0,8*X,Y);

        g.setColor(Color.red);
        g.drawLine(7 * X, 0, 7 * X, 5 * Y);
        g.drawLine(7*X, 6*Y, 8*X, 6*Y);
        g.drawLine(7*X, 7*Y, 7*X, 8*Y);
        g.drawLine(6*X, 0, 6*X, 3*Y);
        g.drawLine(6*X, 4*Y, 6*X, 7*Y);
        g.drawLine(6*X, 5*Y, 7*X, 5*Y);
        g.drawLine(5*X, 4*Y, 6*X, 4*Y);
        g.drawLine(5*X, Y, 5*X, 4*Y);
        g.drawLine(5*X, 7*Y, 5*X, 8*Y);
        g.drawLine(4*X, 2*Y, 4*X, 5*Y);
        g.drawLine(4*X, 6*Y, 6*X, 6*Y);
        g.drawLine(3*X, 5*Y, 5*X, 5*Y);
        g.drawLine(3*X, 5*Y, 3*X, 7*Y);
        g.drawLine(2*X, 2*Y, 4*X, 2*Y);
        g.drawLine(2*X, 4*Y, 4*X, 4*Y);
        g.drawLine(2*X, 4*Y, 2*X, 6*Y);
        g.drawLine(2*X, 7*Y, 5*X, 7*Y);
        g.drawLine(X, Y, X, 3*Y);
        g.drawLine(X, Y, 5*X, Y);
        g.drawLine(X, 4*Y, X, 6*Y);
        g.drawLine(X, 3*Y, 3*X, 3*Y);
        g.drawLine(0, 6*Y, X, 6*Y);

        g.setColor(Color.black);
        g.drawString("START", (int)(.1*X), (int)(7.6*Y));
        g.drawString("END", (int)(7.3*X), (int)(.6*Y));

        setImageFile();
        g.drawImage(img, 0, 0, maze.x, maze.y, null);

    }

    //unused
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //if you click outside the start box
        if(e.getX() > X || e.getY() < 7*Y){
            dead();
        }
        prevPoint = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //if you release outside the end box
        if(e.getX() < 7*X || e.getY() > Y){
            dead();
        }
        //if you release inside the end box
        else if (e.getX() >= 7*X && e.getY() <= Y){
            notDead();
        }
    }

    //unused
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
       dead();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //boundaries
        if(e.getX()>=8*X || e.getY()>=8*Y){
            dead();
        }
        //0.6
        if(prevPoint.x >= 0 && prevPoint.x <= X && prevPoint.y >= 6*Y && prevPoint.y <= 7*Y && e.getY() <= 6*Y)
        {
            dead();
        }
        else if(prevPoint.x >= 0 && prevPoint.x <= X && prevPoint.y >= 5*Y && prevPoint.y <= 6*Y && e.getY() <= 7*Y && e.getY() >= 6*Y)
        {
            dead();
        }
        //0.5-6
        else if(prevPoint.x >= 0 && prevPoint.x <= X && prevPoint.y >= 4*Y && prevPoint.y <= 6*Y &&
                e.getX() <= 2*X && e.getX() >= X)
        {
            dead();
        }
        //0.1-3
        else if(prevPoint.x >= 0 && prevPoint.x <= X && prevPoint.y >= Y && prevPoint.y <= 3*Y &&
                e.getX() <= 2*X && e.getX() >= X)
        {
            dead();
        }
        //1-4.0
        else if(prevPoint.x >= X && prevPoint.x <= 5*X && prevPoint.y >= 0 && prevPoint.y <= Y &&
                e.getY() <= 2*Y && e.getY() >= Y)
        {
            dead();
        }
        //1.4-5
        else if((prevPoint.x >= X && prevPoint.x <= 2*X && prevPoint.y >= 4*Y && prevPoint.y <= 6*Y) &&
                ((e.getX() >= 0 && e.getX() <= X) || ( e.getX() >= 2*X && e.getX() <= 3*X)))
        {
            dead();
        }
        //1.1-2
        else if((prevPoint.x >= X && prevPoint.x <= 2*X && prevPoint.y >= Y && prevPoint.y <= 3*Y) &&
                ((e.getX() >= 0 && e.getX() <= X) || ( e.getY() >= 0 && e.getY() <= Y) || (e.getY() >= 3*Y)))
        {
            dead();
        }
        //1.3
        else if(prevPoint.x >= X && prevPoint.x <= 2*X && prevPoint.y >= 3*Y && prevPoint.y <= 4*Y &&
                e.getY() <= 3*Y)
        {
            dead();
        }
        //2.6
        else if((prevPoint.x >= 2*X && prevPoint.x <= 3*X && prevPoint.y >= 6*Y && prevPoint.y <= 7*Y) &&
                (e.getX() >= 3*X || e.getY() >= 7*Y ))
        {
            dead();
        }
        //2.3
        else if((prevPoint.x >= 2*X && prevPoint.x <= 3*X && prevPoint.y >= 3*Y && prevPoint.y <= 4*Y) &&
                (e.getY() <= 3*Y || e.getY() >= 4*Y ))
        {
            dead();
        }
        //2.2
        else if((prevPoint.x >= 2*X && prevPoint.x <= 3*X && prevPoint.y >= 2*Y && prevPoint.y <= 3*Y) &&
                (e.getY() <= 2*Y || e.getY() >= 3*Y ))
        {
            dead();
        }
        //2-3.1
        else if((prevPoint.x >= 2*X && prevPoint.x <= 4*X && prevPoint.y >= Y && prevPoint.y <= 2*Y) &&
                (e.getY() <= Y || e.getY() >= 2*Y ))
        {
            dead();
        }
        //2-3.7
        else if((prevPoint.x >= 2*X && prevPoint.x <= 4*X && prevPoint.y >= 7*Y && prevPoint.y <= 8*Y) &&
                (e.getY() <= 7*Y))
        {
            dead();
        }
        //2.5
        else if((prevPoint.x >= 2*X && prevPoint.x <= 3*X && prevPoint.y >= 5*Y && prevPoint.y <= 6*Y) &&
                (e.getX() <= 2*X || e.getX() >= 3*X))
        {
            dead();
        }
        //2.4
        else if((prevPoint.x >= 2*X && prevPoint.x <= 3*X && prevPoint.y >= 4*Y && prevPoint.y <= 5*Y) &&
                (e.getY() <= 4*Y || e.getX() <= 2*X))
        {
            dead();
        }
        //3.4
        else if((prevPoint.x >= 3*X && prevPoint.x <= 4*X && prevPoint.y >= 4*Y && prevPoint.y <= 5*Y) &&
                (e.getY() <= 4*Y || e.getY() >= 5*Y || e.getX() >= 4*X))
        {
            dead();
        }
        //3.2-3
        else if((prevPoint.x >= 3*X && prevPoint.x <= 4*X && prevPoint.y >= 2*Y && prevPoint.y <= 4*Y) &&
                (e.getY() <= 2*Y || e.getY() >= 4*Y || e.getX() >= 4*X))
        {
            dead();
        }
        //3.5-6
        else if((prevPoint.x >= 3*X && prevPoint.x <= 4*X && prevPoint.y >= 5*Y && prevPoint.y <= 7*Y) &&
                (e.getY() <= 5*Y || e.getY() >= 7*Y || e.getX() <= 3*X))
        {
            dead();
        }
        //5.0
        else if((prevPoint.x >= 5*X && prevPoint.x <= 6*X && prevPoint.y >= 0 && prevPoint.y <= Y) &&
                (e.getX() >= 6*X))
        {
            dead();
        }
        //4.1
        else if((prevPoint.x >= 4*X && prevPoint.x <= 5*X && prevPoint.y >= Y && prevPoint.y <= 2*Y) &&
                (e.getY() <= Y ||  e.getX() >= 5*X))
        {
            dead();
        }
        //4.2-3
        else if((prevPoint.x >= 4*X && prevPoint.x <= 5*X && prevPoint.y >= 2*Y && prevPoint.y <= 4*Y) &&
                (e.getX() <= 4*X ||  e.getX() >= 5*X))
        {
            dead();
        }
        //4.4
        else if((prevPoint.x >= 4*X && prevPoint.x <= 5*X && prevPoint.y >= 4*Y && prevPoint.y <= 5*Y) &&
                (e.getY() >= 5*Y || e.getX() <= 4*X))
        {
            dead();
        }
        //4.5
        else if((prevPoint.x >= 4*X && prevPoint.x <= 5*X && prevPoint.y >= 5*Y && prevPoint.y <= 6*Y) &&
                (e.getY() <= 5*Y || e.getY() >= 6*Y))
        {
            dead();
        }
        //4.6
        else if((prevPoint.x >= 4*X && prevPoint.x <= 5*X && prevPoint.y >= 6*Y && prevPoint.y <= 7*Y) &&
                (e.getY() <= 6*Y || e.getY() >= 7*Y))
        {
            dead();
        }
        //4.7
        else if((prevPoint.x >= 4*X && prevPoint.x <= 5*X && prevPoint.y >= 7*Y && prevPoint.y <= 8*Y) &&
                (e.getY() <= 7*Y || e.getX() >= 5*X))
        {
            dead();
        }
        //5-6.7
        else if((prevPoint.x >=5*X && prevPoint.x <= 7*X && prevPoint.y >= 7*Y && prevPoint.y <= 8*Y) &&
                (e.getX() <= 5*X || e.getX() >= 7*X))
        {
            dead();
        }
        //5.6
        else if((prevPoint.x >= 5*X && prevPoint.x <= 6*X && prevPoint.y >= 6*Y && prevPoint.y <= 7*Y) &&
                (e.getY() <= 6*Y || e.getX() >= 6*X))
        {
            dead();
        }
        //5.4-5
        else if((prevPoint.x >= 5*X && prevPoint.x <= 6*X && prevPoint.y >= 4*Y && prevPoint.y <= 6*Y) &&
                (e.getY() <= 4*Y || e.getY() >= 6*Y || e.getX() >= 6*X))
        {
            dead();
        }
        //5.3
        else if((prevPoint.x >= 5*X && prevPoint.x <= 6*X && prevPoint.y >= 3*Y && prevPoint.y <= 4*Y) &&
                (e.getY() >= 4*Y || e.getX() <= 5*X))
        {
            dead();
        }
        //5.1-2
        else if((prevPoint.x >= 5*X && prevPoint.x <= 6*X && prevPoint.y >= Y && prevPoint.y <= 3*Y) &&
                (e.getX() >= 6*Y || e.getX() <= 5*X))
        {
            dead();
        }
        //6.1-2
        else if((prevPoint.x >= 6*X && prevPoint.x <= 7*X && prevPoint.y >= 0 && prevPoint.y <= 3*Y) &&
                (e.getX() >= 7*Y || e.getX() <= 6*X))
        {
            dead();
        }
        //6.3
        else if((prevPoint.x >= 6*X && prevPoint.x <= 7*X && prevPoint.y >= 3*Y && prevPoint.y <= 4*Y) &&
                (e.getX() >= 7*X ))
        {
            dead();
        }
        //6.4
        else if((prevPoint.x >= 6*X && prevPoint.x <= 7*X && prevPoint.y >= 4*Y && prevPoint.y <= 5*Y) &&
                (e.getX() >= 7*Y || e.getX() <= 6*X || e.getY() >= 5*Y))
        {
            dead();
        }
        //6.5
        else if((prevPoint.x >= 6*X && prevPoint.x <= 7*X && prevPoint.y >= 5*Y && prevPoint.y <= 6*Y) &&
                (e.getY() <= 5*Y || e.getX() <= 6*X))
        {
            dead();
        }
        //6.6
        else if((prevPoint.x >= 6*X && prevPoint.x <= 7*X && prevPoint.y >= 6*Y && prevPoint.y <= 7*Y) &&
                (e.getX() <= 6*X))
        {
            dead();
        }
        //7.6
        else if((prevPoint.x >= 7*X && prevPoint.x <= 8*X && prevPoint.y >= 6*Y && prevPoint.y <= 7*Y) &&
                (e.getY() <= 6*Y ))
        {
            dead();
        }
        //7.7
        else if((prevPoint.x >= 7*X && prevPoint.x <= 8*X && prevPoint.y >= 7*Y && prevPoint.y <= 8*Y) &&
                (e.getX() <= 7*X ))
        {
            dead();
        }
        //7.5
        else if((prevPoint.x >= 7*X && prevPoint.x <= 8*X && prevPoint.y >= 5*Y && prevPoint.y <= 6*Y) &&
                (e.getY() >= 6*Y ))
        {
            dead();
        }
        //7.6
        else if((prevPoint.x >= 7*X && prevPoint.x <= 8*X && prevPoint.y >= 6*Y && prevPoint.y <= 7*Y) &&
                (e.getY() <= 6*Y ))
        {
            dead();
        }
        //7.0-4
        else if((prevPoint.x >= 7*X && prevPoint.x <= 8*X && prevPoint.y >= 0 && prevPoint.y <= 5*Y) &&
                (e.getX() <= 7*X ))
        {
            dead();
        }
        //at end
        prevPoint = e.getPoint();
    }

    //unused
    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
