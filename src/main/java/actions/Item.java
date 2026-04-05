package actions;
import combatants.Combatants;
public class Item implements Actions{
	@Override
    public void execute(Combatants user) {
        System.out.println(user.getName() + " uses an item.");
	}
}
