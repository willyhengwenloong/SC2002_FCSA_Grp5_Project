package battle;
import combatants.*;
import ui.DisplayManager;
import ui.InputHandler;

public class GameSetup {
	private Player selectedPlayer;
	
	public GameSetup() {}
	
	public Player settingUp() {
		DisplayManager.printLoadingScreen();
		return selectPlayer();
	}
	
	public Player selectPlayer() {
		DisplayManager.printPlayerChoices();
		int choice = InputHandler.readInt(1, 2);
		if (choice == 1) {
			selectedPlayer = new Warrior();
		}else {
			selectedPlayer = new Wizard();
		}
		System.out.println(" Selected: " + selectedPlayer.getName());
		return selectedPlayer;
	}
}
