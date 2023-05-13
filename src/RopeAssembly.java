import java.awt.*;

import javax.swing.ImageIcon;

public class RopeAssembly {
    

    Image gr, p1w, p2w, ss;
    Image current;

    // Fields are not final. This is where we import the Rope Assembly Graphics
    int x;

    static int y = 90;

    public RopeAssembly() {
        gr = new ImageIcon("DesignAssets/GetReady.PNG").getImage();
        p1w = new ImageIcon("DesignAssets/P1Win.PNG").getImage();
        p2w = new ImageIcon("DesignAssets/P2Win.PNG").getImage();
        ss = new ImageIcon("DesignAssets/SameSpeed.png").getImage();

        // TODO Scale all these images
        
        current = gr;
    }

    public void setX(int a) {
        this.x = a;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(gr, x, 50, null);

    }
}
