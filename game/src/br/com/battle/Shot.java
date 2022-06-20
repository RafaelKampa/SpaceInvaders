package br.com.battle;

import br.pucpr.jge.AbstractGameObject;
import br.pucpr.jge.GameObject;
import br.pucpr.jge.InputManager;

public class Shot extends GameObject {
	
	public boolean isAlive = true;
    public Shot(double x, double y) {
        super("/image/shot.png", x, y);
    }

    public void update(double s, InputManager keys) {
        y -= 800 * s;
    }

    @Override
    public boolean isInGame() {
        return y > 10 && isAlive;
    }
    
    @Override
    public void onCollision(AbstractGameObject obj) {
    	if (obj instanceof Alien) {
    		isAlive = false;
		}
    	if (obj instanceof Boss) {
    		isAlive = false;
		}
    }
    

}
