package br.com.battle;

import static java.awt.event.KeyEvent.VK_ESCAPE;


import java.awt.*;
import java.io.File;
import java.io.IOException;

import br.pucpr.jge.GameFrame;
import br.pucpr.jge.GameManager;
import br.pucpr.jge.InputManager;
import br.pucpr.jge.Steps;
//Desenvolvido por: Rafael Gilberto Kampa e Lucas Pego de Souza
public class StarBattle implements Steps{
    ScoreListener score = new ScoreListener();
    Ship ship = new Ship(500);
    Boss boss = new Boss(250, -800);
	
	@Override
	public void load() {
        loadFont();
		GameManager.getInstance().add(new Background(0,0));
		for (var y = 0; y < 4; y++) {
			for (var x = 0; x < 5; x++) {
				var xOff = 50 + 50 * (y % 2);
				var alien = new Alien(x * 150 + xOff, y * 75 - 280);
				alien.addObserver(score);
				GameManager.getInstance().add(alien);
//				A linha abaixo está comentada pois é utilizada apenas para visualizar o quadrado de debug para o hitBox
//				para visualizá-la é necessário comentar a linha que instancia o alien e descomentar a linha abaixo.
//				GameManager.getInstance().add(new DebugRectangle(alien, Color.RED)); 
			}
		}
		GameManager.getInstance().add(ship);
//		A linha abaixo está comentada pois é utilizada apenas para visualizar o quadrado de debug para o hitBox
//		para visualizá-la é necessário comentar a linha que instancia a nave e descomentar a linha abaixo.
//		GameManager.getInstance().add(new DebugRectangle(new Ship(500), Color.BLUE));
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
    public void draw(Graphics2D g2d) throws FontFormatException, IOException {
		Color startColor = Color.CYAN;
		Color endColor = Color.RED;
		GradientPaint gradient = new GradientPaint(0, 0, startColor, 800, 50, endColor);
		g2d.setPaint(gradient);
		g2d.fillRect(0, 600, 800, 50);
		g2d.setColor(Color.BLACK);
		Font f = Font.createFont(Font.TRUETYPE_FONT, new File("src/br/com/battle/PressStart2P.ttf"));
		var font = f.deriveFont(Font.PLAIN, 40f);

		g2d.fillRect(0, 0, 800, 600);
		g2d.setColor(Color.BLACK);
		g2d.setFont(font);
		g2d.drawString(String.format("%05d", score.scorePoints), 600, 650);
    }
    
	@Override
	public void draw2(Graphics2D g2d) throws FontFormatException, IOException {
		Font f = Font.createFont(Font.TRUETYPE_FONT, new File("src/br/com/battle/PressStart2P.ttf"));
		var font = f.deriveFont(Font.PLAIN, 40f);
		g2d.setColor(Color.WHITE);
		g2d.setFont(font);
		if(!ship.isAlive) {
			g2d.drawString("GAME OVER", 225, 300);
		}
		if(!boss.isAlive) {
			g2d.drawString("YOU WIN", 240, 300);
		}
	}

    public void loadFont() {
    }

	@Override
	public void unload() {
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> new GameFrame("Tretas Estelares", 800, 650, new TelaInicial()).setVisible(true));
	}
}