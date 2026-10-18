package week09;

import java.awt.*;
import javax.swing.*;

public class P07_AngryBirds {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			AngryBirds frame = new AngryBirds();
			frame.setTitle("Angry Birds");
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}

class AngryBirds extends JFrame {
	private final GamePanel gamePanel = new GamePanel();

	private final JTextField txtHeight = new JTextField("-100", 10);
	private final JTextField txtSpeed = new JTextField("50", 10);
	private final JTextField txtAngle = new JTextField("30", 10);
	private final JTextField txtScore = new JTextField("0", 6);

	private int score = 0;

	public AngryBirds() {
		setLayout(new BorderLayout(8, 8));

		// Top: score
		JPanel top = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		top.add(new JLabel("SCORE:"));
		txtScore.setEditable(false);
		top.add(txtScore);
		add(top, BorderLayout.NORTH);

		// Center: game scene
		add(gamePanel, BorderLayout.CENTER);

		// Bottom: inputs and OK button
		JPanel controls = new JPanel(new GridLayout(4, 3, 5, 5));

		controls.add(new JLabel("Bird Position in Y-axis:"));
		controls.add(txtHeight);
		controls.add(new JLabel("pixels"));

		controls.add(new JLabel("Shooting Speed:"));
		controls.add(txtSpeed);
		controls.add(new JLabel("m/s"));

		controls.add(new JLabel("Angle:"));
		controls.add(txtAngle);
		controls.add(new JLabel("degrees"));

		JButton btnOK = new JButton("OK");
		controls.add(new JLabel());
		controls.add(btnOK);
		controls.add(new JLabel());

		add(controls, BorderLayout.SOUTH);

		btnOK.addActionListener(e -> calculateShot());
	}

	private void calculateShot() {
		try {
			double height = Double.parseDouble(txtHeight.getText());
			double speed = Double.parseDouble(txtSpeed.getText());
			double angle = Double.parseDouble(txtAngle.getText());

			if (speed <= 0 || angle < 0 || angle > 90 || height >= 0) {
				JOptionPane.showMessageDialog(
					this,
					"Enter a negative initial Y position, positive speed, " +
					"and an angle between 0 and 90 degrees."
				);
				return;
			}

			Projectile projectile = new Projectile(height, speed, angle);

			double time = projectile.getFlightTime();
			double distance = projectile.getLandingX();

			if (time < 0 || Double.isNaN(distance)) {
				JOptionPane.showMessageDialog(
					this, "The landing point could not be calculated."
				);
				return;
			}

			// Convert projectile distance to screen coordinates.
			double landingX = gamePanel.getLaunchX() + distance;
			double landingY = gamePanel.getGroundY();

			gamePanel.setBirdHeight(height);

			// Check whether the landing point is within 20 pixels
			// of the pig's position.
			boolean hit =
				Math.abs(landingX - (gamePanel.getPigX() + 17.5)) <= 20 &&
				Math.abs(landingY - (gamePanel.getPigY() + 17.5)) <= 20;

			if (hit) {
				score += 100;
				txtScore.setText(String.valueOf(score));

				JOptionPane.showMessageDialog(
					this,
					String.format(
						"Hit! Flight time: %.2f s, distance: %.2f",
						time, distance
					)
				);

				// Move the pig so the next shot has a new target.
				gamePanel.randomizePig();
			} else {
				JOptionPane.showMessageDialog(
					this,
					String.format(
						"Miss! Flight time: %.2f s, distance: %.2f",
						time, distance
					)
				);
			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(
				this, "Please enter valid numbers."
			);
		}
	}
}