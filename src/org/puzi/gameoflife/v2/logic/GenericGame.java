package org.puzi.gameoflife.v2.logic;

import static org.puzi.gameoflife.utils.CollectionUtils.collect;
import static org.puzi.gameoflife.utils.CollectionUtils.filter;
import static org.puzi.gameoflife.utils.CollectionUtils.transform;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.puzi.gameoflife.utils.Pair;
import org.puzi.gameoflife.utils.Predicate;
import org.puzi.gameoflife.utils.Transformer;
import org.puzi.gameoflife.v2.logic.iface.Game;
import org.puzi.gameoflife.v2.logic.iface.GameContext;
import org.puzi.gameoflife.v2.logic.iface.Rule;

public class GenericGame implements Game {
	
	private Set<Rule> rules;
	private Map<Location, Pair<Rule,Integer>> board;
	private Location size;

	public GenericGame(int sizeX, int sizeY) {
		size = new Location(sizeX,sizeY);
		board = new TreeMap<Location, Pair<Rule,Integer>>();
		rules = new TreeSet<Rule>();
	}
	
	public GameContext getState() {
		return createGameContext();
	}

	public GameContext nextStep() {
		final GenericGameContext context = createGameContext();
		if (rules.isEmpty()) {
			return context;
		}
		Map<Location,Pair<Rule,Integer>> changes = new TreeMap<Location,Pair<Rule,Integer>>();
		for (Map.Entry<Location, Pair<Rule,Integer>> field : board.entrySet()) {
			context.setLocation(field.getKey());
			Rule rule = field.getValue().getFirst();
			
			// no rule is already applied
			if (rule == null) {
				Set<Pair<Rule,Integer>> rulz = collect(filter(transform(rules, 
				new Transformer<Rule, Pair<Rule,Integer>>() {
					public Pair<Rule,Integer> transform(Rule rule) {
						return new Pair<Rule, Integer>(rule, rule.applyRule(context));
					}
				}), 
				new Predicate<Pair<Rule,Integer>>() {
					private int c = 0;
					public boolean evaluate(Pair<Rule, Integer> pair) {
						return pair.getSecond() == null ? false : c++ < 2;
					}
				}), 
				new HashSet<Pair<Rule,Integer>>());
				
				// only one rule can be applied at once
				if (rulz.size() == 1) {
					changes.put(field.getKey(), rulz.iterator().next());
				}
				
			} 
			// rule is already applied
			else {
				Integer status = rule.applyRule(context);
				changes.put(field.getKey(), new Pair<Rule,Integer>(status != null ? rule : null, status));
			}
		}
		
		// apply changes
		board.putAll(changes);
 		
		return context;
	}

	public boolean addRule(Rule rule) {
		return rules.add(rule);
	}	
	
	private GenericGameContext createGameContext() {
		return new GenericGameContext(size, Collections.unmodifiableMap(this.board));
	}
}
