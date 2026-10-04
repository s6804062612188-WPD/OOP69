package week10;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class P05_BalloonButHelicopter {
	public static void main(String[] args) {
		GameFrame frame = new GameFrame();

		frame.setTitle("Helicopter Shooter 6804062612188");
		frame.setSize(600, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class GameFrame extends JFrame {
	private final GamePanel gamePanel = new GamePanel();

	public GameFrame() {
		add(gamePanel);
	}
}

abstract class Thing {
	protected float x, y;
	protected GamePanel parent;
	
	public Thing(GamePanel gp) {
		this.parent = gp;
	}
}
interface Exists {
	void update();
	void draw(Graphics g);
}
abstract class CircleThing extends Thing implements Exists {
	protected float radius;
	
	public static final void collisionCheck(CircleThing a, CircleThing b) {
		float poses = (a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y);
		float radii = a.radius + b.radius;
		if ( poses <= radii*radii ) {
			a.onHit();
			b.onHit();
		}
	}

	public CircleThing(GamePanel gp) {
		super(gp);
	}
	public abstract void onHit();
	@Override
	public abstract void update();
	@Override
	public void draw(Graphics g) {
		int diameter = (int) (radius * 2);

		g.drawOval(
			(int) (x - radius), (int) (y - radius),
			diameter, diameter
		);
	}
}

class Helicopter extends CircleThing {
	private int intensity;
	private float phase;
	
	public Helicopter(GamePanel gp) {
		super(gp);
		x = 300;
		y = 100;
		radius = 25;
		intensity = 0;
		phase = 0;
	}
	
	@Override
	public void onHit() {
		++parent.score;
		++intensity;
	}
	@Override
	public void update() {
		phase += 0.0002f * intensity;
		x = 300 + 250 * (float) Math.sin(phase);
	}	
	
	@Override
	public void draw(Graphics g) {
		// Main body
		g.drawOval(
			(int) x-16, (int) y-8,
			32, 16
		);
		// Bottom landing structure
		g.drawLine(
			(int) x - 8, (int) y + 5,
			(int) x - 8, (int) y + 20
		);

		g.drawLine(
			(int) x + 8, (int) y + 5,
			(int) x + 8, (int) y + 20
		);

		// Connecting line
		g.drawLine(
			(int) x - 12, (int) y + 20,
			(int) x + 12, (int) y + 20
		);

		// Tail
		g.drawLine(
			(int) x + 16, (int) y,
			(int) x + 30, (int) y
		);

		// Tail rotor
		g.drawOval(
			(int) x + 27, (int) y - 4,
			8, 8
		);

		// Main rotor stem
		g.drawLine(
			(int) x, (int) y - 10,
			(int) x, (int) y - 25
		);

		// Main rotor
		g.drawLine(
			(int) x - 12, (int) y - 25,
			(int) x + 12, (int) y - 25
		);

		g.drawLine(
			(int) x, (int) y - 32,
			(int) x, (int) y - 18
		);
	}
}
class Bullet extends CircleThing {
	public float angle;
	public float speed = 10;
	public boolean removable;
	private int time = 0;
	
	public Bullet(GamePanel gp) {
		super(gp);
		parent.danmaku.add(this);
		this.radius = 5;
		this.removable = false;
	}
	
	@Override
	public void onHit() {
		removable = true;
	}
	@Override
	public void update() {
		forward(1);
		++time;
		if (time > 75) removable = true;
	}
	
	public void forward(int time) {
		for (int i=0; i<time; i=i+1) {
			float temp = (float) Math.toRadians(angle);
			this.x += speed * Math.cos( temp );
			this.y -= speed * Math.sin( temp );
		}
	}
}

class Player extends Thing implements Exists{
	protected float angle = 90;
	protected float length = 25;

	private boolean left, right, up;
	
	public Player(GamePanel gp) {
		super(gp);
		x = 300;
		y = 450;
	}

	public void keyPressed(int key) {
		switch(key) {
			case KeyEvent.VK_LEFT -> left = true;
			case KeyEvent.VK_RIGHT -> right = true;
			case KeyEvent.VK_UP -> up = true;
		}
	}
	public void keyReleased(int key) {
		switch(key) {
			case KeyEvent.VK_LEFT -> left = false;
			case KeyEvent.VK_RIGHT -> right = false;
			case KeyEvent.VK_UP -> up = false;
		}
	}

	@Override
	public void update() {
		if (left) angle = Math.min(angle + 3, 180);
		if (right) angle = Math.max(angle - 3, 0);

		if (up) {
			Bullet bb = new Bullet(parent);
			bb.x = this.x;
			bb.y = this.y;
			bb.angle = this.angle;
			bb.forward(2);
		}
	}

	@Override
	public void draw(Graphics g) {
		float temp = (float) Math.toRadians(angle);
		int endX = (int) (x + length * Math.cos(temp));
		int endY = (int) (y - length * Math.sin(temp));

		g.drawLine(
			(int) x, (int) y,
			endX, endY
		);
	}
}

class GamePanel extends JPanel {
	public final Player player;
	public final Helicopter heli;
	public final ArrayList<Bullet> danmaku = new ArrayList<>();
	public int score;
	private int time;
	private int elapsed;
	private boolean gameOver;

	public GamePanel() {
		setFocusable(true);
		player = new Player(this);
		heli = new Helicopter(this);
		time = 60;
		score = 0;
		gameOver = false;

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				player.keyPressed(e.getKeyCode());
			}

			@Override
			public void keyReleased(KeyEvent e) {
				player.keyReleased(e.getKeyCode());
			}
		});

		Timer timer = new Timer(16, e -> {
			if (!gameOver) {
				elapsed += 16;

				if (elapsed >= 1000) {
					--time;
					elapsed -= 1000;

					if (time <= 0) {
						time = 0;
						gameOver = true;
					}
				}

				update();
			}

			repaint();
		});

		timer.start();
	}

	private void update() {
		player.update();
		heli.update();

		for (Bullet bullet : danmaku) {
			bullet.update();
			CircleThing.collisionCheck(heli, bullet);
		}
		
		danmaku.removeIf(bullet -> bullet.removable);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		player.draw(g);
		heli.draw(g);

		for (Bullet bullet : danmaku) {
			bullet.draw(g);
		}

		if (!gameOver) {
			g.drawString("Left/Right keys to move. Up to shoot.", 10, 425);
			if (time <= 58) g.drawString("Yes, you can shoot rapidly, that's the feature.", 10, 435);
			g.drawString("Score: " + score, 10, 20);

			String timerText = "Time: " + time;
			int textWidth = g.getFontMetrics().stringWidth(timerText);

			g.drawString(
				timerText,
				getWidth() - textWidth - 10,
				20
			);
		} else {
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.WHITE);

			String gameOverText = "GAME OVER!";
			int gameOverWidth = g.getFontMetrics().stringWidth(gameOverText);

			g.drawString(
				gameOverText,
				(getWidth() - gameOverWidth) / 2,
				getHeight() / 2 - 20
			);

			String scoreText = "Final Score: " + score;
			int scoreWidth = g.getFontMetrics().stringWidth(scoreText);

			g.drawString(
				scoreText,
				(getWidth() - scoreWidth) / 2,
				getHeight() / 2 + 20
			);
		}
	}
	
	@Override
	public void addNotify() {
		super.addNotify();
		requestFocusInWindow();
	}
}