package Game;

import java.awt.Graphics;
import java.util.Random;

public class Platform extends GameObject {

	Platform(int a, int b, int c, int d) {
		super(a, b, c, d);
		// TODO Auto-generated constructor stub
	}

	void draw(Graphics g) {
		g.fillRect(x, y, width, height);
		g.drawRect(x, y, width, height);
		super.draw(g);

	}
}
