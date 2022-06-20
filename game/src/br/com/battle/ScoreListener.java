package br.com.battle;

import br.pucpr.jge.Listener;

public class ScoreListener implements Listener {
    public int scorePoints = 0;
    
    public void notify(Object obs) {
        scorePoints += 1.5;
    }
}