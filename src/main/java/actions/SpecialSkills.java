package actions;
import combatants.Combatants;
public class SpecialSkills implements Actions {
	@Override
    public void execute(Combatants user) {
        System.out.println(user.getName() + " uses a special skill.");
	}

}
