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
import javax.swing.*;

public class EndScreen extends JPanel{
    Image winnerIcon, backGround;
    boolean chickenDinner = false;

    public EndScreen() {

        //TODO FIX TO PROPER IMAGE (WINNER ICON) AND SHOW IT PROPERLY AFTER GAME ENDS.

        backGround = new ImageIcon("Background").getImage();

        if(RopeAssembly.getWinner() == 1){
            this.winnerIcon = new ImageIcon("DesignAssets/P1WinnerScreen").getImage();
            boolean chickenDinner = true;
        } else if (RopeAssembly.getWinner() == 2){
            this.winnerIcon = new ImageIcon("DesignAssets/P2WinnerScreen").getImage();
            boolean chickenDinner = true;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; // Cast into a g2d Object
        g2d.drawImage(backGround, 0, 0,960, 540, null);
        g2d.drawImage(winnerIcon, 0, 0,960, 540, null);
    }
}
