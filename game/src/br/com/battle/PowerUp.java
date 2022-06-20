package br.com.battle;

import br.pucpr.jge.AbstractGameObject;
import br.pucpr.jge.GameObject;
import br.pucpr.jge.InputManager;

public class PowerUp extends GameObject {
	public boolean isAlive = true;

    public PowerUp(double x, double y) {
        super("/image/x2.png", x, y);
    }

    public void update(double s, InputManager keys) {
        y += 100 * s;
    }

    @Override
    public boolean isInGame() {
        return y < 550 && isAlive;
    }
    
    @Override
    public void onCollision(AbstractGameObject obj) {
    	if (obj instanceof Ship) {
    		isAlive = false;
		}
    }
}
