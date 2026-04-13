package items;

import java.util.List;

import combatants.Combatants;
import combatants.Player;

public class PowerStone implements Items{
	
	private boolean used = false;
	
	@Override
	public String use(Player player, List<Combatants> targets) {
		if (used) return "Power Stone already used!";
		used = true;
		
		int savedCooldown = player.getSpecialSkillCooldown();
		String result = player.executeSpecialSkill(targets, targets);
		player.setSpecialSkillCooldown(savedCooldown);
		
		return "Power Stone used --> " + player.getSpecialSkillName() + " triggered!\n" 
		+ result + "\n(Cooldown unchanged --> " + savedCooldown + ")";
	}
	
	@Override
	public String getName() {return "Power Stone";}
	@Override
	public String getDescription() {return "Free extra use of special skill. Does not affect cooldown.";}
	@Override
	public boolean isUsed() { return used;}
}
