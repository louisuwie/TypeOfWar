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

public class Player {
    
    int playerID;
    int low, med, high;
    int speed;

    public Player(int id) {
        this.playerID = id;
        this.speed = 1;
        this.low = 3;
        this.med = 6;
        this.high = 9;
    }

    public void calculateSpeed(int clicks) {
        if (playerID == 1) {
            if (clicks <= 1) {
                speed = low * -1;
            } else if (clicks <= 4) {
                speed = med * -1;
            } else if (clicks <= 8) {
                speed = high * -1;
            }
        } else if (playerID == 2) {
            if (clicks <= 1) {
                speed = low;
            } else if (clicks <= 4) {
                speed = med;
            } else if (clicks <= 8) {
                speed = high;
            }
        }
    }

    public int getSpeed() {
        return speed;
    }
}
