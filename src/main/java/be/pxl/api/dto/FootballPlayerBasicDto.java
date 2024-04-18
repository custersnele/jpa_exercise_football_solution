package be.pxl.api.dto;

import be.pxl.domain.FootballPlayer;

public class FootballPlayerBasicDto {
	private String name;
	private int shirtNumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getShirtNumber() {
		return shirtNumber;
	}

	public void setShirtNumber(int shirtNumber) {
		this.shirtNumber = shirtNumber;
	}

	static FootballPlayerBasicDto from(FootballPlayer footballPlayer) {
		FootballPlayerBasicDto result = new FootballPlayerBasicDto();
		result.setName(footballPlayer.getName());
		result.setShirtNumber(footballPlayer.getShirtNumber());
		return result;
	}
}
