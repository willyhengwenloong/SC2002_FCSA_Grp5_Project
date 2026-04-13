package actions;
import combatants.Combatants;
import items.Items;

public class Item implements Actions{
	
	private Items items; 
	
	public Item(Items items) {
		this.items = items;
	}
	
	@Override
    public void execute(Combatants user) {
        System.out.println(user.getName() + " uses an item.");
        
	}
	
	public String getName() { return "Item: " + items.getName();}
	
	public Items getItems() { return items;}
}
