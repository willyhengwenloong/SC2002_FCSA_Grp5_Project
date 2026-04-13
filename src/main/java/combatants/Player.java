package combatants;
import java.util.List;
import items.Items;

public abstract class Player extends Combatants {
	
	private int specialSkillCooldown; 
	
	public Player(String name, int hp, int attack, int defense, int speed) {
		super(name, hp, attack, defense, speed);
		this.specialSkillCooldown = 0;
		
	}
	
	public String getStatusSummary() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n  ").append(getName())
		.append(" | HP: ").append(getCurrentHp()).append("/").append(getMaxHp())
		.append(" | ATK: ").append(getAttack())
		.append(" | DEF: ").append(getEffectiveDefense())
		.append(" (base:").append(getBaseDefense()).append(")")
		.append(" | SPD: ").append(getSpeed());
		
		//SpecialSkillCooldown
		if(specialSkillCooldown > 0) {
			sb.append(" | ").append(getSpecialSkillName()).append(" CD: ").append(specialSkillCooldown);
		} else {
			sb.append(" | ").append(getSpecialSkillName()).append(": READY");
		}
		
		//append Items list here
		
		
		return sb.toString();
	}
	
	
	//items 
	
	
	public List<Items> getAvailableItems() {
		//the "pass" in java
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	// special skill cooldown
	public boolean isSpecialSkillReady() {
		return specialSkillCooldown == 0;
	}
	
	public int getSpecialSkillCooldown() {
		return specialSkillCooldown;
	}
	
	public void setSpecialSkillCooldown(int cooldown) {
		this.specialSkillCooldown = cooldown;
	}
	
	public void decrementCooldown() {
		if(specialSkillCooldown > 0) {
			specialSkillCooldown--;
		}
	}
	
	public abstract String executeSpecialSkill(List<Combatants> target, List<Combatants> allEnemy);
	
	//to display the special skill name for each classes
	public abstract String getSpecialSkillName();
	
}
