package battle;

import java.util.ArrayList;
import java.util.List;
import combatants.Goblin;
import combatants.Player;
import combatants.Warrior;
import combatants.Wizard;
import combatants.Wolf;
import items.*;
import level.Level;
import strategy.speedBasedTurnOrderStrategy;
import ui.DisplayManager;
import ui.InputHandler;

public class GameManager {

    private PlayerType lastPlayerType;
    private List<ItemType> lastItemTypes;
    private Level.Difficulty lastDifficulty;

    private enum PlayerType { WARRIOR, WIZARD }
    private enum ItemType   { POTION, POWER_STONE, SMOKE_BOMB }

    public void run() {
        boolean playing = true;
        boolean useLastSettings = false;

        while (playing) {
            Player player;
            Level level;

            if (useLastSettings && lastPlayerType != null) {
                player = buildPlayer(lastPlayerType);
                addItems(player, lastItemTypes);
                level = new Level(lastDifficulty);
                System.out.println("\n  Replaying with the same settings...");
                System.out.println("  Player: " + player.getName()
                    + "  |  Difficulty: " + level.getDifficultyName());
            } else {
                GameSetup setup = new GameSetup();
                setup.setUp();
                player = setup.getSelectedPlayer();
                level  = setup.getSelectedLevel();
                lastPlayerType = (player instanceof Warrior) ? PlayerType.WARRIOR : PlayerType.WIZARD;
                lastItemTypes  = extractItemTypes(player);
                lastDifficulty = level.getDifficulty();
            }
            useLastSettings = false;

            Goblin.resetCounter();
            Wolf.resetCounter();

            BattleEngine engine = new BattleEngine(
                player, level, new speedBasedTurnOrderStrategy()
            );
            engine.startBattle();

            DisplayManager.printPostGameMenu();
            int choice = InputHandler.readInt(1, 3);
            switch (choice) {
                case 1: useLastSettings = true;  break;
                case 2: useLastSettings = false; break;
                case 3:
                    playing = false;
                    System.out.println("\n  Thanks for playing! Goodbye.\n");
                    break;
            }
        }
    }

    private Player buildPlayer(PlayerType type) {
        return type == PlayerType.WARRIOR ? new Warrior() : new Wizard();
    }

    private void addItems(Player player, List<ItemType> types) {
        for (ItemType t : types) {
            switch (t) {
                case POTION:      player.addItem(new Potion());     break;
                case POWER_STONE: player.addItem(new PowerStone()); break;
                case SMOKE_BOMB:  player.addItem(new SmokeBomb());  break;
            }
        }
    }

    private List<ItemType> extractItemTypes(Player player) {
        List<ItemType> types = new ArrayList<>();
        for (Items item : player.getItems()) {
            if (item instanceof Potion)          types.add(ItemType.POTION);
            else if (item instanceof PowerStone) types.add(ItemType.POWER_STONE);
            else                                 types.add(ItemType.SMOKE_BOMB);
        }
        return types;
    }
}
