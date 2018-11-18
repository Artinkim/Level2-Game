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
	public static BufferedImage player;

	GamePanel() {

		tim = new Timer(1000 / 60, this);
		tim.start();
		p = new Player(200, 200, 20, 20);
		om = new ObjectManager(p);

		try {
			player = ImageIO.read(this.getClass().getResourceAsStream("space.jpeg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void drawMenu(Graphics g) {
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, 2000, 2000);
		g.setColor(new Color(0, 250, 0));
		g.setFont(new Font("Monospaced", Font.BOLD, 80));
		g.drawString("Stay alive", 400, 250);
		g.setFont(new Font("Monospaced", Font.BOLD, 20));
		g.drawString("In this game you try to stay alive and collect coins to get a higher score.", 50, 350);
		g.drawString("You start off with 3 lives if you get hit by the ROCKETS you loose one life", 50, 400);
		g.drawString("Falling into the void instantly kills you. You have powerups to help you along the way" , 50, 450);
		g.drawString("TIME stops time for 5 seconds, and JUMP adds another jump to your origional double jump(Jump Power Ups Stack)", 50, 500);
		g.drawString("You also have a teleporter beams at the bottom that teleport you to the top of the screen", 50, 550);
	}

	void drawGame(Graphics g) {
		om.draw(g);
		p.draw(g);
		g.drawString(" " + om.score, 20, 20);
		g.drawString(" " + p.lives, 50, 20);

	}

	void drawEnd(Graphics g) {
		g.setColor(new Color(0, 0, 0));
		g.setFont(new Font("Comicsans", Font.BOLD, 80));
		g.drawString("You got " + om.score, 400, 400);
	}

	@Override
	public void paintComponent(Graphics g) {

		if (state == 1) {
			drawMenu(g);
			p = new Player(200, 200, 20, 20);
			om = new ObjectManager(p);

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
		if (e.getKeyCode() == KeyEvent.VK_A) {
			p.left = true;
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
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (state > 2) {
				state = 1;
			} else {
				state++;
			}
		}
		System.out.println(e.getKeyCode()+"pressed");
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
		System.out.println(e.getKeyCode());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (p.lives <= 0) {
			state = 3;
		}
		if(state == 2) {
		om.update();
		om.makeFloor();
		om.makePowerUps();
		om.makePlats();
		om.makeProjectilesR();
		om.makeCoins();
		p.update();
		}
		repaint();
	}
}
