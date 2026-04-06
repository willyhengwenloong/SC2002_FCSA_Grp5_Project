package items;

public interface Items {
	
	public String use(Player player, List<Combatant> targets);
	public String getName();
	public String getDescription();
	public boolean isUsed();
}
