package Game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JTextPane;

public class ObjectManager {
	ArrayList<Platform> plats = new ArrayList<Platform>();
	ArrayList<CannonProjectile> projectiles = new ArrayList<CannonProjectile>();
	ArrayList<Coin> coins = new ArrayList<Coin>();
	ArrayList<floor> floors = new ArrayList<floor>();
	Player p;
	Random rand = new Random();
	long enemyTimer = -5000;
	long enemyTimer2 = 0;
	long enemyTimer3 = 0;
	long enemyTimer4 = 0;
	int enemySpawnTime = 1000;
	int floorSpawnTime = 1000;
	int projectileSpawnTime = 2000;
	int score = 0;

	ObjectManager(Player a) {
		p = a;
	}

	void makePlats() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			plats.add(new Platform(1400, 0, rand.nextInt(40) + 30, 5));
			enemyTimer = System.currentTimeMillis();
		}

	}

	void makeFloor() {
		if (System.currentTimeMillis() - enemyTimer4 >= rand.nextInt(5000) + 3000) {
			floors.add(new floor(1300, 700, 50, 50));
			enemyTimer4 = System.currentTimeMillis();
		}
	}

	void makeProjectilesR() {

		if (System.currentTimeMillis() - enemyTimer2 >= projectileSpawnTime) {
			projectiles.add(new CannonProjectile(1400, 0, 20, 20, p.x, p.y));
			enemyTimer2 = System.currentTimeMillis();
		}

	}

	void makeCoins() {
		if (System.currentTimeMillis() - enemyTimer3 >= rand.nextInt(2000) + 4000) {
			coins.add(new Coin(0, 0, 20, 20));
			enemyTimer3 = System.currentTimeMillis();
		}

	}

	void draw(Graphics g) {

		for (Platform n : plats) {
			n.draw(g);
		}
		for (CannonProjectile n : projectiles) {
			n.draw(g);
		}
		for (Coin n : coins) {
			n.draw(g);
		}
		for (floor n : floors) {
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
		
		for (floor n : floors) {
			n.update();
			if (n.collisionBox.intersects(p.collisionBox)) {
				p.floor(n);
			}
		}

		for (int j = 0; j < projectiles.size(); j++) {
			projectiles.get(j).update();
			if (projectiles.get(j).collisionBox.intersects(p.collisionBox)) {
				p.lives--;
				projectiles.remove(projectiles.get(j));
			}
		}
		for (int j = 0; j < coins.size(); j++)
		{
			coins.get(j).update();
			if (coins.get(j).collisionBox.intersects(p.collisionBox)) {
				score++;
				if (projectileSpawnTime <= 400) {
					projectileSpawnTime = 400;
				} else {
					projectileSpawnTime -= 50;
				}
				coins.remove(coins.get(j));
			}

		}
	}
}
