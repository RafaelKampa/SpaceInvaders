package br.com.battle;

import br.pucpr.jge.GameObject;

public class ScoreIterator extends GameObject{
	public int value;

	public ScoreIterator(String spriteName, double x, double y) {
		super(spriteName, x, y);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
