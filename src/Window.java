import java.awt.Color;

import java.awt.Graphics;
import java.awt.Point;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;


public class Window extends JFrame implements Basics {

	BufferedImage bi = new BufferedImage(1920, 1040, BufferedImage.TYPE_INT_RGB);
	Graphics gi = bi.createGraphics();
	Player player = new Player(300, 300);
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	ArrayList<Powerup> powerups = new ArrayList<Powerup>();
	int difficulty = 3;
	int shots = 1;
	int injured = 0;
	Bullet b;
	Asteroid a;
	Powerup p;


	public Window() {
		
		importImages();
		setUndecorated(true);
		setSize(1920, 1040);
		setLocation(0, 0);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("stuff");
		
		this.addKeyListener(key);
		this.addMouseListener(mouse);
		update.start();
		gt.start();
	}

	public void paint(Graphics g) {
		gi.setColor(Color.BLACK);
		gi.fillRect(0, 0, 1920, 1040);
		player.draw(gi);
		for (Powerup p : powerups) {
			p.draw(gi);
		}
		for (Bullet b : bullets) {
			b.draw(gi);
		}
		for (Asteroid a : asteroids) {
			a.draw(gi);
		}
		if (injured > 0 && injured % 5 < 3) {
			gi.setColor(Color.white);
			gi.drawOval((int) player.x - 18, (int) player.y - 18, 36, 36);
		}
		for (int i = 0; i < player.health; i++) {
			gi.setColor(Color.cyan);
			gi.drawOval(50 + i * 70, 50, 50, 50);
		}
		g.drawImage(bi, 0, 0, 1920, 1040, null);
	}

	public void update() {
		player.update();
		if (player.x < 0) {
			player.x = 1920;
		} else if (player.x > 1920) {
			player.x = 0;
		}
		if (player.y < 0) {
			player.y = 1020;
		} else if (player.y > 1020) {
			player.y = 0;
		}
		for (int i = 0; i < powerups.size(); i++) {
			p = powerups.get(i);
			if (p.distance(player.x, player.y, 16)) {
				if (shots <= 3) {
					shots++;
				} else {
					player.health++;
				}
				powerups.remove(i);
			}
		}
		for (int i = 0; i < bullets.size(); i++) {
			b = bullets.get(i);
			b.update();
			if (b.distance >= 1200) {
				bullets.remove(i);
			}
			if (b.x < 0) {
				b.x = 1920;
			} else if (b.x > 1920) {
				b.x = 0;
			}
			if (b.y < 0) {
				b.y = 1020;
			} else if (b.y > 1020) {
				b.y = 0;
			}

		}
		for (int i = 0; i < asteroids.size(); i++) {
			a = asteroids.get(i);
			a.update();
			if (a.x < 0) {
				a.x = 1920;
			} else if (a.x > 1920) {
				a.x = 0;
			}
			if (a.y < 0) {
				a.y = 1020;
			} else if (a.y > 1020) {
				a.y = 0;
			}
			if (a.distance(player.x, player.y, 16) && injured == 0) {
				player.health--;
				if (a.size > 1) {
					asteroids.add(new Asteroid(a.x, a.y, Math.random() * Math.PI * 2 - Math.PI, a.size - 1));
					asteroids.add(new Asteroid(a.x, a.y, Math.random() * Math.PI * 2 - Math.PI, a.size - 1));
				}
				injured = 200;
				asteroids.remove(i);

			}
			if (injured > 0) {
				injured--;
			}
		}
		for (int i = 0; i < bullets.size(); i++) {
			for (int j = 0; j < asteroids.size(); j++) {
				Bullet b = bullets.get(i);
				if (asteroids.get(j).distance(b.x, b.y, b.radius)) {
					if (Math.random() < .1)
						powerups.add(new Powerup((int) asteroids.get(j).x, (int) asteroids.get(j).y));
					if (asteroids.get(j).size > 1) {
						asteroids.add(new Asteroid(asteroids.get(j).x, asteroids.get(j).y,
								Math.random() * Math.PI * 2 - Math.PI, asteroids.get(j).size - 1));
						asteroids.add(new Asteroid(asteroids.get(j).x, asteroids.get(j).y,
								Math.random() * Math.PI * 2 - Math.PI, asteroids.get(j).size - 1));
					}
					asteroids.remove(j);
					bullets.remove(i);
					break;
				}
			}
		}

		if (asteroids.size() == 0) {

			for (int i = 0; i < difficulty; i++) {
				asteroids.add(new Asteroid(Math.random() * 1920, Math.random() * 1020,
						Math.random() * Math.PI * 2 - Math.PI, 1 + difficulty / 3));
				if (asteroids.get(i).distance(player.x, player.y, 316)) {
					System.out.println("derp");
					asteroids.remove(i);
					i--;
				}
			}
			difficulty++;
		}
		if (player.health <= 0) {
			JOptionPane j = new JOptionPane();
			j.showMessageDialog(null, "You know you lost");

			System.exit(0);
		}
	}

	Timer update = new Timer(10, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			update();

		}

	});
	Timer gt = new Timer(33, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			repaint();

		}

	});

	MouseListener mouse = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent m) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent m) {
		
			// TODO Auto-generated method stub
			Point mouse = new Point(m.getX() - (8), m.getY() - (19));
			if (m.getButton() == (MouseEvent.BUTTON1)) {
				double heading = 0;
				heading = Math.atan2(mouse.getY() - player.getY(), mouse.getX() - player.getX());
				bullets.add(new Bullet(player.getX(), player.getY(), heading));
				for (int i = 1; i < shots; i++) {
					bullets.add(new Bullet(player.getX(), player.getY(), heading + i * Math.PI / 60));
					bullets.add(new Bullet(player.getX(), player.getY(), heading - i * Math.PI / 60));
				}

			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	};

	KeyListener key = new KeyListener() {

		@Override
		public void keyPressed(KeyEvent k) {
			// TODO Auto-generated method stub
			if (k.getKeyCode() == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}
			if (k.getKeyCode() == KeyEvent.VK_W) {
				player.setUp(true);
			}
			if (k.getKeyCode() == KeyEvent.VK_D) {
				player.setRight(true);
			}
			if (k.getKeyCode() == KeyEvent.VK_S) {
				player.setDown(true);
			}
			if (k.getKeyCode() == KeyEvent.VK_A) {
				player.setLeft(true);
			}
		}

		@Override
		public void keyReleased(KeyEvent k) {
			// TODO Auto-generated method stub

			if (k.getKeyCode() == KeyEvent.VK_W) {
				player.setUp(false);
			}
			if (k.getKeyCode() == KeyEvent.VK_D) {
				player.setRight(false);
			}
			if (k.getKeyCode() == KeyEvent.VK_S) {
				player.setDown(false);
			}
			if (k.getKeyCode() == KeyEvent.VK_A) {
				player.setLeft(false);
			}
		}

		@Override
		public void keyTyped(KeyEvent k) {
			// TODO Auto-generated method stub

		}

	};

	public void importImages() {

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Window();

	}

}
