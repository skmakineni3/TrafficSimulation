package traffic;

import java.awt.Graphics;
/*
 * This class is a parent class to all the vehicles
 * Author: Suhas Makineni
 * Date: 4/30/23
 */

public class Vehicle {
	int x;
	int y;
	int width = 0;
	int height = 0;
	int speed;
	
	public Vehicle(int newx, int newy) {
		x = newx;
		y = newy;
	}
	
	public void paintMe(Graphics g) {
		
	}
	
	public int getX() {
		return x;
	}
	public void setX(int newx) {
		x = newx;
	}
	public int getSpeed() {
		return speed;
	}
	public int getY() {
		return y;
	}
	public void setY(int newy) {
		y = newy;
	}
	public int getWidth() {
		return width;
	}
}
