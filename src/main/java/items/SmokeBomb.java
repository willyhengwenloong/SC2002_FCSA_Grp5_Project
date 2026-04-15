package items;

import java.util.List;
import combatants.Combatants;
import combatants.Player;
import statusEffects.SmokeBombEffect;


public class SmokeBomb implements Items{
	private boolean used = false;
	
	public String use(Player player, List<Combatants> targets) {
		if(used) return "Smoke Bomb already used!";
		used = true;
		
		// Duration 2: covers the current turn (enemies after player this round) + the next full round
		player.addStatusEffect(new SmokeBombEffect(2));
		return player.getName() + " → Smoke Bomb used: Enemy attacks deal 0 damage this turn + next ";
 	}
	
	public String getName() { return "Smoke Bomb";}
	public String getDescription() {return "Enemy attacks deal 0 damage for current and next turn.";}
	public boolean isUsed() {return used;}
	
	
}
