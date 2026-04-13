package actions;

import combatants.Combatants;

public interface Actions {
	void execute(Combatants user);
	
	String getName();
}
