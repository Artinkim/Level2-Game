package Game;

import java.awt.Color;
import java.awt.Graphics;

public class Portal extends GameObject {

	Portal(int a, int b, int c, int d) {
		super(a, b, c, d);
		// TODO Auto-generated constructor stub
		
	}

	void draw(Graphics g) {
		g.drawImage(GamePanel.portal, x, y, width, height, null);
		g.drawImage(GamePanel.portal, x, 25, width, height, null);

	}
}
