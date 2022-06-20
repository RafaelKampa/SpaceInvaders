package br.com.battle;

import static java.awt.event.KeyEvent.VK_ESCAPE;
//Desenvolvido por: Rafael Gilberto Kampa e Lucas Pego de Souza

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import br.pucpr.jge.GameFrame;
import br.pucpr.jge.GameManager;
import br.pucpr.jge.InputManager;
import br.pucpr.jge.Steps;

public class StarBattle implements Steps{
    ScoreListener score = new ScoreListener();
	
	@Override
	public void load() {
        loadFont();
		GameManager.getInstance().add(new Background(0, 0));
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
		GameManager.getInstance().add(new Ship(500));
//		A linha abaixo está comentada pois é utilizada apenas para visualizar o quadrado de debug para o hitBox
//		para visualizá-la é necessário comentar a linha que instancia a nave e descomentar a linha abaixo.
//		GameManager.getInstance().add(new DebugRectangle(new Ship(500), Color.BLUE));
		GameManager.getInstance().add(new Boss(250, -1000));
	}
	

	@Override
	public boolean update(double s, InputManager keys) {
		return !keys.isDown(VK_ESCAPE);
		
	}

    @Override
    public void draw(Graphics2D g2d) throws FontFormatException, IOException {
    	g2d.setColor(Color.CYAN);
    	g2d.fillRect(0, 600, 800, 50);
        g2d.setColor(Color.BLACK);
    	Font f = Font.createFont(Font.TRUETYPE_FONT, new File("src/br/com/battle/PressStart2P.ttf"));
        var font = f.deriveFont(Font.PLAIN, 40f);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        
        g2d.fillRect(0, 0, 800, 600);
        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(String.format("%05d", score.scorePoints), 600, 650);
    }
	
    public void loadFont() {
    }

	@Override
	public void unload() {
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> new GameFrame("Star Battle", 800, 650, new StarBattle()).setVisible(true));
	}
}