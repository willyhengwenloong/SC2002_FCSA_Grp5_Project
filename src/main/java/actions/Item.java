package actions;
import java.util.List;
import combatants.Combatants;
import items.Items;
import combatants.Player;

public class Item implements Actions{
	
	private Items items; 
	
	public Item(Items items) {
		this.items = items;
	}
	
	@Override
    public String execute(Combatants user, List<Combatants> targets) {
        if(!(user instanceof Player)) return "Only players can use items!";
		return items.use((Player) user, targets);
	}
	
	public String getName() { return "Item: " + items.getName();}
	
	public Items getItems() { return items;}
}
