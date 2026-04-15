package battle;
import combatants.*;
import ui.DisplayManager;
import ui.InputHandler;
import level.Level;
import items.*;

public class GameSetup {
	private Player selectedPlayer;
	private Level selectedLevel; 
	
	public GameSetup() {}
	
	public void setUp() {
		DisplayManager.printLoadingScreen();
		selectPlayer();
		selectItems();
		DisplayManager.printEnemyInfo();
		selectDifficulty();
	}
	
	private void selectPlayer() {
		DisplayManager.printPlayerChoices();
		int choice = InputHandler.readInt(1, 2);
		if (choice == 1) {
			selectedPlayer = new Warrior();
		}else {
			selectedPlayer = new Wizard();
		}
		System.out.println(" Selected: " + selectedPlayer.getName());
		
	}
	
	private void selectItems() {
		DisplayManager.printItemChoices();
		for (int slot = 1; slot <= 2; slot++) {
			DisplayManager.printItemSlotChoice(slot);
			int choice = InputHandler.readInt(1, 3);
			selectedPlayer.addItem(createItem(choice));
			System.out.println(" Item: " + slot + " selected: " + selectedPlayer.getItems().get(slot - 1).getName());
		}
	}
	
	private Items createItem(int choice) {
		switch (choice) {
			case 1: return new Potion();
			case 2: return new PowerStone();
			default: return new SmokeBomb();
		}
	}
	
	private void selectDifficulty() {
		DisplayManager.printDifficultyChoices();
		int choice = InputHandler.readInt(1, 3);
		switch (choice) {
		 	case 1:{ selectedLevel = new Level(Level.Difficulty.EASY); break;}
		 	case 2:{ selectedLevel = new Level(Level.Difficulty.MEDIUM); break;}
		 	default:{ selectedLevel = new Level(Level.Difficulty.HARD); break;}
		}
		System.out.println(" Difficulty: " + selectedLevel.getDifficultyName()) ;
		System.out.println(" Initial Spawn: " + selectedLevel.getInitialSpawnDescription());
		
		if (selectedLevel.hasBackupSpawn()) {
			System.out.println(" Backup spawn: " + selectedLevel.getBackupSpawnDescription());
		}
	}
	
	public Player getSelectedPlayer() {
		return selectedPlayer;
	}
	public Level getSelectedLevel() {
		return selectedLevel;
	}
	
}
