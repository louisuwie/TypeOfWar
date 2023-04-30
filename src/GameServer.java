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
    GameServer.java instantiates the server and starts the game. This instantiates for the Hosts' side.
*/

import java.io.*;
import java.net.*;

public class GameServer {
    public static void main(String[] args) throws IOException {
        
		// Add server code here, must be able to instantiate a server- for both player and client side
		ServerSocket ss = new ServerSocket(0000); // Not too sure about the port number!
		
		Socket sp1 = ss.accept();
		System.out.println("Player 1 connected. Waiting for Player 2..."); // System output muna! Not yet sure how to display this HAHA
		Socket sp2 = ss.accept();
		System.out.println("Player 2 connected. Launching game.");

		Player p1 = new Player(null);
		Player p2 = new Player(null);

		Thread t1 = new Thread(p1);
		Thread t2 = new Thread(p2);

		GameFrame runGame = new GameFrame();
		
		t1.start();
		t2.start();
		
		/* This makes sure that the threads run and finish before the server progrma executes the succeeding lines. */
		try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Game interrupted.");
        }

        ss.close();

	}
}