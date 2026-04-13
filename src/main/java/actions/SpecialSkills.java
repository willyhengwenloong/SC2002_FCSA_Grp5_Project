package actions;
import java.util.List;

import combatants.Combatants;
import combatants.Player;
public class SpecialSkills implements Actions {
	
	private List<Combatants> allEnemies;
	
	public SpecialSkills(List<Combatants> allEnemies) {
		this.allEnemies = allEnemies;
	}
	
	
	@Override
    public String execute(Combatants user, List<Combatants> targets) {
        if(!(user instanceof Player)) return "Only players have special skills!";
        Player player = (Player) user;
        return player.executeSpecialSkill(targets, allEnemies);
	}
	
	public String getName() { return "Special Skill"; }
}
