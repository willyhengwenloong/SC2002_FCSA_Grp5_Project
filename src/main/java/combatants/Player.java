package combatants;
import java.util.List;
import items.Items;

public abstract class Player extends Combatants {
	
	private int specialSkillCooldown; 
	
	public Player(String name, int hp, int attack, int defense, int speed) {
		super(name, hp, attack, defense, speed);
		
	}
	
	public String getStatusSummary() {
		return "Test Summary";
	}
	
	public int getSpecialSkillCooldown() {
		return specialSkillCooldown;
	}
	
	//to display the special skill name for each classes
	public abstract String getSpecialSkillName();
	
	public List<Items> getAvailableItems() {
		//the "pass" in java
		throw new UnsupportedOperationException("Not implemented yet");
	}
}
