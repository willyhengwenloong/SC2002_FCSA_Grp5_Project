package actions;
import combatants.Combatants;
public class Defend implements Actions{
	private static final int DEFEND_ADDs=10;
	private static final int DURATION=2;
	
	@Override
	public void execute(Combatants user) {
		user.setDefenseBuff(DEFEND_ADDs);
		user.setDefenseBuffTurnsRemaining(DURATION);
        System.out.println(user.getName() + " uses Defend. Defense increased by 10 for this turn and next turn.");
	}
}
