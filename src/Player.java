import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player implements Basics {
	double x = 0, y = 0, oldX = 0, oldY = 0, width = 32, height = 32, xVel = 0, yVel = 0;
	boolean up = false, right = false, down = false, left = false;
	Rectangle bounding;
	int health = 3;

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void update() {
		if (up) {
			yVel -= .1;
		}
		if (right) {
			xVel += .1;
		}
		if (down) {
			yVel += .1;
		}
		if (left) {
			xVel -= .1;
		}
		if (xVel > 4) {
			xVel = 4;
		}
		if (xVel < -4) {
			xVel = -4;
		}
		if (yVel > 4) {
			yVel = 4;
		}
		if (yVel < -4) {
			yVel = -4;
		}
		x += xVel;
		y += yVel;
		xVel *= .99;
		yVel *= .99;
		bounding = new Rectangle((int) x, (int) y, (int) width, (int) height);

	}

	public void draw(Graphics gi) {
		gi.setColor(Color.BLUE);
		gi.fillOval((int) (x - width / 2), (int) (y - height / 2), (int) width, (int) height);
		gi.setColor(Color.CYAN);
		gi.fillOval((int) (x - width / 4), (int) (y - height / 4), (int) width / 2, (int) height / 2);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

}
