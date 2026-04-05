package combatants;

public abstract class Combatants {
	private String name;
	private int maxHp;
	private int currentHp;
	private int attack;
	private int defense;
	private int speed;
	private boolean alive;
	private int defenseBuff;
	private int defenseBuffTurnsRemaining;
	
	public Combatants(String name, int hp, int attack, int defense, int speed) {
		this.name = name;
		this.maxHp = hp;
		this.currentHp = hp;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.alive = true;
		this.defenseBuff = 0;
		this.defenseBuffTurnsRemaining = 0;
	}
	
	//getter setter
	public String getName() {return name;}
	public int getMaxHp() {return maxHp;}
	public int getCurrentHp() {return currentHp;}
	public int getAttack() { return attack;}
	public int getDefense() {return defense;}
	public int getSpeed() {return speed;}
	public boolean isAlive() {return alive;}
	public int getDefenseBuff() { return defenseBuff; }
	public void setDefenseBuff(int defenseBuff) { this.defenseBuff = defenseBuff; }
	
	public int getDefenseBuffTurnsRemaining() { return defenseBuffTurnsRemaining; }
	public void setDefenseBuffTurnsRemaining(int defenseBuffTurnsRemaining) { this.defenseBuffTurnsRemaining = defenseBuffTurnsRemaining; }

	public void setCurrentHp(int newHp) {
		this.currentHp=newHp;
	}
	public void setAlive(boolean b) {
		this.alive=b;
		
	}
	
}




