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
import javax.swing.*;

public class GameStarter implements Runnable {

	Player p = new Player();
	public static void main(String[] args) {
		// Setting up the socket for the Player instance
		// TODO Make an input thingy for the IP Address (in console)
		try {
			try (Socket s = new Socket("54.208.43.191", 2000)) {
				DataInputStream in = new DataInputStream(s.getInputStream());
				DataOutputStream out = new DataOutputStream(s.getOutputStream());
			}
		} catch (Exception e) {
			System.out.print("Unable to connect to game.");
		}
	}

	@Override
	public void run() {
		Timer typeTimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// There should be something that counts the clicks while the timer is running
				p.resetSpeed(0); // Variable for number of clicks will replace the 0
			}
		});
		typeTimer.start();
	}

	//TODO create a client code here, must be able to instantiate a client- for both player and client side

}
