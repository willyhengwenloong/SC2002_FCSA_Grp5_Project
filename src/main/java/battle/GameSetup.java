package battle;
import combatants.*;
import ui.ConsoleUI;
import ui.InputHandler;

public class GameSetup {
	private Player selectedPlayer;
	
	public GameSetup() {}
	
	public void settingUp() {
		ConsoleUI.printLoadingScreen();
		selectPlayer();
	}
	
	public void selectPlayer() {
		ConsoleUI.printPlayerChoices();
		int choice = InputHandler.readInt(1, 2);
		if (choice == 1) {
			selectedPlayer = new Warrior();
		}else {
			selectedPlayer = new Wizard();
		}
		System.out.println(" Selected: " + selectedPlayer.getName());
	}
}
