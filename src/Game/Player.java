package Game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
	int gravity = 1;
	int speed = 5;
	int velocity = 20;
	int air = 0;
	Rectangle collisionBox;
	boolean left, right, jump;
	Player(int a, int b, int c, int d) {
		super(a, b, c, d);
		
	}


	void update(){
	if (left == true) {
		l();
	}

	if (right == true) {
		r();
	}

	velocity += gravity;
	y += velocity;

	if (y >= 500) {
		y = 501;
		velocity = 0;
		air = 0;
	}
	super.update();
}
void draw(Graphics g)
{
super.draw(g);	
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
