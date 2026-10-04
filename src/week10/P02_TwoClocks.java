package week10;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class P02_TwoClocks {
	public static void main(String[] args) {
		JFrame frame = new LiveClocks();
		frame.setTitle("Clock Animation 6804062612188");
		frame.setSize(600, 300);
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

final class LiveClocks extends JFrame {
	private final StillClock thailand = new StillClock(7, "THAILAND");
	private final StillClock japan = new StillClock(9, "JAPAN");

	public LiveClocks() {
		setLayout(new GridLayout(1, 2));
		
		add(thailand);
		add(japan);

		// Create a timer with delay 1000 ms
		Timer timer = new Timer(16, new TimerListener());
		timer.start();
	}

	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Set new time and repaint the clock to display current time
			thailand.setCurrentTime();
			thailand.repaint();
			japan.setCurrentTime();
			japan.repaint();
		}
	}
}

final class StillClock extends JPanel {
	public final String country;
	public final int hourOffset;
	public int hour;
	public int minute;
	public int second;
	public int ms;

	public StillClock(int hourOffset, String name) {
		this.country = name;
		this.hourOffset = hourOffset;
		setCurrentTime();
	}
	
	public void setCurrentTime() {
		Calendar calendar = new GregorianCalendar();
		
		this.hour = calendar.get(Calendar.HOUR_OF_DAY) + hourOffset;
		this.hour %= 24;
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
		this.ms = calendar.get(Calendar.MILLISECOND);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(300, 300);
	}
	/** Draw the clock */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Initialize clock parameters
		int clockRadius = (int) (0.5 * 0.8 * Math.min(getWidth(), getHeight()));

		int xCenter = getWidth() / 2;
		int yCenter = getHeight() / 2;

		// Draw circle
		g.setColor(Color.black);

		g.drawOval(
				xCenter - clockRadius, yCenter - clockRadius,
				2 * clockRadius, 2 * clockRadius
		);

		g.drawString("12", xCenter - 5, yCenter - clockRadius + 12);
		g.drawString("9", xCenter - clockRadius + 3, yCenter + 5);
		g.drawString("3", xCenter + clockRadius - 10, yCenter + 3);
		g.drawString("6", xCenter - 3, yCenter + clockRadius - 3);
		g.drawString(country, xCenter- country.length()*3 , yCenter+20);
		g.drawString("GMT+" + hourOffset, xCenter-20, yCenter+40);

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		// Draw second hand
		double secondValue = second + ms/1000.0;
		g.setColor(Color.green);
		drawHand(g, xCenter, yCenter,
				(int) (clockRadius * 0.8),
				secondValue, 60);

		// Draw minute hand
		double minuteValue = minute + secondValue/60.0;
		g.setColor(Color.blue);
		drawHand(g, xCenter, yCenter,
				(int) (clockRadius * 0.65),
				minuteValue, 60);

		// Draw hour hand
		double hourValue = (hour % 12) + minuteValue/60;
		g.setColor(Color.red);
		drawHand(g, xCenter, yCenter,
				(int) (clockRadius * 0.5),
				hourValue, 12);
	}
	
	private void drawHand(Graphics g, int xCenter, int yCenter,
			int length, double value, double units) {

		double angle = value * (2 * Math.PI / units);

		int x = (int) (xCenter + length * Math.sin(angle));
		int y = (int) (yCenter - length * Math.cos(angle));

		g.drawLine(xCenter, yCenter, x, y);
	}
}