package br.com.battle;

import static java.awt.event.KeyEvent.VK_ESCAPE;

import java.awt.Graphics2D;

import br.pucpr.jge.GameManager;
import br.pucpr.jge.InputManager;
import br.pucpr.jge.Steps;

public class StarBattle2 implements Steps{
	public Boss boss = new Boss(250, 0);
	
	@Override
	public void load() {
		var ship = new Ship(500);
		GameManager.getInstance().add(new Background(0, 0));
		GameManager.getInstance().add(ship);
		GameManager.getInstance().add(boss);
	}

	@Override
	public boolean update(double s, InputManager keys) {
		if (keys.isDown(VK_ESCAPE)) {
			return false;
		}
		return true;
	}

	@Override
	public void draw(Graphics2D g2d) {
	}

	@Override
	public void unload() {

	}
}