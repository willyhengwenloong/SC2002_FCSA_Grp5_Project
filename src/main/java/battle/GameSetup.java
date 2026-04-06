package battle;
import combatants.*;
import ui.DisplayManager;
import ui.InputHandler;

public class GameSetup {
	private Player selectedPlayer;
	
	public GameSetup() {}
	
	public void settingUp() {
		DisplayManager.printLoadingScreen();
		selectPlayer();
	}
	
	public void selectPlayer() {
		DisplayManager.printPlayerChoices();
		int choice = InputHandler.readInt(1, 2);
		if (choice == 1) {
			selectedPlayer = new Warrior();
		}else {
			selectedPlayer = new Wizard();
		}
		System.out.println(" Selected: " + selectedPlayer.getName());
	}
}
