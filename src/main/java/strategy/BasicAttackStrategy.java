package strategy;

import actions.Actions;
import actions.BasicAttack;
import combatants.Combatants;
import java.util.List;

public class BasicAttackStrategy implements EnemyBehaviourStrategy {
	public Actions decideAction(Combatants enemy, List<Combatants> targets) {
		return new BasicAttack();
	}
}
