package actions;
import combatants.Combatants;
public class BasicAttack implements Actions{
	
	@Override
	public void execute(Combatants user) {
        System.out.println(user.getName() + " uses Basic Attack.");
	}
	public void execute(Combatants attacker, Combatants target) {
        int damage = attacker.getAttack() - target.getDefense();
        
        if (damage<0) {
        	damage=0;
        }
        
        int newHp = target.getCurrentHp() - damage;
        if (newHp<0) {
        	newHp=0;
        }
        target.setCurrentHp(newHp);
        if (target.getCurrentHp() == 0) {
            target.setAlive(false);
        }
        System.out.println(attacker.getName() + " attacks " + target.getName()
        + " for " + damage + " damage.");
        System.out.println(target.getName() + " HP is now " + target.getCurrentHp() + ".");
	}

}
