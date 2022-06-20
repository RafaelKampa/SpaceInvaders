package br.com.battle;

import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_SPACE;
import static java.awt.event.KeyEvent.VK_UP;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

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
	public boolean hasPowerUp;
	public int powerUpTime;

	
	public Ship(double y) {
		super("/image/xWing.png", 345, 550);
	}

	public void update(double s, InputManager keys) {
		if (hasPowerUp == false){
			shotInterval += s;
		}
		if (hasPowerUp == true){
			shotInterval += s * 4;
			//Contador de tempo para cortar o powerUp em 5 segundos
			new java.util.Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					hasPowerUp = false;
				}
			}, powerUpTime);
		}
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
			this.shotInterval = 0;
		}

		GameManager.getInstance().add(this.life0);
		GameManager.getInstance().add(this.life1);
		GameManager.getInstance().add(this.life2);
		this.contLife[0] = 1;
		this.contLife[1] = 2;
		this.contLife[2] = 3;
	}
	
	@Override
	public boolean isInGame() {
		return this.isAlive;
	}

	@Override
	public void onCollision(AbstractGameObject obj) {
		if (obj instanceof AlienShot || obj instanceof Alien || obj instanceof Boss) {
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
				this.isAlive = false;
				GameManager.getInstance().add(new Explosion(this.getX() + 25, this.getY()));
			}
		}
		if (obj instanceof PowerUp) {
			this.powerUpTime = this.powerUpTime + 5000;
			this.hasPowerUp = true;
		}
	}
}
