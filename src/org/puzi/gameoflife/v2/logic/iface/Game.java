package org.puzi.gameoflife.v2.logic.iface;


public interface Game {
	public GameContext getState();
	public GameContext nextStep();
	public boolean addRule(Rule rule);
}
