package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	int gravity = 1;
	int speed = 5;
	int velocity = 20;
	Rectangle collisionBox;
	boolean left, right, jump, air;

	GameObject(int a, int b, int c, int d) {
		x = a;
		y = b;
		width = c;
		height = d;
		collisionBox = new Rectangle(x, y, width, height);

	}

	void update() {
		if (left == true) {
			l();
		}

		if (right == true) {
			r();
		}

		velocity += gravity;
		y += velocity;

		if (y >= 501) {
			y = 501;
			velocity = 0;
		}

		collisionBox.setBounds(x, y, width, height);
	}

	void draw(Graphics g) {
		g.setColor(new Color(255, 0, 0));
		g.fillRect(x, y, width, height);
	}

	void jump() {
		velocity = -10;
	}

	void l() {
		x -= speed;
		jump = false;
	}

	void r() {
		x += speed;
	}

}
