package dusk.programs.lcg.monster;

public class Monster {
	
	public enum MonsterType {
		Zombie, Puker;
	}
	
	public final MonsterType monsterType;
	
	public Monster(MonsterType monsterType) {
		this.monsterType = monsterType;
	}
	
	
	public static Monster generateNewEnemy() {
		return null;
	}

}
