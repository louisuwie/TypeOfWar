import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.net.Socket;

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
    Player.java handles the program for the player-end. It is the one that
    communicates with the host server.
*/

public class Player implements Runnable{

	private String name;
	private int speed;
	private int clicks;

	public Player(String n) {
		this.speed = 1;
		this.clicks = 1;
		this.name = n;

		// Setting up the socket for the Player instance
		try {
			Socket s = new Socket("localhost", 2000);
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
		} catch (Exception e) {
			System.out.print("Unable to connect to game.");
		}
	}

	// Methods for mutating
	public void resetSpeed(int clicks) {
		speed = clicks * 3;
	}

	// Some Accessor Methods
	public int getSpeed() {
		return speed;
	}

	public int getClicks() {
		return clicks;
	}

	public String getName() {
		return name;
	}

	@Override
	public void run() {
		// TODO Write code for sending in data to the server program!
		throw new UnsupportedOperationException("Unimplemented method 'run'");
	}
}
