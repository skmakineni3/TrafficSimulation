package traffic;

import java.awt.Color;
import java.awt.Graphics;
/*
 * This class is a sub class of Vehicles
 * Author: Suhas Makineni
 * Date: 4/30/23
 */
public class Semi extends Vehicle{
	
	
	public Semi(int newx, int newy) {
		super(newx, newy);
		width = 100;
		height = 40;
		speed = 5;
	}
	
	public void paintMe(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}
}
