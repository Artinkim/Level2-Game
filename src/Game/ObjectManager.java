package Game;

import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {
	ArrayList<Platform> plats = new ArrayList<Platform>();
	Player p;
	long enemyTimer = 0;
	int enemySpawnTime = 1000;

	ObjectManager(Player a) {
	p = a;
	}

	void makePlats() {
		System.out.println("h");
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			plats.add(new Platform(1100, 0, 50, 5));
			System.out.println("hi");
			enemyTimer =  System.currentTimeMillis();
		}
		
	}

	void draw(Graphics g) {

		for (Platform n : plats) {
			n.draw(g);
		}
	}

	void update() {

		for (Platform n : plats) {
			n.update();
			if (n.collisionBox.intersects(p.collisionBox)) {
				p.floor(n);
			}
		}
	}
}
