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
    GameStarter creates the GameFrame and connects to the server.
    It also creates a timer that instantiates a new TypeRacer frame
    every 10 seconds (The twist of the game).

*/

// TODO Remove all debugging statements na! :D 
import java.util.Scanner;
import javax.swing.*;
import java.awt.event.*;

public class GameStarter {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        System.out.print("IP Address: ");
        String ip = in.next();
        GameFrame gameFrame = new GameFrame();
        gameFrame.connectToServer(ip);
        gameFrame.setUpGameFrame();
        gameFrame.setUpFrameTimers();
        in.close();
                
        // Instantiates a new frame every 10 seconds, feel free to edit
        Timer timer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                GameCanvas gameCanvas = gameFrame.getCanvas();
                RopeAssembly ropeAssembly = gameCanvas.getRopeAssembly();
                int winner = ropeAssembly.getWinner();
                
                if (winner == 0) {
                    TypeRacer typeRacer = new TypeRacer();
                    typeRacer.initialize();
                    typeRacer.startTypeRacerTimer();
                }
            }
        });
        timer.setRepeats(true);
        timer.start();
    }
}
