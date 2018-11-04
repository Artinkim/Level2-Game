package Game;

import java.awt.Graphics;
import java.util.Random;

public class Platform extends GameObject{
Random rand = new Random(300);
	Platform(int a, int b, int c, int d) {
		super(a, b , c, d);
		x = rand.nextInt() +500;
		// TODO Auto-generated constructor stub
	}
	void update()
	{
	x--;
	super.update();
	}
void draw(Graphics g)
{
super.draw(g);	
}
}
