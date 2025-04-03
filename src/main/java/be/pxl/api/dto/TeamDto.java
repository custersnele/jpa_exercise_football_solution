package be.pxl.api.dto;

import be.pxl.domain.FootballTeam;

public class TeamDto {
	private Long id;
	private String name;
	private String coach;
	private String city;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
		result.setId(footballTeam.getId());
		result.setName(footballTeam.getName());
		result.setCity(footballTeam.getCity());
		result.setCoach(footballTeam.getCoach());
		return result;
	}
}
