package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import javafx.scene.input.KeyCode;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {
	Timer tim;
	ObjectManager om;
	Player p;
	int state = 1;

	GamePanel() {
	
		tim = new Timer(1000 / 60, this);
		tim.start();
		p = new Player(200, 200, 20, 20);
		om = new ObjectManager(p);

	}

	void drawMenu(Graphics g) {
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, 2000, 2000);
		g.setColor(new Color(255, 0, 0));
		g.setFont(new Font("Monospaced", Font.BOLD, 80));
		g.drawString("Stay alive", 400, 250);
		g.setFont(new Font("Monospaced", Font.BOLD, 20));
		g.drawString("In this game you try to stay alive and collect coins to get a higher score.", 100, 350);
		g.drawString("You have powerups to help you along the way ", 100, 400);

	}

	void drawGame(Graphics g) {
		om.draw(g);
		p.draw(g);
		g.drawString(" " + om.score, 20, 20);
		g.drawString(" " + p.lives, 50, 20);
	

	}

	void drawEnd(Graphics g) {
		g.drawString("You got" + om.score, 200, 200);
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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

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
			if (p.air < 2) {
				p.jump();
				p.update();
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

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		char key = e.getKeyChar();
		if (key == 'a') {
			p.left = false;
		}
		if (key == 'd') {
			p.right = false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		om.update();
		om.makeFloor();
		om.makePlats();
		om.makeProjectilesR();
		om.makeCoins();
		p.update();
		repaint();
	}
}
