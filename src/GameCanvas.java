import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameCanvas extends JComponent{

	RopeAssembly ra;
	Image backgroundImage, openingImage;

    private int clicks = 0;
    public GameCanvas() {


		openingImage = new ImageIcon("DesignAssets/Opening.png").getImage();
		backgroundImage = new ImageIcon("DesignAssets/Background.png").getImage();
		setFocusable(true);
		//setPreferredSize(new Dimension(1900,500));
	}




	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; // Cast g into g2d

		//draw "DesignAssets/Background.png"
		g2d.drawImage(backgroundImage, 0, 0, null);


		ra = new RopeAssembly();
		ra.draw(g2d);
	}
}
