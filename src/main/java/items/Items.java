package items;

import java.util.List;
import combatants.*;

public interface Items {
	
	public String use(Player player, List<Combatants> targets);
	public String getName();
	public String getDescription();
	public boolean isUsed();
}
