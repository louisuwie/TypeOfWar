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

		// ra.isStartScreen(true);
    }


	// Paint Component
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; // Cast into a g2d Object

		g2d.drawImage(bg, 0, 0, 960, 540, null);
		ra.draw(g2d);
	}

	// Repaints every 100ms
	public void startRepaintTimer() {
		Timer timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ra.resetSprite(velocity); // Change the sprite
				ra.tug(velocity); // Update the position
				repaint();
			}
		});
		timer.setRepeats(true);
		timer.start();
	}


	public void startClickTimer() {
		Timer clickTimer = new Timer(1000, new ActionListener() {
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
