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
    It interacts with different classes to display the game,
    and it also handles the needed clicks to move the rope.

    It mainly calls from RopeAssembly for graphics, and also
    handles scanning for the spacebar clicks to 'pull' the rope.
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameCanvas extends JPanel {
	RopeAssembly ropeAssembly;
	Image currentScreen, bg, p1ws, p2ws;
	int clicks, referenceClicks;
	int velocity;

	TypeRacer typeRacer;
    
    public GameCanvas() {
        setFocusable(true);
		velocity = 0;
		ropeAssembly = new RopeAssembly();
		
		bg = new ImageIcon("DesignAssets/Background.png").getImage();
		p1ws = new ImageIcon("DesignAssets/P1WinnerScreen.png").getImage();
		p2ws = new ImageIcon("DesignAssets/P2WinnerScreen.jpeg").getImage();

		currentScreen = bg;
    }


	// Paint Component, shows the background and all.
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; // Cast into a g2d Object
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		
		if (ropeAssembly.getX() > 960/2 || ropeAssembly.getX() + ropeAssembly.getWidth() < 960/2) {
			if (ropeAssembly.getX() > 960/2) {
				currentScreen = p2ws;
				ropeAssembly.setWinner(2);
				g2d.drawImage(currentScreen, 0, 0, 960, 540, null);
			} else if (ropeAssembly.getX() + ropeAssembly.getWidth() < 960/2) {
				currentScreen = p1ws;
				ropeAssembly.setWinner(1);
				g2d.drawImage(currentScreen, 0, 0, 960, 540, null);
			}
		} else {
			currentScreen = bg;
			g2d.drawImage(currentScreen, 0, 0, 960, 540, null);
			ropeAssembly.draw(g2d);
		}
	}

	// Repaints every 100ms
	public void startRepaintTimer() {
			Timer timer = new Timer(100, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (ropeAssembly.getWinner() == 0) {
						ropeAssembly.resetRopeAssembly(velocity); // Change the sprite
						ropeAssembly.tug(velocity); // Update the position
						repaint();
					} else {
						repaint();
					} 
				}
			});
			timer.setRepeats(true);
			timer.start();
	}
	//Resets the number of clicks every 2 seconds. 
	public void startClickTimer() {
		Timer clickTimer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				referenceClicks = clicks;
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
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "Spacebar Press");
	}

	public void resetClicks(){
		this.clicks = 0;
	}

	public int getClicks() {
		return referenceClicks;
	}

	public void passVelocity(int s) {
		this.velocity = s;
	}

	public RopeAssembly getRopeAssembly() {
		return ropeAssembly;
	}
}
