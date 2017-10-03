import java.awt.Color;
import java.awt.Graphics;

public class Asteroid {
	double x, y, oldX, oldY, oldOldX, oldOldY;
	int radius ;
	int size = 2; 


	double heading;

	public Asteroid(double x, double y, double heading, int size) {
		this.x = x;
		this.y = y;
		this.heading = heading;
		this.size = size ;
		radius = size  * 12 ;
	}

	public void update() {
		oldOldX = oldX;
		oldOldY = oldY;
		oldX = x;
		oldY = y;
		x += Math.cos(heading) * 4;
		y += Math.sin(heading) * 4;
		

	}

	public void draw(Graphics gi) {
		gi.setColor(Color.green);
		gi.fillOval((int) (oldX - radius), (int) (oldY - radius), radius * 2, radius * 2);
		gi.setColor(Color.GRAY);
		gi.fillOval((int) (x - radius), (int) (y - radius), radius * 2, radius * 2);

	}

	public boolean distance(double X, double Y, int R) {
		double distance = 0;
		distance = Math.pow(X - x, 2) + Math.pow(Y - y, 2);
		
		distance = Math.pow(distance, .5);
	
		return (distance < R + radius);

	}
}
