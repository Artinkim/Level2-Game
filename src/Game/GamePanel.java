package Game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {
	Timer tim;
	ObjectManager om;
	Player p;

	GamePanel() {

		tim = new Timer(1000 / 60, this);
		tim.start();
		p = new Player(200, 200, 20, 20);
		om = new ObjectManager(p);
	}

	@Override
	public void paintComponent(Graphics g) {
		om.draw(g);
		p.draw(g);

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
		char key = e.getKeyChar();
		// TODO Auto-generated method stub
		if (key == 'a') {
			p.left = true;
		}
		if (key == 'd') {
			p.right = true;
		}
		if (key == ' ') {
			if (p.air < 2) {
				p.jump();
				p.update();
			}
			p.air++;
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
		om.makePlats();
		om.makeProjectilesR();
		om.makeCoins();
		p.update();
		repaint();
	}
}
