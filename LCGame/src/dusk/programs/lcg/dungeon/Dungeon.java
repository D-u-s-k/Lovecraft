package dusk.programs.lcg.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dusk.programs.lcg.dungeon.Room.DungeonType;

public class Dungeon {

	public List<Room> rooms = new ArrayList<Room>();
	Random ran = new Random();


	private void generateRoom(int x, int y) {
		Room r = getRoomAt(x, y);
		if (r != null) {
			generateRoomInDirection(x, y, r, 0);
			generateRoomInDirection(x, y, r, 1);
			generateRoomInDirection(x, y, r, 2);
			generateRoomInDirection(x, y, r, 3);
		}
	}

	private void generateRoomInDirection(int x, int y, Room sourceRoom, int directionIndex) {
		switch (directionIndex) {
		case 0:
			x++;
			break;
		case 1:
			y++;
			break;
		case 2:
			x--;
			break;
		case 3:
			y--;
			break;
		}
		// Out of bounds
		if (x < 0 || x > 9 || y < 0 || y > 9) {
			return;
		}
		Room r = getRoomAt(x, y);
		// Already a room
		if (r != null) {
			return;
		}
		double roomChance = ran.nextDouble();
		if (roomChance < 0.5 || rooms.size() < 5) {
			Room room = new Room(DungeonType.Normal, ran.nextInt(3) - 1, x, y);
			switch (directionIndex) {
			case 0:
				room.doorLeft = true;
				sourceRoom.doorRight = true;
				break;
			case 1:
				room.doorUp = true;
				sourceRoom.doorDown = true;
				break;
			case 2:
				room.doorRight = true;
				sourceRoom.doorLeft = true;
				break;
			case 3:
				room.doorDown = true;
				sourceRoom.doorUp = true;
				break;
			}
			rooms.add(room);
			generateRoom(x, y);
		}
	}

	public Room getRoomAt(int x, int y) {
		for (Room r : rooms) {
			if (r.x == x && r.y == y) {
				return r;
			}
		}
		return null;
	}

	public Dungeon() {
		// for (int y = 0; y < 10; y++) {
		// for (int x = 0; x < 10; x++) {
		// rooms.add(new Room(DungeonType.Normal, ran.nextInt(3) - 1, x, y));
		// }
		// }

		int startRoom = ran.nextInt(36);
		int xStart, yStart;
		if (startRoom <= 9) {
			xStart = startRoom;
			yStart = 0;
		} else if (startRoom >= 26) {
			xStart = 35 - startRoom;
			yStart = 9;
		} else if (startRoom >= 10 && startRoom <= 17) {
			xStart = 9;
			yStart = startRoom - 9;
		} else {
			xStart = 0;
			yStart = startRoom - 17;
		}

		Room startRoomRoom = new Room(DungeonType.Normal, 0, xStart, yStart);
		startRoomRoom.visited =  true;
		rooms.add(startRoomRoom);
		generateRoom(xStart, yStart);
		Character.currentRoom = startRoomRoom;
		Character.x = 50;
		Character.y = 50;
		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				if (getRoomAt(x,y) == null) {
					System.out.print("-");
				} else {
					if (x == xStart && y == yStart) {
						System.out.print("S");
					} else {
						System.out.print("X");
					}
				}
			}
			System.out.println();
		}
	}
}
