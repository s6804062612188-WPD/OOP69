package week09;

import java.awt.*;
import javax.swing.*;

class GamePanel extends JPanel {
	private Image background, bird,pig;

	private int birdX = 250, birdY;
	private int pigX;
	private int groundY = 300;

	public GamePanel() {
		// Load images independently from the GUI.
		background = ImageLoader.load(ImageLoader.BACKGROUND_PATH);
		bird = ImageLoader.load(ImageLoader.BIRD_PATH);
		pig = ImageLoader.load(ImageLoader.PIG_PATH);

		setPreferredSize(new Dimension(600, 350));

		randomizePig();
		birdY = groundY - 100;
	}

	public void setBirdHeight(double initialY) {
		birdY = groundY + (int) initialY;
		repaint();
	}

	public void randomizePig() {
		// Keep the pig inside the visible panel.
		pigX = 300 + (int) (Math.random() * 240);
		repaint();
	}

	public int getPigX() { return pigX; }
	public int getPigY() { return groundY - 30; }
	public int getLaunchX() { return birdX; }
	public int getGroundY() { return groundY; }

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int w = getWidth();
		int h = getHeight();
		groundY = h - 35;

		// Background image, scaled to fill the panel.
		if (background != null) {
			g.drawImage(background, 0, 0, w, h, this);
		} else {
			g.setColor(new Color(170, 205, 220));
			g.fillRect(0, 0, w, h);
		}

		// Ground
		g.setColor(new Color(80, 150, 70));
		g.fillRect(0, groundY, w, h - groundY);

		// Bird
		if (bird != null) {
			g.drawImage(bird, birdX, birdY, 45, 45, this);
		} else {
			g.setColor(Color.RED);
			g.fillOval(birdX, birdY, 35, 35);
		}

		// Pig
		if (pig != null) {
			g.drawImage(pig, pigX, groundY - 30, 35, 35, this);
		} else {
			g.setColor(Color.GREEN);
			g.fillOval(pigX, groundY - 30, 35, 35);
		}
	}
}
