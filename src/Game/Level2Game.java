package Game;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Level2Game {
	public static void main(String[] args) {
		new Level2Game().setup();
	}

	JFrame frame;
	GamePanel gpanel;
	final int width = 1200;
	final int height = 800;

	Level2Game() {
		frame = new JFrame();
		gpanel = new GamePanel();
	}

	void setup() {
		frame.setVisible(true);
		frame.setSize(width, height);
		frame.add(gpanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(gpanel);
		frame.addMouseListener(gpanel);
	}
}
