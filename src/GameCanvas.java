import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameCanvas extends JComponent {

	RopeAssembly ra;
	Image bg, op;
    
    public GameCanvas() {
		op = new ImageIcon("DesignAssets/Opening.png").getImage();
		bg = new ImageIcon("DesignAssets/Background.png").getImage();
        setFocusable(true);
        setPreferredSize(new Dimension(500,500));
    }

    protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; // Cast g into g2d
		ra = new RopeAssembly();
        ra.draw(g2d);
    }

    public void addKeyBindings() {
		ActionMap am = getActionMap();
		InputMap im = getInputMap();

		// Define the Abstract Actions
		AbstractAction sc = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Put clicker code here!
                repaint();
			}
		};

		am.put("Spacebar Press", sc);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "Spacebar Press");
	}
}
