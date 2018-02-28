package dusk.programs.lcg.src;

import dusk.programs.lcg.dungeon.Dungeon;
import dusk.programs.lcg.src.Main.Mode;

public class StateHandler {
	
	public static Dungeon d;
	
	public static void handleState() {
		if (Main.mode == Mode.Dungeon) {
			d = new Dungeon();
		}
	}

}
