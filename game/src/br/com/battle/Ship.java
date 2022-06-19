package br.com.battle;

import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_SPACE;
import static java.awt.event.KeyEvent.VK_UP;

import br.pucpr.jge.AbstractGameObject;
import br.pucpr.jge.GameManager;
import br.pucpr.jge.GameObject;
import br.pucpr.jge.InputManager;

public class Ship extends GameObject {
	private double shotInterval = 0.3;
	public boolean isAlive = true;
	public int cont = 3;
	public int[] contLife = new int[3];
	public Life life0 = new Life(0, 600);
	public Life life1 = new Life(55, 600);
	public Life life2 = new Life(110, 600);
//	public Boss boss = new Boss(250, 0);
	
	public Ship(double y) {
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
			if (y > 50) {
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

		GameManager.getInstance().add(life0);
		GameManager.getInstance().add(life1);
		GameManager.getInstance().add(life2);
		contLife[0] = 1;
		contLife[1] = 2;
		contLife[2] = 3;
//		if (ContadorAlien.getCont() == 0) {
//			GameManager.getInstance().add(boss);
//		}
	}
	
	@Override
	public boolean isInGame() {
		return isAlive;
	}

	@Override
	public void onCollision(AbstractGameObject obj) {
		if (obj instanceof AlienShot) {
			this.cont = cont - 1;
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
				GameManager.getInstance().add(new Explosion(this.getX(), this.getY()));
			}
		}
		
		if (obj instanceof Alien) {
			this.cont = cont - 1;
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
				GameManager.getInstance().add(new Explosion(this.getX(), this.getY()));
			}
		}
	}
}
