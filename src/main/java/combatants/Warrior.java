package combatants;

import java.util.List;
import actions.Actions;
import statusEffects.StunEffect;
import combatants.Combatants;

public class Warrior extends Player {
	private static final int Base_HP = 260;
	private static final int Base_Attack = 40;
	private static final int Base_Defense = 20;
	private static final int Base_Speed = 30;
	
	public Warrior() {
		super("Warrior", Base_HP, Base_Attack, Base_Defense, Base_Speed);
	}
	
	public String getSpecialSkillName() {
		return "Shield Bash";
	}
	
	public String executeSpecialSkill(List<Combatants> target, List<Combatants> allEnemy) {
		Combatants enemy = target.get(0); //only getting one target
		
		int enemyOldHp = enemy.getCurrentHp();
		int damage = Math.max(0, getAttack() - enemy.getEffectiveDefense());
		enemy.takeDamage(damage);
		
		//apply stun effect here for 2 turns
		// Apply stun for 2 turns (current turn already in effect; BattleEngine ticks after skip)
	    enemy.addStatusEffect(new StunEffect(2));
	    setSpecialSkillCooldown(3);
		
		StringBuilder sb = new StringBuilder();
		sb.append(getName()).append(" → Shield Bash → ").append(enemy.getName())
		.append(": HP: ").append(enemyOldHp).append(" → ").append(enemy.getCurrentHp())
		.append(" (dmg: ").append(getAttack()).append("-").append(enemy.getBaseDefense())
		.append("=").append(damage).append(") | ")
		.append(enemy.getName()).append(" STUNNED (2 turns)");
		
		
		if(!enemy.isAlive()) {
			sb.append(" ✗ ELIMINATED");
		}
		
		return sb.toString();
	}
	
	public Actions chooseAction(List<Combatants> targets, List<Combatants> allies) {
		return null;
	}
}
