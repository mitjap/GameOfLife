package org.puzi.gameoflife.v2.logic;

import java.util.Map;

import org.puzi.gameoflife.utils.Pair;
import org.puzi.gameoflife.v2.logic.iface.GameContext;
import org.puzi.gameoflife.v2.logic.iface.Rule;

public class GenericGameContext implements GameContext {
	private Location location;
	private Map<Location,Pair<Rule,Integer>> board;
	private Location boardSize;
	
	public GenericGameContext (Location boardSize, Map<Location,Pair<Rule,Integer>> board) {
		this.boardSize = boardSize;
		this.board = board;
	}
	
	public GenericGameContext setLocation(Location location) {
		this.location = location;
		return this;
	}

	public Location getLocation() {
		return location;
	}
	
	public Map<Location,Pair<Rule,Integer>> getBoard() {
		return board;
	}
	
	public Location getBoardSize() {
		return boardSize;
	}
}
