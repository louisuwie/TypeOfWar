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

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameCanvas extends JComponent {

	ImageIcon background;
	JLabel label;
	int clicks, clickReference, direction;

	// Graphics-side code here, maybe import all the graphics-related files such as img, sound, gif, etc.
	public GameCanvas() {

		clicks = 1; // Starting speed
		clickReference = 0;

		background = new ImageIcon("ToW_Background.jpeg"); // Placeholder! Will fix the file path rin soon...
		label = new JLabel(background);

		setPreferredSize(new Dimension(1000, 600));
		setFocusable(true);
		setLayout(new BorderLayout()); // Para easier for us to layout stuff in the future!
		add(label, BorderLayout.CENTER);

	}

	@Override
	protected void paintComponent(Graphics g2d) {

		//import image
		ImageIcon image = new ImageIcon("ToW_Assembly.png");

		//draw image with x value taken from a different variable, in this case, clicks
		g2d.drawImage(image.getImage(), Player.getSpeed(), 200, null);
	}

	public void addKeyBindings() {
		ActionMap am = getActionMap();
		InputMap im = getInputMap();

		// Define the Abstract Actions
		AbstractAction spaceClicks = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				clicks++;
				updateGameCanvas();
				System.out.println("Speed is: " + Player.getSpeed());
				System.out.println("Clicks from player: " + Player.getPlayerNumber());
			}
		};

		// Creating the Action
		am.put("Spacebar Press", spaceClicks);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "Spacebar Press");
	}

	public void resetClicks() {
		clickReference = clicks; // So that the program can get the clicks in the last second
		clicks = 0; // Resets clicks for the next second
	}

	public void tuggingDirection() {
		// TODO code for tugging the assembly


	}

	public void changeDirection(int d) {
		direction = d;
	}


	public void updateGameCanvas() {
		Timer ugcTimer = new Timer(100, ae -> repaint());
		ugcTimer.setRepeats(true);
		ugcTimer.start();
	}

	public int getReferenceClicks() {
		return clickReference;
	}
}