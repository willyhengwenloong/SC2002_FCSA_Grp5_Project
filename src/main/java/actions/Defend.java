package actions;
import combatants.Combatants;
public class Defend implements Actions{
	
	private static final int DEFENSE_ADDs=10;
	private static final int DURATION=2;
	
	@Override
	public void execute(Combatants user) {
		
		//Apply and set Defense
		user.applyDefenseBuff(DEFENSE_ADDs, DURATION);
		
        System.out.println(user.getName() + " uses Defend. Defense increased by "
        		+ DEFENSE_ADDs + " for " + DURATION + " rounds.");
	}
}
