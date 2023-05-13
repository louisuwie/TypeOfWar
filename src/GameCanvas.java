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

	RopeAssembly ra;
	Image bg, op;
	int clicks;
	int velocity;
    
    public GameCanvas() {
        setFocusable(true);
        setPreferredSize(new Dimension(1900,1080));
		velocity = 0;
		ra = new RopeAssembly();
		bg = new ImageIcon("DesignAssets/Background.png").getImage();
    }


	// Paint Component
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; // Cast into a g2d Object

		g2d.drawImage(bg, 0, 0, 960, 540, null);
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		ra.draw(g2d);
	}

	// Repaints every 100ms
	public void startRepaintTimer() {
		Timer timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ra.resetRopeAssembly(velocity); // Change the sprite
				ra.tug(velocity); // Update the position
				repaint();
			}
		});
		timer.setRepeats(true);
		timer.start();

	}


	public void startClickTimer() {
		Timer clickTimer = new Timer(2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clicks = 0;
			}
		});
		clickTimer.setRepeats(true);
		clickTimer.start();
	}

    public void addKeyBindings() {
		ActionMap am = getActionMap();
		InputMap im = getInputMap();

		// Define the Abstract Actions
		AbstractAction sc = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				clicks++;
			}
		};

		am.put("Spacebar Press", sc);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "Spacebar Press"); // Decided to go for true dito kasi I noticed na you can kinda cheat by just holdiing the Spacebar down
	}

	public int getClicks() {
		return clicks;
	}

	public void passVelocity(int s) {
		this.velocity = s;
	}
}
