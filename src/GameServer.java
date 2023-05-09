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

	public GameServer() {
	
		try {
		ServerSocket ss = new ServerSocket(2000);
		System.out.println("Server started. Waiting for Player 1...");

		// Initializing Input and Output streams for the two Player sockets
		Socket sp1 = ss.accept();
		DataInputStream in1 = new DataInputStream(sp1.getInputStream());
		DataOutputStream out1 = new DataOutputStream(sp1.getOutputStream());
		System.out.println("Player 1 connected. Waiting for Player 2...");


		Socket sp2 = ss.accept();
		DataInputStream in2 = new DataInputStream(sp2.getInputStream());
		DataOutputStream out2 = new DataOutputStream(sp2.getOutputStream());
		System.out.println("Player 2 connected. Launching game.");

		// Casting into threads
		GameStarter p1 = new GameStarter();
		GameStarter p2 = new GameStarter();
		Thread t1 = new Thread(p1);
		Thread t2 = new Thread(p2);
		t1.start();
		t2.start();

		int p1s = in1.readInt();
		System.out.println(p1s);
		int p2s = in2.readInt();
		System.out.println(p2s);
		int ropeSpeed = p2s - p1s;

		out1.writeInt(ropeSpeed);
		out2.writeInt(ropeSpeed);
		} catch (Exception e) {
			// TODO: handle exception
		}


	}

	public static void main(String[] args) {
		GameServer gameServer = new GameServer();
	}

}