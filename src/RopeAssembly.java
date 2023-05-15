/**
 @author Louis G. Binwag III (200747) & Maria Charmane Rose E. Naciongayo (214152)
 @version April 25, 2023
 **/

/*
	I have not discussed the Java language code in my program
	with anyone other than my instructor or the teaching assistants
	assigned to this course.

	I have not used Java language code obtained from another student,
	or any other unauthorized source, either modified or unmodified.

	If any Java language code or documentation used in my program
	was obtained from another source, such as a textbook or website,
	that has been clearly noted with a proper citation in the comments
	of my program.
*/

/*
    GameCanvas.java handles the graphics-side of the program.
*/

import java.awt.*;

import javax.swing.ImageIcon;

public class RopeAssembly {
    

    Image gr, p1w, p2w, ss, p1Won, p2Won;
    Image current;
    int x;
    int winner;
    static int y = 270;
    int width = 601;
    boolean thereIsWinner = false;

    // TODO Modify the WIDTH and HEIGHT of the rope assembly based on new File
    public RopeAssembly() {
        gr = new ImageIcon("DesignAssets/GetReady.PNG").getImage();
        current = gr;
        p1w = new ImageIcon("DesignAssets/P1Win.PNG").getImage();
        p2w = new ImageIcon("DesignAssets/P2Win.PNG").getImage();
        ss = new ImageIcon("DesignAssets/SameSpeed.png").getImage();
        p1Won = new ImageIcon("P1WinnerScreen.png").getImage();
        p2Won = new ImageIcon("P2WinnerScreen.png").getImage();
        x = 140; // TODO Finalize coordinates for center?
        winner = 0;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(current, x, y, width, 200, null);
    }

    public void tug(int v) {
        x += v;
    }
    public void resetRopeAssembly(int vel) {
        if (vel == 0) {
            current = ss;
        } else if (vel < 0) {
            current = p1w;
        } else {
            current = p2w;
        }
    }

    public void setWinner(int i) {
        winner = i;
    }

    public int getWinner() {
        return winner;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

}
