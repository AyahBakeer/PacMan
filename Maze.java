package application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import javafx.scene.control.skin.TextInputControlSkin.Direction;

public class Maze extends JPanel {
	int x;
	int y;
	static int[][] layout = {
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
			{ 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
			{ 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };
	
	
	public Maze(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	private List<Point> walls = new ArrayList<Point>();
	private Point start, end;
	private double blockSize = 35;


	public Maze() {
		for (int row = 0; row < layout.length; row++) {
			for (int col = 0; col < layout[row].length; col++) {
				if (layout[row][col] == 1) {
					walls.add(new Point(col * 40, row * 40));
				} else {
					if (start == null) {
						start = new Point(col * 40, row * 40);
					} else if (end == null) {
						end = new Point(col * 40, row * 40);
					}
				}
			}
		}
		
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1280, 720);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		double blockSize = 35;

		// Draw the walls
		for (int row = 0; row < layout.length; row++) {
			for (int col = 0; col < layout[0].length; col++) {
				int x = (int) (col * blockSize);
				int y = (int) (row * blockSize);

				if (layout[row][col] == 1) {
					// Create a polygon for the wall using Bresenham's line algorithm
					List<Double> points = bresenhamLine(x, y, (int) (x + blockSize), y);
					points.addAll(bresenhamLine((int) ((int) (x) + blockSize), y, (int) (x + blockSize), (int) (y + blockSize)));
					points.addAll(bresenhamLine((int) (x + blockSize),(int) ( y + blockSize), x, y + (int) (blockSize)));
					points.addAll(bresenhamLine(x, (int) (y + blockSize), x, y));

					int[] xPoints = new int[points.size() / 2];
					int[] yPoints = new int[points.size() / 2];

					for (int i = 0; i < points.size() / 2; i++) {
						xPoints[i] = points.get(2 * i).intValue();
						yPoints[i] = points.get(2 * i + 1).intValue();
					}

					g.setColor(new Color(0, 0, 255, 200));
					g.fillPolygon(xPoints, yPoints, xPoints.length);
					g.setColor(new Color(0, 0, 255, 200));
					g.drawPolygon(xPoints, yPoints, xPoints.length);
				}
			}
		}
		   super.paintComponent(g);
	        for (int row = 0; row < layout.length; row++) {
	            for (int col = 0; col < layout[0].length; col++) {
	                int x = (int) (col * blockSize);
	                int y = (int) (row * blockSize);

	                if (layout[row][col] == 0) {
	                    // Create a circle for the empty space
	                    g.setColor(Color.YELLOW);
	                    g.fillOval((int)(x + blockSize / 2), (int)(y + blockSize / 2), (int)(blockSize / 8), (int)(blockSize / 5));
	                    g.setColor(Color.YELLOW);
	                    g.drawOval((int)(x + blockSize / 2), (int)(y + blockSize / 2), (int)(blockSize / 8), (int)(blockSize / 5));
	                }
	            }
	        }
	    }

	private List<Double> bresenhamLine(int x0, int y0, int x1, int y1) {
		List<Double> points = new ArrayList<>();
		int dx = Math.abs(x1 - x0);
		int dy = Math.abs(y1 - y0);
		int sx = x0 < x1 ? 1 : -1;
		int sy = y0 < y1 ? 1 : -1;
		int err = dx - dy;
		while (true) {
			points.add((double) x0);
			points.add((double) y0);
			if (x0 == x1 && y0 == y1) {
				break;
			}
			int e2 = 2 * err;
			if (e2 > -dy) {
				err -= dy;
				x0 += sx;
			}
			if (e2 < dx) {
				err += dx;
				y0 += sy;
			}
		}
		return points;
	}


	public boolean hasWall(int x, int y, Direction direction) {
	    int row = y;
	    int col = x;
	    boolean hasWall = true;

	    switch (direction) {
	        case UP:
	            row--;
	            break;
	        case DOWN:
	            row++;
	            break;
	        case LEFT:
	            col--;
	            break;
	        case RIGHT:
	            col++;
	            break;
	    }

	    if (row < 0 || row >= Maze.layout.length || col < 0 || col >= Maze.layout[0].length) {
	        // Return true for out-of-bounds positions
	        return true;
	    }

	    return Maze.layout[row][col] == 1;
	}
	public boolean hasCircle(int x, int y, Direction direction, int blockSize ) {
	    int row = y;
	    int col = x;
	    boolean hasCircle = true;

	    switch (direction) {
	        case UP:
	            row--;
	            break;
	        case DOWN:
	            row++;
	            break;
	        case LEFT:
	            col--;
	            break;
	        case RIGHT:
	            col++;
	            break;
	    }

	    if (row < 0 || row >= Maze.layout.length || col < 0 || col >= Maze.layout[0].length) {
	        // Do nothing for out-of-bounds positions
	   return true;
	    }
	    return Maze.layout[row][col] == 1;

	}
	public void changecolor (Graphics g) {
	    boolean hasCircle = true;
	    int row = y;
	    int col = x;
	    if (row < 0 || row >= Maze.layout.length || col < 0 || col >= Maze.layout[0].length) {
	    hasCircle = Maze.layout[row][col] == 0;
	    if (!hasCircle) {
	        g.setColor(Color.BLACK);
	        g.fillOval((int)(x + blockSize / 2), (int)(y + blockSize / 2), (int)(blockSize / 8), (int)(blockSize / 5));
	        g.setColor(Color.BLACK);
	        g.drawOval((int)(x + blockSize / 2), (int)(y + blockSize / 2), (int)(blockSize / 8), (int)(blockSize / 5));
	    }
	}
	}
	



	public Point getStart() {
		return start;
	}

	public Point getEnd() {
		return end;
	}
}