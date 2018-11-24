package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import javafx.scene.input.KeyCode;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer tim;
	ObjectManager om;
	Player p;
	int state = 1;
	int score;
	public static BufferedImage player;
	public static BufferedImage timepower;
	public static BufferedImage jumppower;
	public static BufferedImage coin;
	public static BufferedImage projectile;
	public static BufferedImage portal;
	public static BufferedImage health;

	GamePanel() {

		tim = new Timer(1000 / 60, this);
		tim.start();
		p = new Player(200, 200, 20, 50);
		om = new ObjectManager(p);

		try {
			player = ImageIO.read(this.getClass().getResourceAsStream("sm.png"));
			timepower = ImageIO.read(this.getClass().getResourceAsStream("clock.png"));
			jumppower = ImageIO.read(this.getClass().getResourceAsStream("up_arrow.png"));
			coin = ImageIO.read(this.getClass().getResourceAsStream("coin.png"));
			projectile = ImageIO.read(this.getClass().getResourceAsStream("missile.png"));
			portal = ImageIO.read(this.getClass().getResourceAsStream("portal.gif"));
			health = ImageIO.read(this.getClass().getResourceAsStream("health.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void drawMenu(Graphics g) {
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, 2000, 2000);
		g.setColor(om.c);
		g.setFont(new Font("Monospaced", Font.BOLD, 80));
		g.drawString("Stay alive", 400, 250);
		g.setFont(new Font("Monospaced", Font.BOLD, 20));
		g.drawString("The longer you stay alive the more points you get and collecting coins gives +1000 points", 25,
				350);
		g.drawString("You start off with 3 lives if you get hit by the ROCKETS you loose one life.", 25, 400);
		g.drawString("Falling into the void instantly kills you. You have powerups to help you along the way.", 25,
				450);
		g.drawString("TIME stops time for 5 seconds, and JUMP adds another jump to your origional double jump(Stacks)",
				25, 500);
		g.drawString("You also have a teleporter beams at the bottom that teleport you to the top of the screen", 25,
				600);
		g.drawString("Score, health, and jumps are displayed in the top left corner of the screen.", 20, 550);

		g.drawString("Each world is progresively harder than the last.", 20, 650);
	}

	void drawGame(Graphics g) {
		om.draw(g);
		p.draw(g);
		g.setFont(new Font("Arial", Font.PLAIN, 15));
		g.drawString("Score: " + om.score, 20, 20);
		g.drawString("Lives: " + p.lives, 120, 20);
		g.drawString("Jumps: " + (p.jumps + 1), 180, 20);
		g.drawString("World: " + om.speed, 280, 20);
	}

	void drawEnd(Graphics g) {
		g.setColor(om.c);
		g.fillRect(10, 0, 1200, 800);
		g.setColor(new Color(0, 0, 0));
		g.setFont(new Font("Comicsans", Font.CENTER_BASELINE, 40));
		g.drawString("You got to world " + (om.World - 1) + " and got " + om.score + " points.", 200, 300);
		if (om.score > score) {
			score = om.score;
		}
		g.drawString("HighScore: " + score, 450, 400);

	}

	@Override
	public void paintComponent(Graphics g) {

		if (state == 1) {
			drawMenu(g);

		} else if (state == 2) {
			drawGame(g);

		} else if (state == 3) {
			drawEnd(g);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		if (om.drawWorld == false) {
			if (e.getKeyCode() == KeyEvent.VK_A) {
				p.left = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_P) {
				om.speed++;
				p.lives += 2222;
				p.jumps += 200;

			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				p.right = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				if (p.air < p.jumps) {
					p.jumping = true;
				}
				p.air++;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (state > 2) {
				p = new Player(200, 200, 20, 50);
				om = new ObjectManager(p);
				state = 1;
			} else {
				state++;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_A) {
			p.left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			p.right = false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (p.lives <= 0) {
			state = 3;
		}
		if (state == 2) {
			om.Spawners();
			om.update();
			p.update();
		}
		repaint();
	}
}
