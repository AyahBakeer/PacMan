package application;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import javafx.scene.control.skin.TextInputControlSkin.Direction;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javafx.scene.control.skin.TextInputControlSkin.Direction;

public class Main extends JPanel implements KeyListener {
    private JButton startButton;

	private static final int MAZE_WIDTH = 15;
	private static final int MAZE_HEIGHT = 15;
	private static final int WALL_SIZE = 35;
	private static final int STEP_SIZE = 3;

	static private Maze maze;
	static private MidpointCircle pacman;
	static private ghosts ghost1;
	static private ghosts ghost2;
	static private ghosts ghost3;
	static private boolean quizDisplayed = false;
	private int deltaX = 0;
	private int deltaY = 0;
	
	

	public Main() {
		addKeyListener(this);
		setFocusable(true);

		maze = new Maze();
		pacman = new MidpointCircle(260, 270, 13, 30, 310, Color.YELLOW);
		ghost1 = new ghosts(320, 320, 10, 20, 20, Color.pink);
		ghost2 = new ghosts(920, 150, 10, 20, 20, Color.RED);
		ghost3 = new ghosts(520, 200, 10, 20, 20, Color.GREEN);
		QuizFrame quiz = new QuizFrame();
		  addKeyListener(this);
	        setFocusable(true);
	        setLayout(null);

	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		maze.paintComponent(g);
		pacman.draw(g);
		ghost1.draw(g);
		ghost2.draw(g);
		ghost3.draw(g);

	}

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setSize(1150,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Main panel = new Main();
		panel.setBackground(Color.BLACK);
		frame.add(panel);
		frame.setVisible(true);

		while (true) {
			panel.movePacman(null);
			panel.repaint();
			panel.MoveGhost1();
			panel.repaint();
			panel.MoveGhost2();
			panel.repaint();
			panel.MoveGhost3();
			panel.repaint();
			if (checkForCollision()) {
				 EventQueue.invokeLater(new Runnable() {
					   @Override
					    public void run() {
					        QuizFrame ex = new QuizFrame();
					        ex.setVisible(true);
					        if (ex.resultLabel.equals("Correct!")) {
					        	panel.movePacman(null);
								panel.repaint();
								panel.MoveGhost1();
								panel.repaint();
								panel.MoveGhost2();
								panel.repaint();
								panel.MoveGhost3();
								panel.repaint();
					    }
					   }
					});
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			
			}}}
	

	private boolean checkForWallCollision() {
		int pacmanRow = pacman.y / WALL_SIZE;
		int pacmanCol = pacman.x / WALL_SIZE;

		if (maze.hasWall(pacmanCol, pacmanRow, Direction.UP) && deltaY < 0) {
			deltaY = 0;
			return true;
		}

		if (maze.hasWall(pacmanCol, pacmanRow, Direction.DOWN) && deltaY > 0) {
			deltaY = 0;
			return true;
		}

		if (maze.hasWall(pacmanCol, pacmanRow, Direction.LEFT) && deltaX < 0) {
			deltaX = 0;
			return true;
		}

		if (maze.hasWall(pacmanCol, pacmanRow, Direction.RIGHT) && deltaX > 0) {
			deltaX = 0;
			return true;
		}

		return false;
	}
	
	
	private boolean checkForCircleCollision() {
		int pacmanRow = pacman.y / WALL_SIZE;
		int pacmanCol = pacman.x / WALL_SIZE;

		if (maze.hasCircle(pacmanCol, pacmanRow, Direction.UP,  WALL_SIZE) && deltaY < 0) {
			deltaY = 0;
			return true;
		}

		if (maze.hasCircle(pacmanCol, pacmanRow, Direction.DOWN , WALL_SIZE) && deltaY > 0) {
			deltaY = 0;
			return true;
		}

		if (maze.hasCircle(pacmanCol, pacmanRow, Direction.LEFT , WALL_SIZE) && deltaX < 0) {
			deltaX = 0;
			return true;
		}

		if (maze.hasCircle(pacmanCol, pacmanRow, Direction.RIGHT, WALL_SIZE) && deltaX > 0) {
			deltaX = 0;
			return true;
		}

		return false;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_UP && deltaY != STEP_SIZE) {
			deltaX = 0;
			deltaY = -STEP_SIZE;
		} else if (code == KeyEvent.VK_DOWN && deltaY != -STEP_SIZE) {
			deltaX = 0;
			deltaY = STEP_SIZE;
		} else if (code == KeyEvent.VK_LEFT && deltaX != STEP_SIZE) {
			deltaX = -STEP_SIZE;
			deltaY = 0;
		} else if (code == KeyEvent.VK_RIGHT && deltaX != -STEP_SIZE) {
			deltaX = STEP_SIZE;
			deltaY = 0;
		}
	}

	private void movePacman(Graphics g) {
		if (deltaX != 0 || deltaY != 0) {
			if (!checkForWallCollision()) {
				pacman.x += deltaX;
				pacman.y += deltaY;
			}
			if(checkForCircleCollision()) {
				maze.changecolor( g);
				}
		}
			int newX = pacman.x + deltaX;
			int newY = pacman.y + deltaY;

			int pacmanRow = newY / WALL_SIZE;
			int pacmanCol = newX / WALL_SIZE;

			if (maze.layout[pacmanRow][pacmanCol] == 0) {
				Color c = pacman.color;
				int alpha = 50;
				pacman.color = new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha);
			} else {
				pacman.x = newX;
				pacman.y = newY;
			}
		}

	

	private void MoveGhost1() {
		int deltaX = pacman.x - ghost1.x;
		int deltaY = pacman.y - ghost1.y;

		if (Math.abs(deltaX) > Math.abs(deltaY)) {
			if (deltaX > 0) {
				ghost1.x += 1;
			} else {
				ghost1.x -= 1;
			}
		} else {
			if (deltaY > 0) {
				ghost1.y += 1;
			} else {
				ghost1.y -= 1;
			}
		}
	}

	private void MoveGhost2() {
		int deltaX = pacman.x - ghost2.x;
		int deltaY = pacman.y - ghost2.y;

		if (Math.abs(deltaX) > Math.abs(deltaY)) {
			if (deltaX > 0) {
				ghost2.x += 1;
			} else {
				ghost2.x -= 1;
			}
		} else {
			if (deltaY > 0) {
				ghost2.y += 1;
			} else {
				ghost2.y -= 1;
			}
		}
	}

	private void MoveGhost3() {
		int deltaX = pacman.x - ghost3.x;
		int deltaY = pacman.y - ghost3.y;

		if (Math.abs(deltaX) > Math.abs(deltaY)) {
			if (deltaX > 0) {
				ghost3.x += 1;
			} else {
				ghost3.x -= 1;
			}
		} else {
			if (deltaY > 0) {
				ghost3.y += 1;
			} else {
				ghost3.y -= 1;
			}
		}
	}

	private static boolean checkForCollision() {
		int pacmanX = pacman.x + pacman.radius;
		int pacmanY = pacman.y + pacman.radius;
		int ghost1X = ghost1.x + ghost1.radius;
		int ghost1Y = ghost1.y + ghost1.radius;
		int ghost2X = ghost2.x + ghost2.radius;
		int ghost2Y = ghost2.y + ghost2.radius;
		int ghost3X = ghost3.x + ghost3.radius;
		int ghost3Y = ghost3.y + ghost3.radius;

		double distance1 = Math
				.sqrt((pacmanX - ghost1X) * (pacmanX - ghost1X) + (pacmanY - ghost1Y) * (pacmanY - ghost1Y));
		double distance2 = Math
				.sqrt((pacmanX - ghost2X) * (pacmanX - ghost2X) + (pacmanY - ghost2Y) * (pacmanY - ghost2Y));
		double distance3 = Math
				.sqrt((pacmanX - ghost3X) * (pacmanX - ghost3X) + (pacmanY - ghost3Y) * (pacmanY - ghost3Y));

		return distance1 <= pacman.radius + ghost1.radius || distance2 <= pacman.radius + ghost2.radius
				|| distance3 <= pacman.radius + ghost3.radius;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}