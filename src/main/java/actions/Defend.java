package actions;
import java.util.List;
import combatants.Combatants;


public class Defend implements Actions{
	
	private static final int DEFENSE_ADDs=10;
	private static final int DURATION=2;
	
	@Override
	public String execute(Combatants user, List<Combatants> targets) {
		
		//Apply and set Defense
		user.applyDefenseBuff(DEFENSE_ADDs, DURATION);
		
		return user.getName() + " uses Defend. Defense increased by "
        		+ DEFENSE_ADDs + " for " + DURATION + " rounds.";
	}
	
	public String getName() { return "Defend";}
}
