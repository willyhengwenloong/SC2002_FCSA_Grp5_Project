package strategy;

import combatants.Combatants;
import java.util.List;
public interface TurnOrderStrategy {
	List<Combatants> determineTurnOrder(List<Combatants> combatants);
}
