package Game;

import java.awt.Color;
import java.awt.Graphics;

public class floor extends GameObject {

	floor(int a, int b, int c, int d) {
		super(a, b, c, d);
		// TODO Auto-generated constructor stub
		

	}

	void draw(Graphics g) {
		g.setColor(new Color(50,0,0));
super.draw(g);


	}
}
