package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Projectile extends GameObject {

	Projectile(int a, int b, int c, int d) {
		super(a, b, c, d);
		// TODO Auto-generated constructor stub

	}

	void draw(Graphics g) {
		g.drawImage(GamePanel.projectile, x, y, width, height, null);
	}

}
