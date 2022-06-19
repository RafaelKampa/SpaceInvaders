package br.com.battle;

import br.pucpr.jge.AbstractGameObject;
import br.pucpr.jge.GameObject;
import br.pucpr.jge.InputManager;

public class AlienShot extends GameObject {
	
	public boolean isAlive = true;
    public AlienShot(double x, double y) {
        super("/image/intruderShot.png", x, y);
    }

    public void update(double s, InputManager keys) {
        y += 100 * s;
    }

    @Override
    public boolean isInGame() {
        return y < 590 && isAlive;
    }
    
    @Override
    public void onCollision(AbstractGameObject obj) {
    	if (obj instanceof Ship) {
    		isAlive = false;
		}
    }
    

}
