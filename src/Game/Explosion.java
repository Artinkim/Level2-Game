package Game;

import java.awt.Graphics;

public class Explosion {
	int x;
	int y;
	boolean dead;
	long l = System.currentTimeMillis();
	final int width = 100;
	final int height = 100;

	Explosion(int a, int b) {
		x = a;
		y = b;
		dead = false;
	}
	void update() {
		if (System.currentTimeMillis() - 500 >=l ) {
		dead = true;
		
		}
	}
	void draw(Graphics g) {
		g.drawImage(GamePanel.explosion, x, y, width, height, null);
	}
}
