package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	Rectangle collisionBox;

	GameObject(int a, int b, int c, int d) {
		x = a;
		y = b;
		width = c;
		height = d;
		collisionBox = new Rectangle(x, y, width, height);

	}

	void update() {
		collisionBox.setBounds(x, y, width, height);
	}

	void draw(Graphics g) {
		g.fillRect(x, y, width, height);
	}
}
