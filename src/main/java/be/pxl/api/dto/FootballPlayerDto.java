package be.pxl.api.dto;

import be.pxl.Position;
import be.pxl.domain.FootballPlayer;

public class FootballPlayerDto {
	private Long id;
	private String name;
	private String email;
	private Position position;
	private String team;
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

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public static FootballPlayerDto from(FootballPlayer footballPlayer) {
		FootballPlayerDto result = new FootballPlayerDto();
		result.setId(footballPlayer.getId());
		result.setName(footballPlayer.getName());
		result.setEmail(footballPlayer.getEmail());
		result.setCity(footballPlayer.getTeam().getCity());
		result.setTeam(footballPlayer.getTeam().getName());
		result.setPosition(footballPlayer.getPosition());
		return result;
	}
}
