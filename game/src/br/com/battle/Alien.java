package br.com.battle;

import java.util.Random;

import br.pucpr.jge.AbstractGameObject;
import br.pucpr.jge.GameManager;
import br.pucpr.jge.GameObject;
import br.pucpr.jge.InputManager;

public class Alien extends GameObject {
	private double initialX;
	private double t;
	private boolean isAlive = true;
	public double shotInterval = 0.5;
	public int cont;
//	protected BufferedImage sprite1;
//	protected BufferedImage sprite2;

	public Alien(double x, double y) {
		super("/image/destroyer.png", x, y);
		this.initialX = x;
//		sprite1 = new Loader().loadImage("/image/destroyer.png");
//		sprite2 = new Loader().loadImage("/image/explosion.png");
	}
	
//	public void draw(Graphics2D g2d) {
//		if (isAlive) {
//			g2d.drawImage(sprite1, (int) x, (int) y, null);
//		}
//		else {
//			g2d.drawImage(sprite2, (int) x, (int) y, null);
//		}
//		g2d.setColor(Color.WHITE);
//		g2d.setFont(new Font("Arial", Font.BOLD,15));
//		g2d.drawString(String.valueOf(isAlive), 20, 20);
//	}

	public void update(double s, InputManager keys) {
		x = initialX + Math.sin(t) * 50;
//		y = y + t * 0.02;
		t += s;
		var alienShot = new AlienShot(getX() + 25, getY());
		cont ++;
		if (cont >= 120) {
			Random random = new Random();
			var rdn = random.nextInt(1000);
			if (rdn == 10) {
				GameManager.getInstance().add(alienShot);
				shotInterval = 0;
				cont = 0;
			}
		}
	}

	@Override
	public boolean isInGame() {
		return isAlive;
	}

	@Override
	public void onCollision(AbstractGameObject obj) {
		if (obj instanceof Shot) {
			isAlive = false;
			GameManager.getInstance().add(new Explosion(this.getX(), this.getY()));
		}
		if (obj instanceof Ship) {
			isAlive = false;
			GameManager.getInstance().add(new Explosion(this.getX(), this.getY()));
		}
	}
}
