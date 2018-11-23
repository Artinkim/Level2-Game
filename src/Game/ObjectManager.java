package Game;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JTextPane;

public class ObjectManager {
	ArrayList<Platform> plats = new ArrayList<Platform>();
	ArrayList<CannonProjectile> projectiles = new ArrayList<CannonProjectile>();
	ArrayList<Coin> coins = new ArrayList<Coin>();
	ArrayList<Portal> Portals = new ArrayList<Portal>();
	ArrayList<TimePowerUp> TPowers = new ArrayList<TimePowerUp>();
	ArrayList<JumpPowerUp> JPowers = new ArrayList<JumpPowerUp>();
	ArrayList<HealthPowerUp> HPowers = new ArrayList<HealthPowerUp>();
	Player p;
	long Tim = System.currentTimeMillis();
	long Draw = System.currentTimeMillis();
	Random rand = new Random();
	long enemyTimer = 0;
	long enemyTimer2 = System.currentTimeMillis();
	long enemyTimer3 = System.currentTimeMillis();
	long enemyTimer4 = System.currentTimeMillis();
	long enemyTimer5 = System.currentTimeMillis();
	long enemyTimer6 = System.currentTimeMillis();
	long enemyTimer7 = System.currentTimeMillis();
	long scoreTime = System.currentTimeMillis();
	int enemySpawnTime = 1000;
	int PortalspawnTime = 1500;
	int projectileSpawnTime = 2000;
	int score = 0;
	double World = 1;
	double speed = 1;
	double MS = 1;
	boolean time = true;
	boolean drawWorld = false;

	Platform f = new Platform(0, 600, 1400, 15);

	ObjectManager(Player a) {
		p = a;
	}

	void Spawners() {
		if (time == true) {
			if (drawWorld == false) {
				makeFloor();
				makePowerUps();
				makePlats();
				makeProjectilesR();
				score();
			}
		}
	}

	void score() {
		if (System.currentTimeMillis() - 1000 >= scoreTime) {
			score += 100;
			speed += .2;
			scoreTime = System.currentTimeMillis();
		}
	}

	void makePlats() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			plats.add(new Platform(1400, rand.nextInt(400) + 100, rand.nextInt(40) + 30, 10));
			enemyTimer = System.currentTimeMillis();
		}
	}

	void makePowerUps() {
		if (System.currentTimeMillis() - enemyTimer5 >= rand.nextInt(15000) + 15000) {
			TPowers.add(new TimePowerUp(0, 0, 20, 20));
			enemyTimer5 = System.currentTimeMillis();
		}

		if (System.currentTimeMillis() - enemyTimer6 >= rand.nextInt(5000) + 25000) {
			JPowers.add(new JumpPowerUp(0, 0, 20, 20));
			enemyTimer6 = System.currentTimeMillis();
		}

		if (System.currentTimeMillis() - enemyTimer7 >= rand.nextInt(15000) + 20000) {
			HPowers.add(new HealthPowerUp(0, 0, 20, 20));
			enemyTimer7 = System.currentTimeMillis();
		}
	}

	void makeFloor() {
		if (System.currentTimeMillis() - enemyTimer4 >= rand.nextInt(2000) + 8000) {
			Portals.add(new Portal(1300, 675, 50, 50));
			enemyTimer4 = System.currentTimeMillis();
		}
	}

	void makeProjectilesR() {

		if (System.currentTimeMillis() - enemyTimer2 >= projectileSpawnTime) {
			projectiles.add(new CannonProjectile(1400, rand.nextInt(400) + 100, 60, 15));
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
		f.draw(g);
		for (Platform n : plats) {
			n.draw(g);
		}
		for (CannonProjectile n : projectiles) {
			n.draw(g);
		}
		for (Coin n : coins) {
			n.draw(g);
		}
		for (Portal n : Portals) {
			n.draw(g);
		}
		for (TimePowerUp n : TPowers) {
			n.draw(g);
		}
		for (JumpPowerUp n : JPowers) {
			n.draw(g);
		}
		for (HealthPowerUp n : HPowers) {
			n.draw(g);
		}
		if (drawWorld == true) {
			g.setFont(new Font("Monospaced", Font.BOLD, 80));
			g.drawString("World: " + (World - 1), 600, 400);
		}

		if (speed > World) {
			MS = speed;
			Draw = System.currentTimeMillis();
			speed = 0;
			p.speed = 0;
			p.mspeed = 0;
			p.velocity = 0;
			p.gravity = 0;
			drawWorld = true;
			World++;
		}
	}

	void update() {
		p.speed = speed;
		f.speed = speed;
		f.update();

		for (int j = 0; j < JPowers.size(); j++) {
			JPowers.get(j).speed = speed;
			JPowers.get(j).update();
			if (JPowers.get(j).collisionBox.intersects(p.collisionBox)) {
				p.jumps++;
				JPowers.remove(JPowers.get(j));
			}
		}

		if (f.collisionBox.intersects(p.collisionBox)) {
			p.floor(f);
		}

		for (int j = 0; j < TPowers.size(); j++) {
			TPowers.get(j).speed = speed;
			TPowers.get(j).update();
			if (TPowers.get(j).collisionBox.intersects(p.collisionBox)) {
				MS = speed;
				Tim = System.currentTimeMillis();
				speed = 0;
				time = false;
				TPowers.remove(TPowers.get(j));
			}
		}
		if (time == false) {
			if (System.currentTimeMillis() - 5000 >= Tim) { // For time power up length
				time = true;
				speed = MS;

			}
		}

		if (drawWorld == true) {
			if (System.currentTimeMillis() - 2000 >= Draw) { // For drawing the new world length
				speed = MS;
				p.gravity = 1;
				p.mspeed = 5;
				drawWorld = false;

			}
		}

		for (Platform n : plats) {
			n.speed = speed;
			n.update();
			if (n.collisionBox.intersects(p.collisionBox)) {
				p.floor(n);
			}
		}

		for (int j = 0; j < Portals.size(); j++) {
			Portals.get(j).speed = speed;
			Portals.get(j).update();
			if (Portals.get(j).collisionBox.intersects(p.collisionBox)) {
				p.y = 0;
				p.velocity = 0;
				Portals.remove(Portals.get(j));
			}
		}

		for (int j = 0; j < projectiles.size(); j++) {
			projectiles.get(j).speed = speed;
			projectiles.get(j).update();
			if (projectiles.get(j).collisionBox.intersects(p.collisionBox)) {
				p.lives--;
				projectiles.remove(projectiles.get(j));

			}
		}
		for (int j = 0; j < coins.size(); j++) {
			coins.get(j).speed = speed;
			coins.get(j).update();
			if (coins.get(j).collisionBox.intersects(p.collisionBox)) {
				score += 1000;
				if (projectileSpawnTime <= 400) {
					projectileSpawnTime = 400;
				} else {
					projectileSpawnTime -= 100;
				}
				coins.remove(coins.get(j));
			}

		}
		for (int j = 0; j < HPowers.size(); j++) {
			HPowers.get(j).speed = speed;
			HPowers.get(j).update();
			if (HPowers.get(j).collisionBox.intersects(p.collisionBox)) {
				p.lives++;
				HPowers.remove(HPowers.get(j));

			}
		}
	}

}
