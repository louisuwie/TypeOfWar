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
GameStarter.java instantiates the game for the Player Side.
*/

import java.net.*;
import java.io.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;

// TODO Figure out how to send each player's inputs to server, then send back inputs to canvases for validation and repainting.

public class GameStarter implements Runnable {

	static Socket s;
	static DataInputStream in;
	static DataOutputStream out;

	GameFrame gf = new GameFrame();
	Player p = new Player();

	public static void main(String[] args) {
		// Setting up the socket for the Player instance
		Scanner ai = new Scanner(System.in);
		System.out.print("Enter the IP address: ");
		String ip = ai.next();
		ai.close();

		try {
			try (Socket s = new Socket(ip, 2000)) {
				in = new DataInputStream(s.getInputStream());
				out = new DataOutputStream(s.getOutputStream());
			}
		} catch (Exception e) {
			System.out.print("Unable to connect to game.");
		}
	}

	// So far, this run() method adds the KeyBindings and updates the Canvas painting every 1 second.
	// It sends the clicks in the last second to the server.
	// It will then read the ropeSpeed (rightPlayerSpeed - leftPlayerSpeed) that came from the server
	// It will update the movement of the ToW assembly in GameCanvas (repaint is called every 1/10 of a second.)
	@Override
	public void run() {

		GameCanvas gc = gf.getCanvas();
		gc.addKeyBindings();
		gc.updateGameCanvas();
		
		Timer timer = new Timer(1000, new ActionListener() {

			int updatedClicks;
			int playerSpeed;
			int ropeSpeed;

			public void actionPerformed(ActionEvent ae) {
				gc.resetClicks();
				updatedClicks = gc.getReferenceClicks();
				playerSpeed = p.getSpeed(updatedClicks);

				// For some reason. this is not working.. im not rally sure what to do...
				try {
					out.writeInt(playerSpeed); // Placeholder
					ropeSpeed = in.readInt();
				} catch (Exception e) {
					// TODO: handle exception
				}
				gc.changeDirection(ropeSpeed); // Also this..
				System.out.println("Clicks: " + gc.getReferenceClicks());
			}
		});
		timer.setRepeats(true);
		timer.start();
	}
}
