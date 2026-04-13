package actions;

import java.util.List;

import combatants.Combatants;

public interface Actions {
	String execute(Combatants user, List<Combatants> targets);
	String getName();
}
