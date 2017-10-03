import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
	double x, y, oldX, oldY,oldOldX,oldOldY;
	double heading;
	int radius = 5;
	int distance = 0;

	public Bullet(double x, double y, double heading) {
		this.x = x;
		this.y = y;
		this.heading = heading;
	}

	public void update(){
		oldOldX = oldX;
		oldOldY = oldY;
		oldX = x;
		oldY = y;
		x += Math.cos(heading) * 6;
		y += Math.sin(heading) * 6;
		distance += 6;
	}
	
	public void draw(Graphics gi) {
		gi.setColor(Color.yellow);
		gi.fillRect((int)(oldOldX - radius), (int)(oldOldY - radius), radius *2,  radius *2);
		gi.setColor(Color.ORANGE);
		gi.fillRect((int)(oldX - radius), (int)(oldY - radius),  radius *2,  radius *2);
		gi.setColor(Color.RED);
		gi.fillRect((int)(x - radius), (int)(y - radius),  radius *2,  radius *2);
		
	}
}
