package statusEffects;

import combatants.Combatants;

/**
 * ArcaneBlastEffect: tracks the attack bonus granted to the Wizard per kill.
 * The actual stat modification is stored on the Wizard directly.
 * This effect class is used to satisfy OCP for the effect system.
 */
public class ArcaneBlastEffect implements StatusEffect {

    private boolean expired = false;

    @Override
    public String onTurnStart(Combatants target) {
        return null; // passive bonus tracked on Wizard
    }

    @Override
    public void tick() { /* permanent until end of level */ }

    @Override
    public boolean isExpired() { return expired; }

    public void expire() { expired = true; }

    @Override
    public int getDuration() { return -1; } // -1 = until end of level

    @Override
    public String getName() { return "Arcane Boost"; }
}