package br.com.battle;

import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_SPACE;
import static java.awt.event.KeyEvent.VK_UP;

import java.util.ArrayList;
import java.util.List;

import br.pucpr.jge.AbstractGameObject;
import br.pucpr.jge.GameManager;
import br.pucpr.jge.GameObject;
import br.pucpr.jge.InputManager;
import br.pucpr.jge.Listener;

public class Ship extends GameObject {
	private double shotInterval = 0.3;
	public boolean isAlive = true;
	public int cont = 3;
	public int[] contLife = new int[3];
	public Life life0 = new Life(0, 600);
	public Life life1 = new Life(55, 600);
	public Life life2 = new Life(110, 600);
	public static final List<Listener> listeners = new ArrayList<Listener>();
	
	public Ship(double y) {
		super("/image/xWing.png", 345, 550);
	}

	public void update(double s, InputManager keys) {
		shotInterval += s*2;
		if (keys.isDown(VK_RIGHT)) {
			if (x < 680) {
				x += 400 * s;
			}
		} else if (keys.isDown(VK_LEFT)) {
			if (x > 10) {
				x -= 400 * s;
			}
		} else if (keys.isDown(VK_UP)) {
			if (y > 50) {
				y -= 400 * s;
			}
		} else if (keys.isDown(VK_DOWN)) {
			if (y < 545) {
				y += 400 * s;
			}
		}

		if (keys.isDown(VK_SPACE) && shotInterval > 0.3) {
			var shot = new Shot(getX() + 52.5, getY() - 40);
			GameManager.getInstance().add(shot);
			shotInterval = 0;
		}

		GameManager.getInstance().add(life0);
		GameManager.getInstance().add(life1);
		GameManager.getInstance().add(life2);
		contLife[0] = 1;
		contLife[1] = 2;
		contLife[2] = 3;
	}
	
	@Override
	public boolean isInGame() {
		return isAlive;
	}

	@Override
	public void onCollision(AbstractGameObject obj) {
		if (obj instanceof AlienShot || obj instanceof Alien) {
			this.cont = this.cont - 1;
			if (this.contLife[this.cont] == 3) {
				this.contLife[this.cont] = 0;
				life2.isAlive = false;
			} else if (this.contLife[this.cont] == 2) {
				this.contLife[this.cont] = 0;
				life1.isAlive = false;
			} else {
				this.contLife[this.cont] = 0;
				life0.isAlive = false;
			}
			if (life0.isAlive == false) {
				isAlive = false;
				GameManager.getInstance().add(new Explosion(this.getX() + 25, this.getY()));
			}
		}
	}
}
