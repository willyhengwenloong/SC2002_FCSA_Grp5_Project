package combatants;

public abstract class Enemy extends Combatants{
	public Enemy(String name, int hp, int attack, int defense, int speed) {
		super(name, hp, attack, defense, speed);
		
	}
	
	public String getStatusSummary() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName())
		.append(" | HP: ").append(getCurrentHp()).append("/").append(getMaxHp())
		.append(" | ATK: ").append(getAttack())
		.append(" | DEF: ").append(getEffectiveDefense())
		.append(" | SPD: ").append(getSpeed());
		
		// statusEffect [STUNNED] etc
		
		return sb.toString();
	}
}
