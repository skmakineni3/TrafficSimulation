package traffic;

import java.awt.Color;
import java.awt.Graphics;
/*
 * This program is a sub class of Vehicles
 * Author: Suhas Makineni
 * Date: 4/30/23
 */

public class Sports extends Vehicle{
	
	
	public Sports(int newx, int newy) {
		super(newx, newy);
		width = 40;
		height = 20;
		speed = 10;
	}
	
	public void paintMe(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}
}
