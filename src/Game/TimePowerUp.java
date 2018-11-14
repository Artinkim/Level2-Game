package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class TimePowerUp extends GameObject {
	Random rand = new Random();
	int r = rand.nextInt(2) + 3;

	TimePowerUp(int a, int b, int c, int d) {
		super(a, b, c, d);
		// TODO Auto-generated constructor stub
		x = rand.nextInt(1000) + 100;
	}

	void update() {
		y += r;
		super.update();
	}

	void draw(Graphics g) {
		g.setColor(new Color(0,0,255));
		g.fillOval(x, y, width, height);
	}

}
