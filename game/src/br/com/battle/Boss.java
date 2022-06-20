package br.com.battle;

import java.util.Random;

import br.pucpr.jge.AbstractGameObject;
import br.pucpr.jge.GameManager;
import br.pucpr.jge.GameObject;
import br.pucpr.jge.InputManager;
import br.pucpr.jge.Listener;

public class Boss extends GameObject{
	private double initialX;
	private double t;
	protected boolean isAlive = true;
	public double shotInterval = 0.5;
	public int cont;
	private int lifes = 30;

	public Boss(double x, double y) {
		super("/image/DeathStar.png", x, y);
		this.initialX = x;
	}

	public void update(double s, InputManager keys) {
		if (y < 0) { 
			y += 30 * s;
		} else {
			y = 0;
		}
		x = initialX + Math.sin(t) * 250;
		t += s;
		var alienShot = new AlienShot(getX() + 108, getY() + 88);
		if (getY() >= 0) {
			cont ++;
			if (cont >= 0) {
				Random random = new Random();
				var rdn = random.nextInt(30);
				if (rdn == 10) {
					GameManager.getInstance().add(alienShot);
					cont = 0;
				}
			}
			var alienShot2 = new AlienShot(getX() + 20, getY() + 150);
			cont ++;
			if (cont >= 0) {
				Random random = new Random();
				var rdn = random.nextInt(70);
				if (rdn == 10) {
					GameManager.getInstance().add(alienShot2);
					cont = 0;
				}
			}
			var alienShot3 = new AlienShot(getX() + 280, getY() + 150);
			cont ++;
			if (cont >= 0) {
				Random random = new Random();
				var rdn = random.nextInt(70);
				if (rdn == 10) {
					GameManager.getInstance().add(alienShot3);
					cont = 0;
				}
			}
			var alienShot4 = new AlienShot(getX() + 150, getY() + 280);
			cont ++;
			if (cont >= 0) {
				Random random = new Random();
				var rdn = random.nextInt(70);
				if (rdn == 10) {
					GameManager.getInstance().add(alienShot4);
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
		if (obj instanceof Shot || obj instanceof Ship) {
			lifes = lifes - 1;
			notifyObserver();
			if (lifes == 0) {
				isAlive = false;
			}
		}
		if (isAlive == false) {
			GameManager.getInstance().add(new Explosion(this.getX() + 150, this.getY()));
			GameManager.getInstance().add(new Explosion(this.getX() + 150, this.getY() + 150));
			GameManager.getInstance().add(new Explosion(this.getX(), this.getY() + 150));
			GameManager.getInstance().add(new Explosion(this.getX() + 150, this.getY() + 150));
			GameManager.getInstance().add(new Explosion(this.getX() + 300, this.getY() + 150));
			GameManager.getInstance().add(new Explosion(this.getX() + 150, this.getY() + 300));
		}
	}
	
    private void notifyObserver() {
        for (Listener listener : Ship.listeners) {
            listener.notify(this);
        }
    }
    
    public void addObserver(Listener listener) {
        Ship.listeners.add(listener);
    }
    
    public void removerListener(Listener listener) {
        Ship.listeners.remove(listener);
    }
}
