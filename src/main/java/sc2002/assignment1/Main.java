package sc2002.assignment1;

import battle.BattleEngine;
import battle.GameSetup;
// Main entry point for the Turn Based Combat
import combatants.Player;

public class Main {
	public static void main(String[] args) {
        GameSetup setup = new GameSetup();
        Player player = setup.settingUp();
        BattleEngine engine = new BattleEngine();
        engine.start(player);
    }
}
