package strategy;

import actions.Actions;
import combatants.Combatants;
import java.util.List;

public interface EnemyBehaviourStrategy {
	Actions decideAction(Combatants enemy, List<Combatants> targets);
}
