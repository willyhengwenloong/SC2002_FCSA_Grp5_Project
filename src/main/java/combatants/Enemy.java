package combatants;

import actions.Actions;
import strategy.EnemyBehaviourStrategy;
import java.util.List;

public abstract class Enemy extends Combatants{
	
	protected EnemyBehaviourStrategy behaviourStrategy;
	
	public Enemy(String name, int hp, int attack, int defense, int speed, EnemyBehaviourStrategy behaviourStrategy) {
		super(name, hp, attack, defense, speed);
		this.behaviourStrategy = behaviourStrategy;
		
	}
	
	public void setBehaviourStrategy(EnemyBehaviourStrategy strategy) {
		this.behaviourStrategy = strategy;
	}
	
	public Actions chooseAction(List<Combatants> targets, List<Combatants> allies) {
		return behaviourStrategy.decideAction(this, targets);
	}
	
	
	
	public String getStatusSummary() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName())
		.append(" | HP: ").append(getCurrentHp()).append("/").append(getMaxHp())
		.append(" | ATK: ").append(getAttack())
		.append(" | DEF: ").append(getEffectiveDefense())
		.append(" | SPD: ").append(getSpeed());
		
		// statusEffect [STUNNED] etc
		if (!statusEffects.isEmpty()) {
	        sb.append(" | Effects: ");
	        statusEffects.forEach(e -> sb.append(e.getName()).append("(").append(e.getDuration()).append(") "));
		}
		return sb.toString();
	}
}
