package org.puzi.gameoflife.v2.logic;

import org.puzi.gameoflife.v2.logic.iface.Rule;


public abstract class GenericRule implements Rule {

	@Override
	public int compareTo(Rule r) {
		return this.getIdentifier() - r.getIdentifier(); 
	}
}