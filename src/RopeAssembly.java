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
    

    Image gr, p1w, p2w, ss;
    Image current;
    int x;
    static int winner;
    static int y = 270;
    GameFrame gameFrame;
    TypeRacer typeRacer;
    // Fields are not final. This is where we import the Rope Assembly Graphics

    public RopeAssembly() {
        
        gr = new ImageIcon("DesignAssets/GetReady.PNG").getImage();
        current = gr;

        p1w = new ImageIcon("DesignAssets/P1Win.PNG").getImage();
        p2w = new ImageIcon("DesignAssets/P2Win.PNG").getImage();
        ss = new ImageIcon("DesignAssets/SameSpeed.png").getImage();

        x = 150;
    }

    public void setGameFrame(GameFrame gameframe){
        this.gameFrame = gameframe;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(current, x, y, 600, 200, null);
    }

    public void tug(int v) {

        if(winner != 0){ // If there is a winner, stop the game
            System.out.println("Game Over");
        }

        if(x > 400){ // If the rope is pulled too far, stop the game
            winner = 2;
            System.out.println("Player 2 Wins!");
        } else if (x < 0) {
            winner = 1;
            System.out.println("Player 1 Wins!");
        }
        else{
            x += v;
        }

        if(x == 50){
            gameTwist();
        } else if(x == 100){
            gameTwist();
        } else if (x == 200){
            gameTwist();
        } else if (x == 250){
            gameTwist();
        }

        System.out.println(x);
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

    public void gameTwist(){
        typeRacer.initialize();

        if(typeRacer.isTyped()){
            if(gameFrame.getPlayerID() == 1){
                x += 30;
            } else if (gameFrame.getPlayerID() == 2){
                x += 30;
            }
        }
    }

    public static int getWinner() {
        return winner;
    }
}
