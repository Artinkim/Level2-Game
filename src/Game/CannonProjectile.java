package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class CannonProjectile extends GameObject {
	Random rand = new Random();
	int r;

	CannonProjectile(int a, int b, int c, int d, int px, int py) {
		super(a, b, c, d);
		y = rand.nextInt(600) + 100;
		// TODO Auto-generated constructor stub

	}

	void draw(Graphics g) {
		g.setColor(new Color(255, 0, 0));
		super.draw(g);
	}

	void update() {
		x -= 3;
		super.update();
	}

}
