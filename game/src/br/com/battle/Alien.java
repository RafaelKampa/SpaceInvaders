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

	public Alien(double x, double y) {
		super("/image/destroyer.png", x, y);
		this.initialX = x;
		ContadorAlien.setCont(ContadorAlien.getCont() + 1);
	}

	public void update(double s, InputManager keys) {
		if (y < 550) {
			y += 20 * s;
		} else {
			isAlive = false;
		}
		x = initialX + Math.sin(t) * 50;
		t += s;
		var alienShot = new AlienShot(getX() + 25, getY());
		cont ++;
		if (y > 0) {
			if (cont >= 120) {
				Random random = new Random();
				var rdn = random.nextInt(100);
				if (rdn == 10) {
					GameManager.getInstance().add(alienShot);
					shotInterval = 0;
					cont = 0;
				}
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
			Score2.setScore(Score2.getScore() + 100);
			ContadorAlien.setCont(ContadorAlien.getCont() - 1);
			isAlive = false;
			GameManager.getInstance().add(new Explosion(this.getX(), this.getY()));
			
		}
		if (obj instanceof Ship) {
			isAlive = false;
			GameManager.getInstance().add(new Explosion(this.getX(), this.getY()));
		}
	}
}
