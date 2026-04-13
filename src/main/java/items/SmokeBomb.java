package items;

import java.util.List;

import combatants.Combatants;
import combatants.Player;

public class SmokeBomb implements Items{
	private boolean used = false;
	
	public String use(Player player, List<Combatants> targets) {
		if(used) return "Smoke Bomb already used!";
		used = true;
		
		//implement status effect
		
		
		return player.getName() + " uses Smoke Bomb! Enemy attacks deal 0 damage this turn + next turn.";
 	}
	
	public String getName() { return "Smoke Bomb";}
	public String getDescription() {return "Enemy attacks deal 0 damage for current and next turn.";}
	public boolean isUsed() {return used;}
	
	
}
