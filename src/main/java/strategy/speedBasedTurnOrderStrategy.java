package strategy;

import combatants.Combatants;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class speedBasedTurnOrderStrategy implements TurnOrderStrategy {
	public List<Combatants> determineTurnOrder(List<Combatants> combatants){
		List<Combatants> ordered = new ArrayList<>(combatants);
		ordered.sort(Comparator.comparingInt(Combatants::getSpeed).reversed());
		return ordered;
	}
}
