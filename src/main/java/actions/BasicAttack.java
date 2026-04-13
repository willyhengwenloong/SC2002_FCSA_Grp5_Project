package actions;
import combatants.Combatants;
public class BasicAttack implements Actions{
	
	@Override
	public void execute(Combatants user) {
        System.out.println(user.getName() + " uses Basic Attack.");
	}
	
	public void execute(Combatants attacker, Combatants target) {
        int damage = attacker.getAttack() - target.getEffectiveDefense();
        
        if (damage<0) {
        		damage=0;
        }
        
        target.setCurrentHp(target.getCurrentHp() - damage);
      
        
        if (target.getCurrentHp() < 0) {
            target.setCurrentHp(0);
        }
        
        if (target.getCurrentHp() == 0) {
        		target.setAlive(false);
        }
        
        System.out.println(attacker.getName() + " attacks " + target.getName() + " for " + damage + " damage. HP left: " + target.getCurrentHp());
	}
	
	public String getName() { return "BasicAttack";}

}
