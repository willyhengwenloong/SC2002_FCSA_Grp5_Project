package combatants;
import java.util.List;
import items.Items;
import java.util.ArrayList;

public abstract class Player extends Combatants {
	
	private int specialSkillCooldown; 
	protected List<Items> items;
	
	public Player(String name, int hp, int attack, int defense, int speed) {
		super(name, hp, attack, defense, speed);
		this.specialSkillCooldown = 0;
		this.items = new ArrayList<>();
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
		for(Items item : items) {
			sb.append(" | ").append(item.getName()).append(": ").append(item.isUsed() ? "0":"1");
		}
		return sb.toString();
	}
	
	
	//items 
	
	public void addItem(Items item) {
		items.add(item);
	}
	
	public List<Items> getItems(){
		return items;
	}
	
	public boolean hasItems() {
		for(Items item: items) {
			if(!item.isUsed()) {
				return true;
			}
		}
		return false;
	}
	
	public List<Items> getAvailableItems() {
		List<Items> available = new ArrayList<>();
		for(Items item : items) {
			if (!item.isUsed()) {
				available.add(item);
			}
		}
		return available;
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
