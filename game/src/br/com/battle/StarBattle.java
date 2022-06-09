package br.com.battle;

import static java.awt.event.KeyEvent.VK_ESCAPE;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;

import br.pucpr.jge.GameFrame;
import br.pucpr.jge.GameManager;
import br.pucpr.jge.InputManager;
import br.pucpr.jge.Steps;

public class StarBattle implements Steps {

	@Override
	public void load() {
//		var ship = new Ship();
		GameManager.getInstance().add(new Ship());
//		GameManager.getInstance().add(new DebugRectangle(ship, Color.BLUE));
		for (var y = 0; y < 4; y++) {
			for (var x = 0; x < 5; x++) {
				var xOff = 50 + 50 * (y % 2);
				var alien = new Alien(x * 150 + xOff, y * 75 + 25);
				GameManager.getInstance().add(alien);
//				GameManager.getInstance().add(new DebugRectangle(alien, Color.RED));
			}
		}
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
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, 800, 600);
//		GameManager.getInstance().add(new Background(0, 0));
	}

	@Override
	public void unload() {

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> new GameFrame("Star Battle", 800, 600, new StarBattle()).setVisible(true));
	}
}
