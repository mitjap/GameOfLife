package org.puzi.gameoflife.v2.logic.iface;

import java.util.Map;

import org.puzi.gameoflife.utils.Pair;
import org.puzi.gameoflife.v2.logic.Location;

public interface GameContext {
	public Map<Location, Pair<Rule, Integer>> getBoard();

	public Location getLocation();

	public Location getBoardSize();

}
