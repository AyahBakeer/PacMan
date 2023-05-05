package application;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MidpointCircle extends JPanel implements KeyListener {
	int xc, yc, r;
	int startAngle, arcAngle;
	Color color;
	int x, y;
	int xSpeed = 5;
	int ySpeed = 5;
	public int radius;

	public MidpointCircle(int xc, int yc, int r, int startAngle, int arcAngle, Color color) {
		this.xc = xc;
		this.yc = yc;
		this.r = r;
		this.startAngle = startAngle;
		this.arcAngle = arcAngle;
		this.color = color;
		this.x = xc - r;
		this.y = yc - r;
		this.addKeyListener(this);
		this.setFocusable(true);
	}

	private void midpointCircle(int xc, int yc, int r, Graphics g) {
		int x = 0;
		int y = r;
		int d = 1 - r;

		while (y > x) {
			g.fillRect(xc + x, yc + y, 1, 1);
			g.fillRect(xc + y, yc + x, 1, 1);
			g.fillRect(xc - x, yc + y, 1, 1);
			g.fillRect(xc - y, yc + x, 1, 1);
			g.fillRect(xc + x, yc - y, 1, 1);
			g.fillRect(xc + y, yc - x, 1, 1);
			g.fillRect(xc - x, yc - y, 1, 1);
			g.fillRect(xc - y, yc - x, 1, 1);

			if (d < 0) {
				d += 2 * x + 3;
			} else {
				d += 2 * (x - y) + 5;
				y--;
			}
			x++;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(color);
		g.fillArc(xc - r, yc - r, 2 * r, 2 * r, startAngle, arcAngle);
		g.setColor(Color.YELLOW);
		g.fillOval(xc - r, yc - r, 2 * r, 2 * r);
		g.setColor(Color.WHITE);
		g.fillArc(xc - r, yc - r, 2 * r, 2 * r, startAngle + arcAngle, 360 - arcAngle);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// Do nothing
	}
	public void move() {
		x += xSpeed;
		y += ySpeed;
		repaint();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int delta = 5;
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			yc -= delta;
			break;
		case KeyEvent.VK_DOWN:
			yc += delta;
			break;
		case KeyEvent.VK_LEFT:
			xc -= delta;
			break;
		case KeyEvent.VK_RIGHT:
			xc += delta;
			break;
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Do nothing
	}

	public void draw(Graphics g) {
	    g.setColor(Color.YELLOW);
	    g.fillArc(x - r, y - r, 2 * r, 2 * r, startAngle, arcAngle);
	}

	public void moveLeft() {
		x -= 10;
	}

	public void moveRight() {
		x += 10;
	}

	public void moveUp() {
		y -= 10;
	}

	public void moveDown() {
		y += 10;
	}
}
