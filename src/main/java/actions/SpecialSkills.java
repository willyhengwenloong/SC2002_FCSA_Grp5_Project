package actions;
import java.util.List;

import combatants.Combatants;
public class SpecialSkills implements Actions {
	
	private List<Combatants> allEnemies;
	
	public SpecialSkills(List<Combatants> allEnemies) {
		this.allEnemies = allEnemies;
	}
	
	
	@Override
    public void execute(Combatants user) {
        System.out.println(user.getName() + " uses a special skill.");
	}
	
	public String getName() { return "Special Skill"; }
}
