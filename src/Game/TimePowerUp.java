package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class TimePowerUp extends GameObject {
	Random rand = new Random();

	TimePowerUp(int a, int b, int c, int d) {
		super(a, b, c, d);
		// TODO Auto-generated constructor stub
		x = rand.nextInt(1000) + 100;
	}

	void update() {
		if (speed != 0) {
			y += speed + 3;
		}
		super.update();
	}

	void draw(Graphics g) {
		g.drawImage(GamePanel.timepower,x,y,width,height,null);
	}

}
