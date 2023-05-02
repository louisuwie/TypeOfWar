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

/* LAUNCH TERMINAL AT PEM FOLDER
 * TYPE chmod 400 TestKey001.pem
 * ssh -i "TestKey001.pem" ec2-user@ec2-54-208-43-191.compute-1.amazonaws.com
 * install Git
 * git clone https://github.com/louisuwie/TypeOfWar.git
 * install then run Java
 * Compile game files
 * Run1!!!! YAYYY
*/

import java.io.*;
import java.net.*;

public class GameServer {
    public static void main(String[] args) throws IOException {
        
		// Add server code here, must be able to instantiate a server- for both player and client side
		ServerSocket ss = new ServerSocket(2000); // Not too sure about the port number!
		
		// Initializing Input and Output streams for the two Player sockets
		Socket sp1 = ss.accept();
		DataInputStream in1 = new DataInputStream(sp1.getInputStream());
		DataOutputStream out1 = new DataOutputStream(sp1.getOutputStream());
		System.out.println("Player 1 connected. Waiting for Player 2..."); // System output muna! Not yet sure how to display this HAHA
		Socket sp2 = ss.accept();
		DataInputStream in2 = new DataInputStream(sp2.getInputStream());
		DataOutputStream out2 = new DataOutputStream(sp2.getOutputStream());
		System.out.println("Player 2 connected. Launching game.");

		// Casting into threads
		GameStarter p1 = new GameStarter();
		GameStarter p2 = new GameStarter();
		Thread t1 = new Thread(p1);
		Thread t2 = new Thread(p2);

		GameFrame runGame = new GameFrame(); // This should open up the frame after both

		// This should start the client threads as soon as either player hits START.
		if (runGame.getGameVisibility()) {
			t1.start();
			t2.start();
		}

		// End of prgram
        ss.close();

	}
}