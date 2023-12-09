package traffic;

import java.awt.Color;
import java.awt.Graphics;
/*
 * This class is a sub class of Vehicles
 * Author: Suhas Makineni
 * Date: 4/30/23
 */

public class SUV extends Vehicle{
	
	
	public SUV(int newx, int newy) {
		super(newx, newy);
		width = 60;
		height = 30;
		speed = 8;
	}
	
	public void paintMe(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}
}
