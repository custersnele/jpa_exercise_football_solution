package be.pxl.api.dto;

import be.pxl.domain.FootballTeam;

public class TeamDto {
	private String name;
	private String coach;
	private String city;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoach() {
		return coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public static TeamDto from(FootballTeam footballTeam) {
		TeamDto result = new TeamDto();
		result.setName(footballTeam.getName());
		result.setCity(footballTeam.getCity());
		result.setCoach(footballTeam.getCoach());
		return result;
	}
}
