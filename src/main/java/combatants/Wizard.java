package combatants;
import java.util.List;

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
		return getAttack() + bonusDamage;
	}
	
	public void executeSpecialSkill(List<Combatants> target, List<Combatants> allEnemy) {
		System.out.println(this.getName() + "uses Arcane Blast --> All Enemies:");
		
		for (Combatants enemy: target) {
			if(!enemy.isAlive()) continue;
			
			int enemyOldHp = enemy.getCurrentHp();
			int attack = getAttack();
			int damage = Math.max(0, attack - enemy.getDefense());
			enemy.takeDamage(damage);
			
			System.out.println(enemy.getName() + " HP: " + enemyOldHp + " → " 
			+ enemy.getCurrentHp() + " (dmg: " + attack + "-" 
					+ enemy.getDefense() + "=" + damage + ")");
			
			if(!enemy.isAlive()) {
				int before = getAttack();
				bonusDamage += 10;
				System.out.println(" ✗ ELIMINATED | ATK: " + before 
						+ " → " + getAttack() + " (+10)");
			}
			
		}
		
	}
}
