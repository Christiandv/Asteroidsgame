import java.awt.Color;
import java.awt.Graphics;

public class Powerup {
	int x, y, radius;
	
	public Powerup(int x, int y){
		this.x = x;
		this.y = y;
		radius = 10;
	}
	
	public void draw(Graphics gi){
		gi.setColor(Color.PINK);
		gi.fillOval((int) (x - radius), (int) (y - radius), radius * 2, radius * 2);
	}
	public boolean distance(double X, double Y, int R) {
		double distance = 0;
		distance = Math.pow(X - x, 2) + Math.pow(Y - y, 2);
		
		distance = Math.pow(distance, .5);
	
		return (distance < R + radius);

	}
	
}
