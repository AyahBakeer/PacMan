package application;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ghosts extends JPanel {
    int x;
	int y;
    int radius;
    private int rectWidth, rectHeight;
    private Color color;

    public ghosts(int x, int y, int radius, int rectWidth, int rectHeight, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.rectWidth = rectWidth;
        this.rectHeight = rectHeight;
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        

        // Draw top half of the circle
        int x1 = x - radius;
        int y1 = y - radius;
        int x2 = x + radius;
        int y2 = y + radius;
        midpointCircle(g, x, y, radius, false);
        g.fillArc(x1, y1, x2 - x1, y2 - y1, 0, 180);

        // Draw the rectangle
        int rectX = x - rectWidth / 2;
        int rectY = y + radius/100;
        int zigzagSpacing = 1;

        bresenhamLine(g, rectX, rectY, rectX + rectWidth, rectY);
        bresenhamLine(g, rectX + rectWidth, rectY, rectX + rectWidth, rectY + rectHeight);
        bresenhamLine(g, rectX + rectWidth, rectY + rectHeight, rectX, rectY + rectHeight);
        bresenhamLine(g, rectX, rectY + rectHeight, rectX, rectY);
        g.fillRect(rectX, rectY, rectWidth, rectHeight);
    }

    private void bresenhamLine(Graphics g, int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        while (true) {
            g.fillRect(x1, y1, 2, 2);

            if (x1 == x2 && y1 == y2) {
                break;
            }
            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }
    }

    private void midpointCircle(Graphics g, int x, int y, int radius, boolean fill) {
        int x0 = 0;
        int y0 = radius;
        int p = (5 - radius * 4) / 4;

        while (x0 <= y0) {
            if (fill) {
                g.fillRect(x + x0, y + y0, 2, 2);
                g.fillRect(x + y0, y + x0, 2, 2);
                g.fillRect(x - x0, y + y0, 2, 2);
                g.fillRect(x - y0, y + x0, 2, 2);
                        g.fillRect(x - x0, y + y0, 2, 2);
                        g.fillRect(x - y0, y + x0, 2, 2);
                        g.fillRect(x + x0, y - y0, 2, 2);
                        g.fillRect(x + y0, y - x0, 2, 2);
                        g.fillRect(x - x0, y - y0, 2, 2);
                        g.fillRect(x - y0, y - x0, 2, 2);
                    } else {
                        g.drawRect(x + x0, y + y0, 2, 2);
                        g.drawRect(x + y0, y + x0, 2, 2);
                        g.drawRect(x - x0, y + y0, 2, 2);
                        g.drawRect(x - y0, y + x0, 2, 2);
                        g.drawRect(x + x0, y - y0, 2, 2);
                        g.drawRect(x + y0, y - x0, 2, 2);
                        g.drawRect(x - x0, y - y0, 2, 2);
                        g.drawRect(x - y0, y - x0, 2, 2);
                    }

                    if (p < 0) {
                        p += 2 * x0 + 1;
                    } else {
                        p += 2 * (x0 - y0) + 5;
                        y0--;
                    }
                    x0++;
                }
            }
        }