package combatants;

import java.util.List;

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
		
		
		StringBuilder sb = new StringBuilder();
		sb.append(getName()).append(" uses Shield Bash on ").append(enemy.getName())
		.append(" HP: ").append(enemyOldHp).append(" → ").append(enemy.getCurrentHp())
		.append(" (dmg: ").append(getAttack()).append("-").append(enemy.getBaseDefense())
		.append("=").append(damage).append(") | ")
		.append(enemy.getName()).append(" STUNNED (2 turns)");
		
		
		if(!enemy.isAlive()) {
			sb.append(" ✗ ELIMINATED");
		}
		
		return sb.toString();
	}
}
