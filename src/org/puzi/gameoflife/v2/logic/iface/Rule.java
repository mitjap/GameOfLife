package org.puzi.gameoflife.v2.logic.iface;


public interface Rule extends Comparable<Rule> {
	public int getIdentifier();
	public Integer applyRule(GameContext context);
}

