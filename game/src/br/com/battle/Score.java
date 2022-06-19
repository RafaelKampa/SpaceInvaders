package br.com.battle;

import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import br.pucpr.jge.AbstractGameObject;

public class Score {

	public Score(int value, double x, double y) {
		super();
		
	}
	
	public int value;
	public List<AbstractGameObject> objects = new ArrayList<>();
	
	public void draw(Graphics2D g2d) {
		g2d.setFont(new Font("OCR-A BT", Font.PLAIN, 40));
		g2d.fillRect(0, 600, 800, 50);
		g2d.drawString(String.format("%05d", this.getValue()), 650, 635);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}

