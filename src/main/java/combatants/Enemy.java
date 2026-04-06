package combatants;

public abstract class Enemy extends Combatants{
	public Enemy(String name, int hp, int attack, int defense, int speed) {
		super(name, hp, attack, defense, speed);
		
	}
	
	public String getStatusSummary() {
		return "TEST Summary";
	}
}
