package org.puzi.gameoflife.utils;

import static org.puzi.gameoflife.utils.CollectionUtils.filter;

import org.puzi.gameoflife.v2.logic.GenericGameContext;
import org.puzi.gameoflife.v2.logic.Location;
import org.puzi.gameoflife.v2.logic.iface.Rule;

public class BoardUtils {
	private Iterable<Location> getNeightbours(final Location location, final GenericGameContext context) {
		
		Iterable<Location> neighbourLocations = filter(context.getBoard().keySet(), new Predicate<Location>() {
			public boolean evaluate(Location object) {
				Integer dX = Math.abs(object.getX() - location.getX());
				Integer dY = Math.abs(object.getY() - location.getY());
				
				return dX == 1 && dY == 1;
			}
		});
		
		return neighbourLocations;
	}
	
	public Iterable<Location> getNeightbours(final Rule rule, Location location, final GenericGameContext context) {
		return filter(getNeightbours(location, context), new Predicate<Location>() {
			public boolean evaluate(Location location) {
				return CollectionUtils.equals(rule, context.getBoard().get(location).getFirst());
			}
		});
	}
}
	