package Game;

import java.awt.Graphics;
import java.util.Random;

public class Platform extends GameObject {
	Random rand = new Random();

	Platform(int a, int b, int c, int d) {
		super(a, b, c, d);
		// TODO Auto-generated constructor stub
	}

	void draw(Graphics g) {
		super.draw(g);

	}
}
