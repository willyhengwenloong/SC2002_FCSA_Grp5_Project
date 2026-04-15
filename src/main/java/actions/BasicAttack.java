package actions;

import java.util.List;
import combatants.Combatants;

public class BasicAttack implements Actions{
		
	public String execute(Combatants user, List<Combatants> targets) {
		if (targets == null || targets.isEmpty()) { return user.getName() + " has no target!";}
        Combatants target = targets.get(0);
        
        
		int damage = Math.max(0, user.getAttack()) - target.getEffectiveDefense();
        if (damage<0) {damage=0;}
        
        int oldHp = target.getCurrentHp();
        target.takeDamage(damage);
        
        StringBuilder sb = new StringBuilder();
        sb.append(user.getName()).append(" → BasicAttack → ").append(target.getName())
        .append(": HP: ").append(oldHp).append(" → ").append(target.getCurrentHp())
        .append(" (dmg: ").append(user.getAttack()).append("-").append(target.getBaseDefense());
        
        int defBuff = target.getEffectiveDefense() - target.getBaseDefense();
        if (defBuff > 0) {
        		sb.append("-").append(defBuff).append("[buff]");
        }
        sb.append("=").append(damage).append(")");
        
        
        if (!target.isAlive()) {
        		sb.append(" ✗ ELIMINATED");
        }
        return sb.toString();
        	
	}
	
	public String getName() { return "BasicAttack";}

}
