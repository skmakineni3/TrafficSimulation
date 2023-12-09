package traffic;

import java.awt.Color;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

/*
 * This class contains the roads and checks for collisions
 * Author: Suhas Makineni
 * Date: 11/30/23
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

public class Road extends JPanel {
	
	// creates roadway
	final int LANE_HEIGHT = 120;
	final int ROAD_WIDTH = 800;
	ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
	int carCount = 0;
	
	public Road() {
		super();
	}
	
	public void addCar(Vehicle v) { // adds cars when called
		cars.add(v);
	}
	
	public void paintComponent(Graphics g) { // paints and creates lanes 
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		for (int a = LANE_HEIGHT; a < LANE_HEIGHT * 4; a = a + LANE_HEIGHT) { // which lane
			for (int b = 0; b < getWidth(); b = b + 40) { // which line
				g.fillRect(b, a, 30, 5);
			}
		}
		// draw cars
		for (int a = 0; a < cars.size(); a++) {
			cars.get(a).paintMe(g);
		}
	}	
	
	public void step() { // moves cars forward, a.k.a. "stepping"
		for (int a = 0; a < cars.size(); a++) {
			Vehicle v = cars.get(a);
			if (collision(v.getX() + v.getSpeed(), v.getY(), v) == false) { // theres no collision
				v.setX(v.getX() + v.getSpeed());
				if (v.getX() > ROAD_WIDTH) {
					if (collision(0, v.getY(), v) == false) {
						v.setX(0);
						carCount++;
					}
				}	
			}
			else { // car ahead!
				if ((v.getY() > 40) && (collision(v.getX(), v.getY() - LANE_HEIGHT, v) == false)) {
						v.setY(v.getY() - LANE_HEIGHT);
				}
				else if ((v.getY() < 40 + 3 * LANE_HEIGHT) && (collision(v.getX(), v.getY() + LANE_HEIGHT, v) == false)) {
					v.setY(v.getY() + LANE_HEIGHT);
				}
			}
		}
	}
	
	public boolean collision(int x, int y, Vehicle v) { // collision prevention
		for (int a = 0; a < cars.size(); a++) {
			Vehicle u = cars.get(a);
			if (y == u.getY()) { // if i'm in the same lane
				if (u.equals(v) == false) { // if i'm not checking myself
					if (x < u.getX() + u.getWidth() && // my left side is left of their right
							x + v.getWidth() > u.getX()) { // my right side is right of their right
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public int getCarCount() { // gets car count
		return carCount;
	}
	
	public void resetCarCount() { // resets car count when called
		carCount = 0;
	}
}