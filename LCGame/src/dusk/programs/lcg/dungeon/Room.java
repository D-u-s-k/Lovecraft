package dusk.programs.lcg.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dusk.programs.lcg.monster.Monster;

public class Room {
	
	public enum DungeonType {
		Normal, Boss
	}
	
	public enum RoomType {
		Normal, Abnormal
	}
	
	public final DungeonType dungeonType;
	public final RoomType roomType = RoomType.Normal;
	public final int x,y;
	public final String uuid;
	public boolean doorUp = false, doorDown = false, doorLeft = false, doorRight = false;
	
	//Monsters
	List<Monster> presentEnemies = new ArrayList<Monster>();
	//Other objects
	
	public Room(DungeonType dungeonType, int numberOfEnemies, int x, int y) {
		uuid = UUID.randomUUID().toString();
		this.x = x;
		this.y = y;
		if (dungeonType == DungeonType.Normal) {
			this.dungeonType = dungeonType;
			for (int i = 0; i < numberOfEnemies; i++) {
				presentEnemies.add(Monster.generateNewEnemy());
			}
		} else {
			this.dungeonType = dungeonType;
		}
	}

}
