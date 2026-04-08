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
	
	public void executeSpecialSkill(List<Combatants> target, List<Combatants> allEnemy) {
		Combatants enemy = target.get(0); //only getting one target
		
		int enemyOldHp = enemy.getCurrentHp();
		int damage = Math.max(0, getAttack() - enemy.getEffectiveDefense());
		enemy.takeDamage(damage);
		
		//apply stun effect here for 2 turns
		
		
		
		System.out.println(getName() + " uses Shield Bash on " + enemy.getName() 
		+ " HP: " + enemyOldHp + " → " 
		+ enemy.getCurrentHp() + " (dmg: " + getAttack() + "-" 
				+ enemy.getBaseDefense() + "=" + damage + ") | " + enemy.getName() + " STUNNED (2 turns)");
		
		if(!enemy.isAlive()) {
			System.out.println(" ✗ ELIMINATED");
		}
	}
}
