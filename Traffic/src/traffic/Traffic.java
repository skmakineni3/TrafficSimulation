package traffic;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/*
 * This objective of this program is to create a traffic simulation
 * Author: Suhas Makineni
 * Date: 4/30/23
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Traffic implements ActionListener, Runnable {
	
	JFrame frame = new JFrame("Traffic Simulation");
	Road road = new Road();
	// south container
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	JLabel throughput = new JLabel("Throughput: 0");
	Container south = new Container();
	// west container
	JButton semi = new JButton ("Add Semi");
	JButton suv = new JButton ("Add SUV");
	JButton sports = new JButton ("Add Sports");
	Container west = new Container();
	boolean running = false;
	int carCount = 0;
	long startTime = 0;

	public Traffic() { // sets interface features
		frame.setSize(1000, 550);
		frame.setLayout(new BorderLayout());
		frame.add(road, BorderLayout.CENTER);
		
		// adds start, stop, and throughput to south of gui
		south.setLayout(new GridLayout(1, 3));
		south.add(start);
		start.addActionListener(this);
		south.add(stop);
		stop.addActionListener(this);
		south.add(throughput);
		frame.add(south, BorderLayout.SOUTH);
		
		// actions listeners for car adding buttons
		west.setLayout(new GridLayout(3,1));
		west.add(semi);
		semi.addActionListener(this);
		west.add(suv);
		suv.addActionListener(this);
		west.add(sports);
		sports.addActionListener(this);
		frame.add(west, BorderLayout.WEST);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Traffic();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(start)) { // when start button is pressed, start traffic
			if (running == false) {
				running = true;
				road.resetCarCount();
				startTime = System.currentTimeMillis(); // timer to determine throughput
				Thread t = new Thread(this);
				t.start();
			}
		}
		if (event.getSource().equals(stop)) { // when stop button is pressed, stop traffic
			running = false;
		}
		if (event.getSource().equals(semi)) { // adds semi into roadway
			Semi semi = new Semi(0, 20);
			road.addCar(semi);
			for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
				for (int y = 40; y < 480; y = y + 120) {
					semi.setX(x);
					semi.setY(y);
					if (road.collision(x, y, semi) == false) {
						frame.repaint();
						return;
					}
				}
			}
		}
		
		if (event.getSource().equals(suv)) { // adds suv into roadway
			SUV suv = new SUV(0, 20);
			road.addCar(suv);
			for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
				for (int y = 40; y < 480; y = y + 120) {
					suv.setX(x);
					suv.setY(y);
					if (road.collision(x, y, suv) == false) {
						frame.repaint();
						return;
					}
				}
			}
		}
		
		if (event.getSource().equals(sports)) { // adds sports car into roadway
			Sports sports = new Sports(0, 20);
			road.addCar(sports);
			for (int x = 0; x < road.ROAD_WIDTH; x = x + 20) {
				for (int y = 40; y < 480; y = y + 120) {
					sports.setX(x);
					sports.setY(y);
					if (road.collision(x, y, sports) == false) {
						frame.repaint();
						return;
					}
				}
			}
		}
	}

	@Override
	public void run() { // calculates throughput and steps through each stage of the cars moving
		while (running == true) {
			road.step();
			carCount = road.getCarCount();
			// calculating throughput
			double throughputCalc = carCount / (1000 * (double)(System.currentTimeMillis() - startTime));
			throughput.setText("Throughput: " + throughputCalc);
			frame.repaint();
			try {
				Thread.sleep(100);
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
