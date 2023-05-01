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

public class GameStarter {
	public static void main(String[] args) {
		// Setting up the socket for the Player instance
		try {
			try (Socket s = new Socket("localhost", 2000)) {
				DataInputStream in = new DataInputStream(s.getInputStream());
				DataOutputStream out = new DataOutputStream(s.getOutputStream());
			}
		} catch (Exception e) {
			System.out.print("Unable to connect to game.");
		}
	}

	//TODO create a client code here, must be able to instantiate a client- for both player and client side

}
