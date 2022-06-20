package br.com.battle;

import br.pucpr.jge.AbstractGameObject;
import br.pucpr.jge.GameObject;
import br.pucpr.jge.InputManager;

public class Life extends GameObject {
	public boolean isAlive = true;

	public Life(double x, double y) {
		super("/image/life.png", x, y);
	}
	

	public void update(double s, InputManager keys) {
	}

	@Override
	public boolean isInGame() {
		return isAlive;
	}
	
	@Override
	public void onCollision(AbstractGameObject obj) {
	}
}
