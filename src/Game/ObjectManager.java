package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JTextPane;

public class ObjectManager {
	ArrayList<Platform> plats = new ArrayList<Platform>();
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Coin> coins = new ArrayList<Coin>();
	ArrayList<Portal> Portals = new ArrayList<Portal>();
	ArrayList<TimePowerUp> TPowers = new ArrayList<TimePowerUp>();
	ArrayList<JumpPowerUp> JPowers = new ArrayList<JumpPowerUp>();
	ArrayList<HealthPowerUp> HPowers = new ArrayList<HealthPowerUp>();
	ArrayList<Explosion> Explodes = new ArrayList<Explosion>();
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
	int enemySpawnTime = 900;
	int projectileSpawnTime = 2000;
	int score = 0;
	double World = 1;
	double speed = 1;
	double MS = 1;
	boolean time = true;
	boolean drawWorld = false;
	Color c;
	Color c2;

	Platform f = new Platform(0, 600, 2500, 15);

	ObjectManager(Player a, Color b,Color second) {
		p = a;
		c = b;
		c2 = second;
	}

	void Spawners() {
		if (time == true) {
			if (drawWorld == false) {
				makePortal();
				makePowerUps();
				makePlats();
				makeProjectiles();
				score();
				makeCoins();

			}
		}
	}

	void score() {
		if (System.currentTimeMillis() - 1000 >= scoreTime) {
			score += 100;
			speed += .04;
			scoreTime = System.currentTimeMillis();
		}
	}

	void makePlats() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			plats.add(new Platform(1400, rand.nextInt(400) + 100, rand.nextInt(30) + 50, 10));
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

	void makePortal() {
		if (System.currentTimeMillis() - enemyTimer4 >= rand.nextInt(2000) + 8000) {
			Portals.add(new Portal(1300, 675, 50, 50));
			enemyTimer4 = System.currentTimeMillis();
		}
	}

	void makeProjectiles() {

		if (System.currentTimeMillis() - enemyTimer2 >= projectileSpawnTime) {
			projectiles.add(new Projectile(1400, rand.nextInt(400) + 100, 60, 15));
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

		if (speed > World) {
			MS = speed;
			Draw = System.currentTimeMillis();
			speed = 0;
			p.speed = 0;
			p.mspeed = 0;
			p.velocity = 0;
			p.gravity = 0;
			drawWorld = true;
			if (World < 10) {
				enemySpawnTime -= 100;
				projectileSpawnTime -= 180;
			} else {
				enemySpawnTime = 100;
				projectileSpawnTime = 400;
			}
			if (World > 1) {
				c = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
				c2 = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
			}
			World++;
		}
		g.setColor(c);
		g.fillRect(0, 0, 1300, 800);
		g.setColor(c2);
		f.draw(g);
		for (Platform n : plats) {
			n.draw(g);
		}
		for (Projectile n : projectiles) {
			n.draw(g);
		}
		for (Coin n : coins) {
			n.draw(g);
		}
		for (Explosion n : Explodes) {
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

	}

	void update() {
		p.speed = speed;
		f.speed = speed * 2;
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
			if (System.currentTimeMillis() - 3000 >= Tim) { // For time power up length
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

		for (int j = 0; j < projectiles.size(); j++) {
			projectiles.get(j).speed = speed * 2;
			projectiles.get(j).update();
			if (projectiles.get(j).collisionBox.intersects(p.collisionBox)) {
				p.lives--;
				Explodes.add(new Explosion(projectiles.get(j).x - 60, projectiles.get(j).y - 15));
				projectiles.remove(projectiles.get(j));

			}
		}

		for (int j = 0; j < plats.size(); j++) {
			plats.get(j).speed = speed;
			plats.get(j).update();
			if (plats.get(j).collisionBox.intersects(p.collisionBox)) {
				p.floor(plats.get(j));
			}
		}

		for (int j = 0; j < plats.size(); j++) { // Checking collision of missiles and platforms
			for (int q = 0; q < projectiles.size(); q++) {
				if (plats.get(j).collisionBox.intersects(projectiles.get(q).collisionBox)) {
					Explodes.add(new Explosion(projectiles.get(q).x - 60, projectiles.get(q).y - 15));
					plats.remove(plats.get(j));
					projectiles.remove(projectiles.get(q));

				}
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

		for (int j = 0; j < coins.size(); j++) {
			coins.get(j).speed = speed;
			coins.get(j).update();
			if (coins.get(j).collisionBox.intersects(p.collisionBox)) {
				score += 1000;
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

		for (int j = 0; j < Explodes.size(); j++) {
			Explodes.get(j).update();
			if (Explodes.get(j).dead == true) {
				Explodes.remove(Explodes.get(j));
			}
		}
	}

}
