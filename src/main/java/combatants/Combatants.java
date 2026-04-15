package combatants;

import java.util.ArrayList;
import java.util.List;
import actions.Actions;
import statusEffects.StatusEffect;

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
	
	// Status effects list
	protected List<StatusEffect> statusEffects;
	
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
		this.statusEffects = new ArrayList<>();
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
	// — Status Effects

	public void addStatusEffect(StatusEffect effect) {
	    statusEffects.add(effect);
	}

	public List<StatusEffect> getStatusEffects() {
	    return statusEffects;
	}

	public boolean hasStatusEffect(Class<? extends StatusEffect> type) {
	    for (StatusEffect e : statusEffects) {
	        if (type.isInstance(e)) return true;
	    }
	    return false;
	}

	/**
	 * Applies and ticks all status effects. Returns log messages.
	 */
	public List<String> processStatusEffects() {
	    List<String> logs = new ArrayList<>();
	    List<StatusEffect> toRemove = new ArrayList<>();
	    for (StatusEffect effect : statusEffects) {
	        String msg = effect.onTurnStart(this);
	        if (msg != null) logs.add(msg);
	        if (effect.isExpired()) toRemove.add(effect);
	    }
	    statusEffects.removeAll(toRemove);
	    return logs;
	}

	public void tickStatusEffects() {
	    List<StatusEffect> toRemove = new ArrayList<>();
	    for (StatusEffect e : statusEffects) {
	        e.tick();
	        if (e.isExpired()) toRemove.add(e);
	    }
	    statusEffects.removeAll(toRemove);
	}
	
	// Abstract Methods ------------------------------------------------------------
	//to return a summary of the combatant Stats
	public abstract String getStatusSummary();
	
	public abstract Actions chooseAction(List<Combatants> targets, List<Combatants> allies);
}




