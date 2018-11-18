package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
	int gravity = 1;
	int mspeed = 5;
	int velocity = 20;
	int air = 0;
	int lives = 3;
	int jumps = 2;
	boolean left, right, jump, touch;

	Player(int a, int b, int c, int d) {
		super(a, b, c, d);
		touch = false;
		speed = 1;
	}

	void floor(Platform P) {
		y = P.y - 20;
		velocity = 0;
		air = 0;
	}

	void floor(floor P) {
		y = P.y - 20;
		velocity = 0;
		air = 0;
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
		if (y > 800) {
			lives = 0;

		}
		if (x < 0) {
			x = 0;
		}
		if (x > 1150) {
			x = 1150;
		}
		super.update();
	}

	void draw(Graphics g) {
		g.setColor(new Color(0, 0, 255));
		super.draw(g);
	}

	void jump() {
		velocity = -15;
	}

	void l() {
		x -= mspeed;
		jump = false;
	}

	void r() {
		x += mspeed;
	}

}
