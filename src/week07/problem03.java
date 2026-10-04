package week07;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

interface Function {
	double f(double x);
}

abstract class AbstractDrawFunction extends JPanel implements Function {
	public static ArrayList<AbstractDrawFunction> everything = new ArrayList<>();
	
	/**Polygon to hold the points*/
	private Polygon p = new Polygon();
	/**Default constructor*/
	protected AbstractDrawFunction() {
		setBackground(Color.white);
		everything.add(this);
	}
	/**Obtain points for x coordinates 100, 101, ..., 300*/
	public void drawFunction() {
		for (int x = -100; x <= 100; x++) {
			p.addPoint(x+200, 200-(int)f(x));
		}
	}
	/**Paint the function diagram*/
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Draw x axis
		g.drawLine(10, 200, 390, 200);
		// Draw y axis
		g.drawLine(200,30, 200, 390);
		// Draw arrows on x axis
		g.drawLine(390, 200, 370, 190);
		g.drawLine(390, 200, 370, 210);
		// Draw arrows on y axis
		g.drawLine(200, 30, 190, 50);
		g.drawLine(200, 30, 210, 50);
		// Draw x, y
		g.drawString("X", 370, 170);
		g.drawString("Y", 220, 40);
		// Draw a polygon line by connecting the points in the polygon
		g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
	}
}

class SquareF extends AbstractDrawFunction implements Function {
	public SquareF() {
		super();	drawFunction();
	}
	@Override
	public double f(double x) {
		return x*x;
	}
}
class SinF extends AbstractDrawFunction implements Function {
	public SinF() {
		super();	drawFunction();
	}
	@Override
	public double f(double x) {
		x = Math.toRadians(x);
		return 100*Math.sin(x);
	}
}
class CosF extends AbstractDrawFunction implements Function {
	public CosF() {
		super();	drawFunction();
	}
	@Override
	public double f(double x) {
		x = Math.toRadians(x);
		return 100*Math.cos(x);
	}
}
class TanF extends AbstractDrawFunction implements Function {
	public TanF() {
		super();	drawFunction();
	}
	@Override
	public double f(double x) {
		x = Math.toRadians(x);
		return 100*Math.tan(x);
	}
}
class Swap1F extends AbstractDrawFunction implements Function {
	public Swap1F() {
		super();	drawFunction();
	}
	@Override
	public double f(double x) {
		x = Math.toRadians(x);
		return 5*Math.sin(x)+Math.cos(x);
	}
}
class Swap2F extends AbstractDrawFunction implements Function {
	public Swap2F() {
		super();	drawFunction();
	}
	@Override
	public double f(double x) {
		x = Math.toRadians(x);
		return Math.sin(x)+5*Math.cos(x);
	}
}
class LogF extends AbstractDrawFunction implements Function {
	public LogF() {
		super();	drawFunction();
	}
	@Override
	public double f(double x) {
		return Math.log(x) + x*x;
	}
}

public class problem03 {
	public static void main(String[] args) {
		SquareF panel1 = new SquareF();
		SinF panel2 = new SinF();
		CosF panel3 = new CosF();
		TanF panel4 = new TanF();
		Swap1F panel5 = new Swap1F();
		Swap2F panel6 = new Swap2F();
		LogF panel7 = new LogF();
		
		for (AbstractDrawFunction panel : AbstractDrawFunction.everything) {
			JFrame frame = new JFrame();
			frame.setSize(400, 400);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//		frame.setLayout(new GridLayout(1, 2));
			frame.add(panel);
			frame.setVisible(true);
		}
	}
}
	