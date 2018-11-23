package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class CannonProjectile extends GameObject {
	Random rand = new Random();
	int r;

	CannonProjectile(int a, int b, int c, int d) {
		super(a, b, c, d);
		// TODO Auto-generated constructor stub

	}

	void draw(Graphics g) {
		g.drawImage(GamePanel.projectile, x, y, width, height, null);
	}

	void update() {
		x-=speed;
		super.update();
	}

}
