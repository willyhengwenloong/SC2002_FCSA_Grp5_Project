package combatants;
import java.util.List;
import actions.Actions;

public class Wizard extends Player{
	private static final int Base_Hp = 200;
	private static final int Base_Attack = 50;
	private static final int Base_Defense = 10;
	private static final int Base_Speed = 20;
	
	//addition damage from killing with skills
	private int bonusDamage;
	
	public Wizard() {
		super("Wizard", Base_Hp, Base_Attack, Base_Defense, Base_Speed);
		this.bonusDamage = 0;
	}
	
	public String getSpecialSkillName() {
		return "Arcane Blast";
	}
	
	// return base attack and bonus from skills
	@Override
	public int getAttack() {
		return Base_Attack + bonusDamage;
	}
	
	public String executeSpecialSkill(List<Combatants> target, List<Combatants> allEnemy) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(getName()).append(" uses Arcane Blast → All Enemies: ")
		.append(getAttack()).append("\n");
		
		setSpecialSkillCooldown(3);
		for (Combatants enemy: target) {
			if(!enemy.isAlive()) continue;
			
			int enemyOldHp = enemy.getCurrentHp();
			int attack = getAttack();
			int damage = Math.max(0, attack - enemy.getEffectiveDefense());
			enemy.takeDamage(damage);
			
			sb.append(" ").append(enemy.getName())
			.append(" HP: ").append(enemyOldHp).append(" → ").append(enemy.getCurrentHp())
			.append(" (dmg: ").append(attack).append("-")
			.append(enemy.getBaseDefense()).append("=").append(damage).append(")");
			
			
			
			if(!enemy.isAlive()) {
				int before = getAttack();
				bonusDamage += 10;
				sb.append(" ✗ ELIMINATED | ATK: ").append(before)
						.append(" → ").append(getAttack()).append(" (+10)");
			}
			sb.append("\n");
		}
		
		
		return sb.toString().trim();
		
	}
	
	public Actions chooseAction(List<Combatants> targets, List<Combatants> allies) {
		return null;
	}
	
	public String getStatusSummary() {
        String base = super.getStatusSummary();
        if (bonusDamage > 0) {
            base += " | Arcane Bonus: +" + bonusDamage + " ATK";
        }
        return base;
    }
}
