package Game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JTextPane;

public class ObjectManager {
	ArrayList<Platform> plats = new ArrayList<Platform>();
	ArrayList<CannonProjectile> projectiles = new ArrayList<CannonProjectile>();
	ArrayList<Coin> coins = new ArrayList<Coin>();
	Player p;
	Random rand = new Random();
	long enemyTimer = 0;
	long enemyTimer2 = 0;
	long enemyTimer3 = 0;
	int enemySpawnTime = 1000;
	int projectileSpawnTime = 5000;
	int score = 0;

	ObjectManager(Player a) {
		p = a;
	}

	void makePlats() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			plats.add(new Platform(1100, 0, 50, 5));
			enemyTimer = System.currentTimeMillis();
		}

	}

	void makeProjectilesR() {
		if (System.currentTimeMillis() - enemyTimer2 >= projectileSpawnTime-score*200) {
			projectiles.add(new CannonProjectile(1300, 0, 20, 20, p.x, p.y));
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
	}

	void update() {

		for (Platform n : plats) {
			n.update();
			if (n.collisionBox.intersects(p.collisionBox)) {
				p.floor(n);
			}
		}
		for (CannonProjectile n : projectiles) {
			n.update();
			if (n.collisionBox.intersects(p.collisionBox)) {
				p.y = 0;
				p.x = 0;
			}
		}
		for (int j = 0; j < coins.size(); j++)

		{
			coins.get(j).update();
			if (coins.get(j).collisionBox.intersects(p.collisionBox)) {
				score++;
				System.out.println(score);
			
				coins.remove(coins.get(j));
			}

		}
	}
}
