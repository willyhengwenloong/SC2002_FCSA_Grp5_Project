package items;

import java.util.List;

import combatants.*;

public class Potion implements Items {
	private static final int healamount = 100;
	private boolean used = false;
	
	public String use(Player player, List<Combatants> targets) {
		if (used) {
			return "Potion used!";
		}
		int oldHP = player.getCurrentHp();
		player.heal(healamount);
		used = true;
		return player.getName() + "uses portion!" + "HP: " + oldHP +"-->" + player.getCurrentHp();
	}
	
	public String getName() {
		return "Potions";
	}
	public String getDescription() {
		return "Heal 100HP (new HP = current HP + 100)"; 
	}
	public boolean isUsed() {
		return used;
	}
}
