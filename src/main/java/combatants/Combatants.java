package combatants;

public abstract class Combatants {
	private String name;
	private int maxHp;
	private int currentHp;
	private int attack;
	private int defense;
	private int speed;
	private boolean alive;
	
	//Defense Buff
	private int defenseBuffAmount;
	private int defenseBuffTurnsRemaining;
	
	//Status Effect
	private boolean stunned;
	private int StunTurnsRemaining;
	private boolean smokeBombProtected;
	private int smokeBombTurnsRemaining;
	
	public Combatants(String name, int hp, int attack, int defense, int speed) {
		this.name = name;
		this.maxHp = hp;
		this.currentHp = hp;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.alive = true;
		this.defenseBuffAmount = 0;
		this.defenseBuffTurnsRemaining = 0;
		
		this.stunned=false;
		this.StunTurnsRemaining=0;
		this.smokeBombProtected = false;
		this.smokeBombTurnsRemaining = 0;
	}
	
	// Combatants Stats
	public String getName() {return name;}
	public int getMaxHp() {return maxHp;}
	public int getCurrentHp() {return currentHp;}
	public int getAttack() { return attack;}
	public int getBaseAttack() { return attack; }
	public int getBaseDefense() {return defense;}
	public int getSpeed() {return speed;}
	public boolean isAlive() {return alive;}
	
	public int getEffectiveDefense() {
		return defense + defenseBuffAmount;
	}
	
	
	
	
	
	//Setter Getter
	public void setAttack(int attack) {this.attack=attack;}
	public void setCurrentHp(int newHp) {this.currentHp=newHp;}
	public void setAlive(boolean b) {this.alive=b;}
	public void setDefenseBuff(int defenseBuff) { this.defenseBuffAmount = defenseBuff; }
	public void setDefenseBuffTurnsRemaining(int defenseBuffTurnsRemaining) { 
		this.defenseBuffTurnsRemaining = defenseBuffTurnsRemaining; }
	
	public int getDefenseBuff() { return defenseBuffAmount; }
	
	
	// HP Management-------------------------------------------------------
	// Capped at MaxHP
	public void heal(int amt) {
		currentHp = Math.min(maxHp, currentHp + amt);
	}
	
	public void takeDamage(int damage) {
		int dmg = Math.min(0, damage);
		currentHp = Math.min(0, currentHp - dmg);
		if(currentHp == 0) {
			alive = false;
		}
	}
	
	
	// Defense Buff --------------------------------------------------------
	public void applyDefenseBuff(int amount, int rounds) {
		defenseBuffAmount += amount;
		defenseBuffTurnsRemaining = Math.max(defenseBuffTurnsRemaining, rounds);
	}
	
	
	public void tickDefenseBuff() {
		if (defenseBuffTurnsRemaining > 0) {
			defenseBuffTurnsRemaining--;
			if (defenseBuffTurnsRemaining == 0) {
				defenseBuffAmount = 0;
			}
		}
	}
	
	public boolean hasDefenseBuff() {
		return defenseBuffTurnsRemaining > 0;
	}
	
	public int getDefenseBuffTurnsRemaining() { return defenseBuffTurnsRemaining; }
	
	
	
	
	
	
	// status effect--------------------------------------------------------------
	
	public boolean isStunned() {
		return stunned;
	}
	public void setStunned(boolean b) {
		// TODO Auto-generated method stub
		this.stunned=b;
	}
	public int getStunTurnsRemaining() {
		return StunTurnsRemaining;
	}
	public void setStunTurnsRemaining(int stunDuration) {
		// TODO Auto-generated method stub
		this.StunTurnsRemaining=stunDuration;
	}
	
	
	public boolean isSmokeBombProtected() { return smokeBombProtected; }

	public void setSmokeBombProtected(boolean smokeBombProtected) {
	    this.smokeBombProtected = smokeBombProtected;
	}
	

	public int getSmokeBombTurnsRemaining() { return smokeBombTurnsRemaining; }

	public void setSmokeBombTurnsRemaining(int smokeBombTurnsRemaining) {
	    this.smokeBombTurnsRemaining = smokeBombTurnsRemaining;
	}
	
	
	// Abstract Methods ------------------------------------------------------------
	//to return a summary of the combatant Stats
	public abstract String getStatusSummary();
}




