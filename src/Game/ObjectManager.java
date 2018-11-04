package Game;

import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {
	ArrayList<Platform> plats = new ArrayList<Platform>();


	ObjectManager() {
		plat = new Platform(1100, 0, 50, 5);
	}

	void draw(Graphics g) {
		
		for (Platform n : plats) {
			n.draw(g);
		}
	}

	void update() {
		for (Platform n : plats) {
			n.update();
			
		}
	}
	
}
