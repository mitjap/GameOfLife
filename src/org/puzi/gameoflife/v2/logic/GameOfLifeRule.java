package org.puzi.gameoflife.v2.logic;

import org.puzi.gameoflife.v2.logic.iface.GameContext;

public abstract class GameOfLifeRule extends GenericRule {

	
	
	public Integer applyRule(GameContext context) {
		Location location = context.getLocation();
		
		if (location == null) {
			return null;
		}
		
		return null;
		
		
	}

}
