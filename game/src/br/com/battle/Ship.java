package br.com.battle;

import br.pucpr.jge.AbstractGameObject;
import br.pucpr.jge.GameManager;
import br.pucpr.jge.GameObject;
import br.pucpr.jge.InputManager;

import static java.awt.event.KeyEvent.*;

public class Ship extends GameObject {
	private double shotInterval = 0.3;
	public boolean isAlive = true;

	public Ship() {
		super("/image/intruder.png", 375, 550);
	}

	public void update(double s, InputManager keys) {
		shotInterval += s;
		if (keys.isDown(VK_RIGHT)) {
			if (x < 750) {
				x += 400 * s;
			}
		} else if (keys.isDown(VK_LEFT)) {
			if (x > 0) {
				x -= 400 * s;
			}
		} else if (keys.isDown(VK_UP)) {
			if (y > 0) {
				y -= 400 * s;
			}
		} else if (keys.isDown(VK_DOWN)) {
			if (y < 550) {
				y += 400 * s;
			}
		}

		if (keys.isDown(VK_SPACE) && shotInterval > 0.3) {
			var shot = new Shot(getX() + 25, getY());
			GameManager.getInstance().add(shot);
			shotInterval = 0;
		}
	}
	
	@Override
	public boolean isInGame() {
		return isAlive;
	}

	@Override
	public void onCollision(AbstractGameObject obj) {
		if (obj instanceof AlienShot) {
			isAlive = false;
			GameManager.getInstance().add(new Explosion(this.getX(), this.getY()));
			
		}
		if (obj instanceof Alien) {
			isAlive = false;
			GameManager.getInstance().add(new Explosion(this.getX(), this.getY()));
		}
	}
}
