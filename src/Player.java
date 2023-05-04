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

public class Player {

	private int speed;
	private int low, med, high;

	public Player() {
		this.speed = 1;
		/* Placeholder */
		this.low = 100;
		this.med = 1000;
		this.high = 2000;
	}

	// Methods for mutating

	// Some Accessor Methods
	public int getSpeed(int c) {

		// Placeholder values
		if (c <= 1) {
			speed = low;
		} else if (c <= 10) {
			speed = med;
		} else if (c <100) {
			speed = high;
		}

		return speed;
	}
}
