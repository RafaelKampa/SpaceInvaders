package br.com.battle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Score2 {
	public static Integer score = 0;

	public static Integer getScore() {
		return score;
	}

	public static void setScore(Integer score) {
		Score2.score = score;
	}
	public void draw(Graphics2D g2d) {
		g2d.setFont(new Font("OCR-A BT", Font.PLAIN, 40));
		g2d.fillRect(0, 600, 800, 50);
		g2d.setColor(Color.WHITE);
		g2d.drawString(Score2.getScore().toString(), 0, 0);
	}
}
